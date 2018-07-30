<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "Providers/WebUserProvider.php";

/**
 * Testes unitários para a classe WebUser.
 */
class WebUserTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\WebUserProvider
    */
   private $instance_;

   public function setUp()
   {
      $this->instance_ = \DBLib\WebUserProvider::getInstance();
   }

   public function testGetWebUserVO()
   {
      $wu = $this->instance_->getWebUser( 1 );
      $this->assertInstanceOf( "ValueObject\WebUser", $wu->getWebUserVO() );

      $wu = $this->instance_->getWebUser( -1 );
      $this->assertNull( $wu );
   }
}

?>