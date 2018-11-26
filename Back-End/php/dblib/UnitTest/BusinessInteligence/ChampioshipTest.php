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

   public function testGetRounds()
   {
      $rounds = $this->instance_->getRounds();
      $this->assertCount( 8, $rounds );
      foreach ( $rounds as $r )
      {
         $this->assertInstanceOf( "\DbLib\Round", $r );
      }
   }

   public function testGetRound()
   {
      $round = $this->instance_->getRound( 1 );
      $this->assertInstanceOf( "\DbLib\Round", $round );

      $round = $this->instance_->getRound( -1 );
      $this->assertNull( $round );

      $round = $this->instance_->getRound( 9 );
      $this->assertNull( $round );
   }
}
?>