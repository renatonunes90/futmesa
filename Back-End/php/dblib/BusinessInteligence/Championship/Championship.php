<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoChampionshipFactory;
use DAO\DaoPhaseFactory;

require_once "BusinessInteligence/Game/Game.php";
require_once "BusinessInteligence/Phase/Phase.php";
require_once "BusinessInteligence/Championship/ChampionshipCreator.php";
require_once "DataAccessObjects/Game/DaoGameFactory.php";
require_once "DataAccessObjects/Phase/DaoPhaseFactory.php";
require_once "ErrorHandlers/ChampionshipException.php";
require_once "ValueObjects/Championship.php";
require_once "ValueObjects/Participant.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de campeonatos.
 */
class Championship
{

   /**
    *
    * @var \ValueObject\Championship VO do campeonato.
    */
   private $championshipVO_;

   /**
    *
    * @var array Mapa de objetos do tipo Player com os participantes do campeonato.
    */
   private $players_;

   /**
    *
    * @var array 
    */
   private $phases_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Championship $championshipVO
    *           Objeto contendo as informações do campeonato no banco de dados.
    */
   public function __construct( \ValueObject\Championship $championshipVO )
   {
      $this->championshipVO_ = $championshipVO;
      $this->players_ = array ();
      $this->phases_ = array ();
   }

   /**
    * Função para clonar um campeonato.
    */
   public function __clone()
   {
      $this->championshipVO_ = clone $this->getChampionshipVO();

      $newPlayers = array ();
      foreach ( $this->players_ as $p )
      {
         $newPlayers[] = clone $p;
      }
      $this->players_ = $newPlayers;

      $newPhases = array ();
      foreach ( $this->phases_ as $r )
      {
         $newPhases[] = clone $r;
      }
      $this->phases_ = $newPhases;
   }

   /**
    * Obtém o VO do campeonato.
    *
    * @return \ValueObject\Championship Objeto contendo as informações do campeonato no banco de dados.
    */
   public function getChampionshipVO(): \ValueObject\Championship
   {
      return $this->championshipVO_;
   }

   /**
    *
    * @return array Lista de objetos de tipo Player que participam do campeonato.
    */
   public function getPlayers(): array
   {
      $this->loadPlayers();
      return $this->players_;
   }

   /**
    *
    * @param int $playerId
    *           Identificador do jogador.
    * @return \DBLib\Player|NULL Objeto contendo o jogador ou null se ele não existir.
    */
   public function getPlayer( int $playerId ): ?\DBLib\Player
   {
      $player = null;
      $this->loadPlayers();

      if ( array_key_exists( $playerId, $this->players_ ) )
      {
         $player = $this->players_[ $playerId ];
      }

      return $player;
   }

   /**
    *
    * @return array 
    */
   public function getPhases(): array
   {
      $this->loadPhases();
      return $this->phases_;
   }

   /**
    *
    * @param int $phaseNumber
    * @return \DBLib\Phase|NULL
    */
   public function getPhase( int $phaseNumber ): ?\DBLib\Phase
   {
      $phase = null;
      $this->loadPhases();

      if ( array_key_exists( $phaseNumber, $this->phases_ ) )
      {
          $phase = $this->phases_[ $phaseNumber ];
      }

      return $phase;
   }

   /**
    * Força o recarregamento das informações do campeonato.
    */
   public function refresh(): void
   {
      $this->players_ = array ();
      $this->phases_ = array ();

      $this->loadPhases( true );
      $this->loadPlayers( true );
   }
   
