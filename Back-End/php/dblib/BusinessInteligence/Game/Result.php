<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/GameResult.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de um resultado de um jogo.
 */
class Result
{

   /**
    *
    * @var int Número de gols do jogador 1.
    */
   private $score1_;

   /**
    *
    * @var int Número de gols do jogador 2.
    */
   private $score2_;

   /**
    *
    * @var int Identificador do jogador vencedor.
    */
   private $winnerId_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\GameResult $gameResultVO
    *           Objeto contendo as informações do resultado do jogo no banco de dados.
    */
   public function __construct( \ValueObject\GameResult $gameResultVO )
   {
      $this->score1_ = $gameResultVO->score1;
      $this->score2_ = $gameResultVO->score2;
      $this->winnerId_ = $gameResultVO->idwinner;
   }

   /**
    * Função para clonar um resultado de jogo.
    */
   public function __clone()
   {
      $this->score1_ = clone $this->score1;
      $this->score2_ = clone $this->score2;
      $this->winnerId_ = clone $this->idwinner;
   }

   /**
    *
    * @return int Número de gols do jogador 1.
    */
   public function getScore1(): int
   {
      return $this->score1_;
   }

   /**
    *
    * @return int Número de gols do jogador 2.
    */
   public function getScore2(): int
   {
      return $this->score2_;
   }

   /**
    *
    * @return int Número do jogador vencedor (1 ou 2) ou 0 se houve empate.
    */
   public function getWinner(): int
   {
      $winner = 0;

      if ( $this->score1_ > $this->score2_ )
      {
         $winner = 1;
      }
      else if ( $this->score1_ < $this->score2_ )
      {
         $winner = 2;
      }

      return $winner;
   }

   /**
    *
    * @return int Identificador do jogador vencedor.
    */
   public function getWinnerId(): int
   {
      return $this->winnerId_;
   }
}
