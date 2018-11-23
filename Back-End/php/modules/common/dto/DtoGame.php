<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Game/Game.php";
require_once "ValueObjects/Game.php";

require_once "modules/common/dto/DtoPlayer.php";

/**
 * A responsabilidade desta classe é encapsular os atributos de um jogo para retonar em uma requisição HTTP.
 */
class DtoGame extends \ValueObject\Game
{

   /**
    *
    * @var DtoPlayer
    */
   public $player1;

   /**
    *
    * @var DtoPlayer
    */
   public $player2;

   /**
    * Construtor padrão.
    *
    * @param \DbLib\Game $game
    *           Objeto retornado da DBLib contendo as informações de um jogador.
    */
   public function __construct( \DbLib\Game $game )
   {
      $this->copyData( $game->getGameVO() );
      $this->player1 = new DtoPlayer( $game->getPlayer1() );
      $this->player2 = new DtoPlayer( $game->getPlayer2() );
   }

   /**
    * Função para clonar um jogo.
    */
   public function __clone()
   {
      $this->player1 = clone $this->getPlayer1();
      $this->player2 = clone $this->getPlayer2();
   }

   /**
    *
    * @return DtoPlayer
    */
   public function getPlayer1(): DtoPlayer
   {
      return $this->player1;
   }

   /**
    *
    * @return DtoPlayer
    */
   public function getPlayer2(): DtoPlayer
   {
      return $this->player2;
   }

}
