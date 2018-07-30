<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace ValueObject;

require_once "ValueObjects/ValueObject.php";

/**
 * Tabela de resultados de jogos de campeonatos do banco de dados.
 */
class GameResult extends ValueObject
{

   /**
    *
    * @var int Identificador do jogo.
    */
   public $idgame;

   /**
    *
    * @var int Gols do jogador 1 no jogo.
    */
   public $score1;

   /**
    *
    * @var int Gols do jogador 2 no jogo.
    */
   public $score2;

   /**
    *
    * @var mixed Horário que o resultado foi inserido.
    */
   public $inputdate;

   /**
    *
    * @var int Identificador do jogador vencedor.
    */
   public $idwinner;
}
