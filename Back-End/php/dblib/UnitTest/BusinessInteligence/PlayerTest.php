<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "BusinessInteligence\Player\Player.php";
require_once "Providers\PlayerProvider.php";

/**
 * Testes unitários para a classe Player.
 */
class PlayerTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Player
    */
   private $instance_;

   public function setUp()
   {
      $this->instance_ = \DBLib\PLayerProvider::getInstance()->getPlayer( 3 );
   }

   public function testGetPlayerVO()
   {
      $this->assertInstanceOf( "\ValueObject\Player", $this->instance_->getPlayerVO() );
   }
}
?>