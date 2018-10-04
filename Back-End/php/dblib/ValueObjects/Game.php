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
   
   /**
    * Preenche as informações deste objeto a partir de outro do mesmo tipo.
    *
    * @param Game $game
    */
   public function copyData( Game $game ) : void
   {
      $this->id = $game->id;
      $this->idround = $game->idround;
      $this->idplayer1 = $game->idplayer1;
      $this->idplayer2 = $game->idplayer2;
      $this->gametable = $game->gametable;
      $this->score1 = $game->score1;
      $this->score2 = $game->score2;
      $this->inputdate = $game->inputdate;
      $this->idwinner = $game->idwinner;
   }
}
