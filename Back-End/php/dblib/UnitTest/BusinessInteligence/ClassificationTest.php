<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;
use DBLib\Classification;

require_once "BusinessInteligence\Championship\Classification.php";

/**
 * Testes unitários para a classe Classification.
 */
class ClassificationTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Classification
    */
   private $instance_;

   public function setUp()
   {
      $this->instance_ = new Classification( 1, 4 );
      $this->instance_->addWin();
      $this->instance_->addWin();
      $this->instance_->addTie();
      $this->instance_->addLoss();
      $this->instance_->addGoalsCon( 10 );
      $this->instance_->addGoalsPro( 15 );
   }

   public function testGetNumberOfGames()
   {
      $this->assertEquals( 4, $this->instance_->getNumberOfGames() );
   }

   public function testGetPoints()
   {
      $this->assertEquals( 7, $this->instance_->getPoints() );
   }

   public function testGetGoalDifference()
   {
      $this->assertEquals( 5, $this->instance_->getGoalDifference() );
   }

   public function testGetWinRate()
   {
      $this->assertEquals( ( 7 / 12 ) * 100, $this->instance_->getWinRate() );
   }
}
?>