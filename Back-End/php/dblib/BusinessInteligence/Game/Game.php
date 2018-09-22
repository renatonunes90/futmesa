<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/Game.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de um jogo.
 */
class Game
{

   /**
    *
    * @var \ValueObject\Game VO do jogo.
    */
   private $gameVO_;

   /**
    *
    * @var \DBLib\Player
    */
   private $player1_;

   /**
    *
    * @var \DBLib\Player
    */
   private $player2_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Game $gameVO
    *           Objeto contendo as informações do jogo no banco de dados.
    */
   public function __construct( \ValueObject\Game $gameVO )
   {
      $this->gameVO_ = $gameVO;
      $this->player1_ = null;
      $this->player2_ = null;
   }

   /**
    * Função para clonar um jogo.
    */
   public function __clone()
   {
      $this->gameVO_ = clone $this->getGameVO();
   }

   /**
    * Obtém o VO da rodada.
    *
    * @return \ValueObject\Game Objeto contendo as informações da rodada no banco de dados.
    */
   public function getGameVO(): \ValueObject\Game
   {
      return $this->gameVO_;
   }

   /**
    *
    * @return \DbLib\Player
    */
   public function getPlayer1(): \DbLib\Player
   {
      $this->loadPlayers();
      return $this->player1_;
   }

   /**
    *
    * @return \DbLib\Player
    */
   public function getPlayer2(): \DbLib\Player
   {
      $this->loadPlayers();
      return $this->player2_;
   }

   /**
    *
    * @return int Número de gols do jogador 1. -1 se o jogo ainda não aconteceu.
    */
   public function getScore1(): int
   {
      return $this->hasResult() ? $this->gameVO_->score1 : -1;
   }

   /**
    *
    * @return int Número de gols do jogador 2. -1 se o jogo ainda não aconteceu.
    */
   public function getScore2(): int
   {
      return $this->hasResult() ? $this->gameVO_->score2 : -1;
   }

   /**
    *
    * @return int Número do jogador vencedor (1 ou 2) ou 0 se houve empate. -1 se o jogo ainda não aconteceu.
    */
   public function getWinner(): int
   {
      $winner = -1;

      if ( $this->hasResult() )
      {
         $winner = 0;

         if ( $this->getScore1() > $this->getScore2() )
         {
            $winner = 1;
         }
         else if ( $this->getScore1() < $this->getScore2() )
         {
            $winner = 2;
         }
      }

      return $winner;
   }

   /**
    *
    * @return int Identificador do jogador vencedor. -1 se o jogo ainda não aconteceu.
    */
   public function getWinnerId(): int
   {
      return $this->hasResult() ? $this->gameVO_->idwinner : -1;
   }

   /**
    *
    * @param int $playerId
    * @return bool Flag indicando se o jogador está no jogo.
    */
   public function hasPlayer( int $playerId ): bool
   {
      return $playerId == $this->getPlayer1()->getPlayerVO()->id || $playerId == $this->getPlayer2()->getPlayerVO()->id;
   }

   /**
    *
    * @return bool Flag indicando se o resultado foi setado.
    */
   public function hasResult(): bool
   {
      return $this->gameVO_->score1 !== null && $this->gameVO_->score2 !== null;
   }

   private function loadPlayers( bool $forceReload = false): void
   {
      if ( $this->player1_ == null || $this->player2_ == null || $forceReload )
      {
         $this->player1_ = PlayerProvider::getInstance()->getPlayer( $this->gameVO_->idplayer1 );
         $this->player2_ = PlayerProvider::getInstance()->getPlayer( $this->gameVO_->idplayer2 );
      }
   }
}
