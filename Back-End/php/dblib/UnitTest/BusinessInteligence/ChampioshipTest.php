<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "BusinessInteligence\Championship\Championship.php";
require_once "Providers\ChampionshipProvider.php";

/**
 * Testes unitários para a classe Championship.
 */
class ChampionshipTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Championship
    */
   private $instance_;

   public function setUp()
   {
      $this->instance_ = \DBLib\ChampionshipProvider::getInstance()->getChampionship( 1 );
   }

   public function testGetChampionshipVO()
   {
      $this->assertInstanceOf( "\ValueObject\Championship", $this->instance_->getChampionshipVO() );
   }

   public function testGetPlayers()
   {
      $players = $this->instance_->getPlayers();
      $this->assertCount( 8, $players );
      foreach ( $players as $p )
      {
         $this->assertInstanceOf( "\DbLib\Player", $p );
      }
   }

   public function testGetPlayer()
   {
      $player = $this->instance_->getPlayer( 1 );
      $this->assertInstanceOf( "\DbLib\Player", $player );

      $player = $this->instance_->getPlayer( -1 );
      $this->assertNull( $player );

      $player = $this->instance_->getPlayer( 9 );
      $this->assertNull( $player );
   }

   public function testGetPhases()
   {
      $phases = $this->instance_->getPhases();
      $this->assertCount( 2, $phases );
      foreach ( $phases as $p )
      {
         $this->assertInstanceOf( "\DbLib\Phase", $p );
      }
   }

   public function testGetPhase()
   {
      $phase = $this->instance_->getPhase( 1 );
      $this->assertInstanceOf( "\DbLib\Phase", $phase );

      $phase = $this->instance_->getPhase( -1 );
      $this->assertNull( $phase );

      $phase = $this->instance_->getPhase( 3 );
      $this->assertNull( $phase );
   }
}
?>