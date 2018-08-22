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
}
?>