<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Championship/Championship.php";
require_once "ValueObjects/Championship.php";

require_once "modules/common/dto/DtoPlayer.php";
require_once "modules/common/dto/DtoRound.php";

/**
 * A responsabilidade desta classe é encapsular os atributos de um campeonato para retonar em uma requisição HTTP.
 */
class DtoChampionship extends \ValueObject\Championship
{

   /**
    *
    * @var array Mapa de objetos do tipo DtoPlayer com os participantes do campeonato.
    */
   public $players;

   /**
    *
    * @var array Mapa de objetos do tipo DtoRound com as rodadas do campeonato.
    */
   public $rounds;

   /**
    * Construtor padrão.
    *
    * @param \DbLib\Championship $championship
    *           Objeto retornado da DBLib contendo as informações de um campeonato.
    */
   public function __construct( \DbLib\Championship $championship )
   {
      $this->copyData( $championship->getChampionshipVO() );
      $this->players = array();
      $players = $championship->getPlayers();
      foreach ( $players as $p )
      {
         $this->players[] = new DtoPlayer( $p ); 
      }
      $this->rounds = array();
      $rounds = $championship->getRounds();
      foreach ( $rounds as $r )
      {
         $this->rounds[] = new DtoRound( $r );
      }
   }

   /**
    * Função para clonar um campeonato.
    */
   public function __clone()
   {
      $newPlayers = array ();
      foreach ( $this->players as $p )
      {
         $newPlayers[] = clone $p;
      }
      $this->players = $newPlayers;

      $newRounds = array ();
      foreach ( $this->rounds as $r )
      {
         $newRounds[] = clone $r;
      }
      $this->rounds = $newRounds;
   }
   
   /**
    *
    * @return array Lista de objetos de tipo DtoPlayer que participam do campeonato.
    */
   public function getPlayers(): array
   {
      return $this->players;
   }
   
   /**
    *
    * @return array Lista de objetos de tipo DtoRound.
    */
   public function getRounds(): array
   {
      return $this->rounds;
   }
}
