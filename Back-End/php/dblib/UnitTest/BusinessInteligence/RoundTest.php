<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;
use DBLib\ChampionshipProvider;

require_once "BusinessInteligence\Round\Round.php";

/**
 * Testes unitários para a classe Round.
 */
class RoundTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Round
    */
   private $instance_;

   public function setUp()
   {
      $championship = ChampionshipProvider::getInstance()->getChampionship( 1 );
      $this->instance_ = $championship->getRound( 1 );
   }

   public function testGetRoundVO()
   {
      $this->assertInstanceOf( "\ValueObject\Round", $this->instance_->getRoundVO() );
   }
}
?>