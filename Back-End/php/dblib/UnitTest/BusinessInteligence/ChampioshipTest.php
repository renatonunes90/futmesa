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
require_once "ErrorHandlers/errorhandler.php";

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
}
?>