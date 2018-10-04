<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Round/Round.php";
require_once "ValueObjects/Round.php";

require_once "dto/DtoGame.php";

/**
 * A responsabilidade desta classe é encapsular os atributos de uma rodada para retonar em uma requisição HTTP.
 */
class DtoRound extends \ValueObject\Round
{
   /**
    *
    * @var Array Mapa de objetos do tipo DtoGame com os jogos da rodada.
    */
   public $games;

   /**
    * Construtor padrão.
    *
    * @param \DbLib\Round $round
    *           Objeto retornado da DBLib contendo as informações de uma rodada.
    */
   public function __construct( \DbLib\Round $round )
   {
      $this->copyData( $round->getRoundVO() );
      $this->games = array();
      $allGames = $round->getGames();
      foreach ( $allGames as $g )
      {
         $this->games[] = new DtoGame( $g );
      }
   }

   /**
    * Função para clonar uma rodada.
    */
   public function __clone()
   {
      $newGames = array ();
      foreach ( $this->games as $g )
      {
         $newGames[] = clone $g;
      }
      $this->games = $newGames;
   }

   /**
    *
    * @return array Lista de objetos de tipo Game.
    */
   public function getGames(): array
   {
      return $this->games;
   }

}
