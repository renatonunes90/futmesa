<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "Providers/ChampionshipProvider.php";

/**
 * Testes unitários para a classe ChampionshipProvider, que tem o objetivo de disponibilizar os objetos
 * buscando de um DAO.
 */
class ChampionshipProviderTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\ChampionshipProvider
    */
   private $provider_;

   public function setUp()
   {
      $this->provider_ = \DBLib\ChampionshipProvider::getInstance();
   }

   public function testGetChampionship()
   {
      // id -1 não existe
      $this->assertNull( $this->provider_->getChampionship( -1 ) );

      $this->assertInstanceOf( "\DBLib\Championship", $this->provider_->getChampionship( 1 ) );
   }

   public function testGetChampionships()
   {
      $champioships = $this->provider_->getChampionships( array ( 1 ,3 ,9 ) );
      $this->assertEquals( 1, count( $champioships ) );
      foreach( $champioships as $c )
      {
         $this->assertInstanceOf( "\DBLib\Championship", $c );
      }
   }

   public function testGetAllChampionships()
   {
      $championships = $this->provider_->getAllChampionships();
      $this->assertEquals( 1, count( $championships ) );
      foreach( $championships as $c )
      {
         $this->assertInstanceOf( "\DBLib\Championship", $c );
      }
   }
}
?>