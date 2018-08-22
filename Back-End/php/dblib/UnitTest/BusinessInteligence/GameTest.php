<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;
use DBLib\ChampionshipProvider;

require_once "BusinessInteligence\Game\Game.php";

/**
 * Testes unitários para a classe Game.
 */
class GameTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Game
    */
   private $instance_;

   public function setUp()
   {
      $championship = ChampionshipProvider::getInstance()->getChampionship( 1 );
      $round = $championship->getRound( 1 );
      $this->instance_ = $round->getGame( 1 );
   }

   public function testGetGameVO()
   {
      $this->assertInstanceOf( "\ValueObject\Game", $this->instance_->getGameVO() );
   }
}
?>