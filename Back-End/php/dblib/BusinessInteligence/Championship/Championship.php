<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoChampionshipFactory;
use DAO\DaoRoundFactory;
use DAO\DaoGameFactory;

require_once "BusinessInteligence/Game/Game.php";
require_once "BusinessInteligence/Round/Round.php";
require_once "BusinessInteligence/Championship/ChampionshipCreator.php";
require_once "DataAccessObjects/Game/DaoGameFactory.php";
require_once "DataAccessObjects/Round/DaoRoundFactory.php";
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
    * @var array Mapa de objetos do tipo Round com as rodadas do campeonato, indexado pelo seu número.
    */
   private $rounds_;

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
      $this->rounds_ = array ();
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

      $newRounds = array ();
      foreach ( $this->rounds_ as $r )
      {
         $newRounds[] = clone $r;
      }
      $this->rounds_ = $newRounds;
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
    * @return array Lista de objetos de tipo Round.
    */
   public function getRounds(): array
   {
      $this->loadRounds();
      return $this->rounds_;
   }

   /**
    *
    * @param int $roundNumber
    *           Número da rodada do campeonato.
    * @return \DBLib\Round|NULL Objeto contendo a rodada ou null se ela não existir.
    */
   public function getRound( int $roundNumber ): ?\DBLib\Round
   {
      $round = null;
      $this->loadRounds();

      if ( array_key_exists( $roundNumber, $this->rounds_ ) )
      {
         $round = $this->rounds_[ $roundNumber ];
      }

      return $round;
   }

   /**
    * Força o recarregamento das informações do campeonato.
    */
   public function refresh(): void
   {
      $this->players_ = array ();
      $this->rounds_ = array ();

      $this->loadPlayers( true );
      $this->loadRounds( true );
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

                foreach( $this->rounds_ as $r ) {

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

   public function setRounds( array $rounds ): void
   {
      $this->rounds_ = $rounds;
   }

   private function loadRounds( bool $forceReload = false): void
   {
      if ( sizeOf( $this->rounds_ ) == 0 || $forceReload )
      {
         $daoGames = DaoGameFactory::getDaoGame();
         $allGames = $daoGames->getAllGames( $this->championshipVO_->id );
         $gamesByRound = array ();
         foreach ( $allGames as $g )
         {
            if ( !array_key_exists( $g->idround, $gamesByRound ) )
            {
               $gamesByRound[ $g->idround ] = array ();
            }

            $game = new Game( $g );
            $gamesByRound[ $g->idround ][ $g->id ] = $game;
         }

         $daoRound = DaoRoundFactory::getDaoRound();
         $rounds = $daoRound->getAllRounds( $this->championshipVO_->id );
         foreach ( $rounds as $r )
         {
            $this->rounds_[ $r->number ] = new Round( $r );
            if ( isset( $gamesByRound[ $r->id ] ) )
            {
               $this->rounds_[ $r->number ]->setGames( $gamesByRound[ $r->id ] );
            }
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
