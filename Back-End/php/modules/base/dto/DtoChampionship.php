<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Championship/Championship.php";
require_once "ValueObjects/Championship.php";

require_once "dto/DtoPlayer.php";

/**
 */
class DtoChampionship extends \ValueObject\Championship
{

   /**
    *
    * @var array
    */
   public $players;

   /**
    *
    * @var array
    */
   public $phases;

   /**
    *
    * @param \DbLib\Championship $championship
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
      $this->phases = array();
      $phases = $championship->getPhases();
      foreach ( $phases as $p )
      {
          $this->phases[] = $p->getPhaseVO();
      }
   }

   /**
    */
   public function __clone()
   {
      $newPlayers = array ();
      foreach ( $this->players as $p )
      {
         $newPlayers[] = clone $p;
      }
      $this->players = $newPlayers;

      $newPhases = array ();
      foreach ( $this->phases as $p )
      {
          $newPhases[] = clone $p;
      }
      $this->phases = $newPhases;
   }
   
   /**
    *
    * @return array 
    */
   public function getPlayers(): array
   {
      return $this->players;
   }
   
   /**
    *
    * @return array
    */
   public function getPhases(): array
   {
      return $this->phases;
   }
}
