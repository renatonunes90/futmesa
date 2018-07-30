<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "Providers/PlayerProvider.php";

/**
 * Testes unitários para a classe PlayerProvider, que tem o objetivo de disponibilizar os objetos
 * buscando de um DAO.
 */
class PlayerProviderTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\PlayerProvider
    */
   private $provider_;

   public function setUp()
   {
      $this->provider_ = \DBLib\PlayerProvider::getInstance();
   }

   public function testGetPlayer()
   {
      // jogador de id -1 não existe
      $this->assertNull( $this->provider_->getPlayer( -1 ) );

      $this->assertInstanceOf( "\DBLib\Player", $this->provider_->getPlayer( 3 ) );
   }

   public function testGetPlayers()
   {
      $players = $this->provider_->getPlayers( array ( 1 ,3 ,9 ) );
      $this->assertEquals( 2, count( $players ) );
      foreach( $players as $p )
      {
         $this->assertInstanceOf( "\DBLib\Player", $p );
      }
   }

   public function testGetAllPlayers()
   {
      $players = $this->provider_->getAllPlayers();
      $this->assertEquals( 8, count( $players ) );
      foreach( $players as $p )
      {
         $this->assertInstanceOf( "\DBLib\Player", $p );
      }
   }
}
?>