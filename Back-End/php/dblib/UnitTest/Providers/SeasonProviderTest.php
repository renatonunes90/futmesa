<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "Providers/SeasonProvider.php";

/**
 * Testes unitários para a classe SeasonProvider, que tem o objetivo de disponibilizar os objetos
 * buscando de um DAO.
 */
class SeasonProviderTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\SeasonProvider
    */
   private $provider_;

   public function setUp()
   {
      $this->provider_ = \DBLib\SeasonProvider::getInstance();
   }

   public function testGetSeason()
   {
      // temporada de id -1 não existe
      $this->assertNull( $this->provider_->getSeason( -1 ) );

      $this->assertInstanceOf( "\DBLib\Season", $this->provider_->getSeason( 1 ) );
   }

   public function testGetSeasons()
   {
      $seasons = $this->provider_->getSeasons( array ( 1, 2, 9 ) );
      $this->assertEquals( 2, count( $seasons ) );
      foreach ( $seasons as $s )
      {
         $this->assertInstanceOf( "\DBLib\Season", $s );
      }
   }

   public function testGetAllSeasons()
   {
      $seasons = $this->provider_->getAllSeasons();
      $this->assertEquals( 2, count( $seasons ) );
      foreach ( $seasons as $s )
      {
         $this->assertInstanceOf( "\DBLib\Season", $s );
      }
   }
}
?>