   public function save(): bool
   {
      $champs = array( $this->championshipVO_ );
      
      // valida os campos do campenonato
      $this->validate();
      
      if ( $this->championshipVO_->id <= 0 )
      {
         $result = DaoChampionshipFactory::getDaoChampionship()->createChampionships( $champs );
         if ( $result )
         {
             $this->championshipVO_->id = DaoChampionshipFactory::getDaoChampionship()->getLastInsertedId();
             
             $participants = array();
             foreach ( $this->players_ as $p )
             {
                 $part = new \ValueObject\Participant();
                 $part->idchampionship = $this->championshipVO_->id;
                 $part->idplayer = $p->getPlayerVO()->id;
                 $participants[] = $part;
                }
                
                $result = DaoChampionshipFactory::getDaoChampionship()->saveParticipants( $participants );
                DaoChampionshipFactory::getDaoChampionship()->deleteChampionships( array( $this->championshipVO_->id ) ); 
                
                $creator = new ChampionshipCreator($this);
                $creator->populateRoundsAndGames();

                foreach( $this->phases_ as $r ) {

                    foreach ( $r->getGames() as $g ) {
                        $game = $g->getGameVO();

                        $gameStr = "Rodada: " . $game->idround . " - "  . $game->idplayer1 . " VS " . $game->idplayer2 . " mesa " . $game->gametable ."\n";
                        test( $gameStr );
                    }

                }            
         }
      }
      else 
      {
         $result = DaoChampionshipFactory::getDaoChampionship()->updateChampionships( $champs );
      }
         
      return $result;
   }
   
   public function setPlayers( array $players ): void
   {
      $this->players_ = $players;
   }

   private function loadPlayers( bool $forceReload = false): void
   {
      if ( sizeOf( $this->players_ ) == 0 || $forceReload )
      {
         $dao = DaoChampionshipFactory::getDaoChampionship();
         $participantIds = $dao->getParticipants( $this->championshipVO_->id );
         $this->players_ = PlayerProvider::getInstance()->getPlayers( $participantIds );
      }
   }

   public function setPhases( array $phases ): void
   {
      $this->phases_ = $phases;
   }

   private function loadPhases( bool $forceReload = false): void
   {
      if ( sizeOf( $this->phases_ ) == 0 || $forceReload )
      {
         $daoPhase = DaoPhaseFactory::getDaoPhase();
         $phases = $daoPhase->getAllPhases( $this->championshipVO_->id );
         foreach ( $phases as $p )
         {
            $this->phases_[ $p->number ] = new Phase( $p );
         }
      }
   }
   
   private function validate() : void
   {
      $emptyFields = array(); 
      if ( $this->championshipVO_->idseason === "" )
      {
         $emptyFields[] = "Temporada";
      }
      if ( $this->championshipVO_->name == "" )
      {
         $emptyFields[] = "Nome";
      }
      if ( $this->championshipVO_->type === "" )
      {
         $emptyFields[] = "Tipo";
      }
      if ( $this->championshipVO_->basedate === "" )
      {
         $emptyFields[] = "Data Base";
      }
      if ( $this->championshipVO_->isfinished != 0 && $this->championshipVO_->isfinished != 1 )
      {
         $emptyFields[] = "Encerrado";
      }
      if ( $this->championshipVO_->roundsbyday === "" )
      {
         $emptyFields[] = "Rodadas P/ Dia";
      }
      if ( $this->championshipVO_->gamesbyround === "" )
      {
         $emptyFields[] = "Jogos P/ Rodada";
      }
      if ( $this->championshipVO_->dateincr === "" )
      {
         $emptyFields[] = "Incremento de Datas";
      }
         
      if ( sizeOf( $emptyFields ) > 0 )
      {
         throw new ChampionshipManagerException( "Os seguintes campos não podem ser vazios: " . implode( ", ", $emptyFields ) );
      }
        
      if ( $this->championshipVO_->idseason <= 0 )
      {
         throw new ChampionshipManagerException( "Temporada inválida." );
      }
      if ( $this->championshipVO_->type < 0 ||  $this->championshipVO_->type > 2 )
      {
         throw new ChampionshipManagerException( "Tipo inválido." );
      }
      if ( $this->championshipVO_->roundsbyday <= 0 )
      {
         throw new ChampionshipManagerException( "Rodadas p/ dia deve ser maior que 0." );
      }
      if ( $this->championshipVO_->gamesbyround <= 0 )
      {
         throw new ChampionshipManagerException( "Jogos p/ rodada deve ser maior que 0." );
      }
      if ( $this->championshipVO_->dateincr <= 0 )
      {
         throw new ChampionshipManagerException( "Incremento de datas deve ser maior que 0." );
      }
      
      if ( sizeOf( $this->players_ ) <= 1 )
      {
         throw new ChampionshipManagerException( "Campeonato deve possuir ao menos 2 participantes." );
      }
   }
}
