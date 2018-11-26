<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "BusinessInteligence\Season\Season.php";
require_once "Providers\SeasonProvider.php";

/**
 * Testes unitários para a classe Season.
 */
class SeasonTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Season
    */
   private $instance_;

   public function setUp()
   {
      $this->instance_ = \DBLib\SeasonProvider::getInstance()->getSeason( 1 );
   }

   public function testGetSeasonVO()
   {
      $this->assertInstanceOf( "\ValueObject\Season", $this->instance_->getSeasonVO() );
   }
}
?>