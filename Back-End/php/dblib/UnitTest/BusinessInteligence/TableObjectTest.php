<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "BusinessInteligence\TableObject\TableObject.php";
require_once "Providers\TableObjectProvider.php";

/**
 * Testes unitários para a classe TableObject.
 */
class TableObjectTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\TableObjectProvider
    */
   private $instance_;

   public function setUp()
   {
      $this->instance_ = \DBLib\TableObjectProvider::getInstance();
   }

   public function testCanWrite()
   {
      $asset = $this->instance_->getTableObject( 1 );
      $this->assertTrue( $asset->canWrite() );
   }

   public function testGetTableObjectVO()
   {
      $object = $this->instance_->getTableObject( 1 );
      $this->assertInstanceOf( "\ValueObject\TableObject", $object->getTableObjectVO() );
   }
}
?>