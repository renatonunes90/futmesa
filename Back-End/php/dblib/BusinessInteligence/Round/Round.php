<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoGameFactory;

require_once "DataAccessObjects/Game/DaoGameFactory.php";
require_once "ValueObjects/Round.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de uma rodada.
 */
class Round
{

   /**
    *
    * @var \ValueObject\Round VO da rodada.
    */
   private $roundVO_;

   /**
    *
    * @var Array Mapa contendo os jogos da rodada indexados pelo seu identificador.
    */
   private $games_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Round $roundVO
    *           Objeto contendo as informações da rodada no banco de dados.
    */
   public function __construct( \ValueObject\Round $roundVO )
   {
      $this->roundVO_ = $roundVO;
      $this->games_ = array ();
   }

   /**
    * Função para clonar uma rodada.
    */
   public function __clone()
   {
      $this->roundVO_ = clone $this->getRoundVO();

      $newGames = array ();
      foreach ( $this->games_ as $g )
      {
         $newGames[] = clone $g;
      }
      $this->games_ = $newGames;
   }

   /**
    * Obtém o VO da rodada.
    *
    * @return \ValueObject\Round Objeto contendo as informações da rodada no banco de dados.
    */
   public function getRoundVO(): \ValueObject\Round
   {
      return $this->roundVO_;
   }

   /**
    *
    * @return array Lista de objetos de tipo Game.
    */
   public function getGames(): array
   {
      $this->loadGames();
      return $this->games_;
   }

   /**
    *
    * @param int $gameId
    *           Identificador do jogo.
    * @return \DBLib\Game|NULL Objeto contendo o jogo ou null se ele não existir.
    */
   public function getGame( int $gameId ): ?\DBLib\Game
   {
      $game = null;
      $this->loadGames();

      if ( array_key_exists( $gameId, $this->games_ ) )
      {
         $game = $this->games_[ $gameId ];
      }

      return $game;
   }

   private function loadGames( bool $forceReload = false): void
   {
      if ( sizeOf( $this->games_ ) == 0 || $forceReload )
      {
         $dao = DaoGameFactory::getDaoGame();
         $games = $dao->getAllGamesByRound( $this->roundVO_->id );
         foreach ( $games as $g )
         {
            $this->games_[ $g->id ] = new Game( $g );
         }
      }
   }
}
