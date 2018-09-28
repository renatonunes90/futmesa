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

require_once "DataAccessObjects/Round/DaoRoundFactory.php";
require_once "ValueObjects/Championship.php";

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

   private function loadPlayers( bool $forceReload = false): void
   {
      if ( sizeOf( $this->players_ ) == 0 || $forceReload )
      {
         $dao = DaoChampionshipFactory::getDaoChampionship();
         $participantIds = $dao->getParticipants( $this->championshipVO_->id );
         $this->players_ = PlayerProvider::getInstance()->getPlayers( $participantIds );
      }
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
}
