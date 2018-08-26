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

   private $score1_;

   private $score2_;

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
}
