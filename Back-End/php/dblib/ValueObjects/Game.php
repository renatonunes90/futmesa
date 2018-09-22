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
 * Tabela de jogos de campeonatos do banco de dados.
 */
class Game extends ValueObject
{

   /**
    *
    * @var int Identificador do jogo.
    */
   public $id;

   /**
    *
    * @var int Identificador da rodada.
    */
   public $idround;

   /**
    *
    * @var int Identificador do jogador 1 do jogo.
    */
   public $idplayer1;

   /**
    *
    * @var int Identificador do jogador 2 do jogo.
    */
   public $idplayer2;

   /**
    *
    * @var int Número da mesa de jogo.
    */
   public $gametable;

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
