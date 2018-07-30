<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "Providers/TableObjectProvider.php";

/**
 * Testes unitários para a classe TableObjectProvider, que tem o objetivo de disponibilizar os objetos
 * buscando de um DAO.
 */
class TableObjectProviderTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\TableObjectProvider
    */
   private $provider_;

   public function setUp()
   {
      $this->provider_ = \DBLib\TableObjectProvider::getInstance();
   }

   public function testGetTableObject()
   {
      // ativo de id -1 não existe
      $this->assertNull( $this->provider_->getTableObject( -1 ) );
   }

   public function testGetTableObjects()
   {
   }

   public function testGetAllObjects()
   {
   }

   public function testGetTableObjectsCount()
   {
   }
}
?>