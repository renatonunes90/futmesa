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

   /**
    * Campeonato com todos os jogos de teste.
    *
    * @var \DbLib\Championship
    */
   private $championship_;

   public function setUp()
   {
      $this->championship_ = ChampionshipProvider::getInstance()->getChampionship( 1 );
      $round = $this->championship_->getRound( 1 );
      $this->instance_ = $round->getGame( 1 );
   }

   public function testGetGameVO()
   {
      $this->assertInstanceOf( "\ValueObject\Game", $this->instance_->getGameVO() );
   }

   public function testGetPlayer1()
   {
      $player1 = $this->instance_->getPlayer1();
      $this->assertInstanceOf( "\DbLib\Player", $player1 );
      $this->assertEquals( "Jogador A", $player1->getPlayerVO()->name );
   }

   public function testGetPlayer2()
   {
      $player2 = $this->instance_->getPlayer2();
      $this->assertInstanceOf( "\DbLib\Player", $player2 );
      $this->assertEquals( "Jogador B", $player2->getPlayerVO()->name );
   }

   public function testHasPlayer()
   {
      $this->assertTrue( $this->instance_->hasPlayer( 1 ) );
      $this->assertTrue( $this->instance_->hasPlayer( 2 ) );
      $this->assertFalse( $this->instance_->hasPlayer( 4 ) );
   }

   public function testGetScore1()
   {
      $this->assertEquals( 1, $this->instance_->getScore1() );
   }

   public function testGetScore2()
   {
      $this->assertEquals( 1, $this->instance_->getScore2() );
   }

   public function testGetWinner()
   {
      $this->assertEquals( 0, $this->instance_->getWinner() );

      $round = $this->championship_->getRound( 1 );
      $anotherGame = $round->getGame( 2 );
      $this->assertEquals( 1, $anotherGame->getWinner() );

      $anotherGame = $round->getGame( 3 );
      $this->assertEquals( 2, $anotherGame->getWinner() );
   }

   public function testGetWinnerId()
   {
      $this->assertEquals( 0, $this->instance_->getWinnerId() );

      $round = $this->championship_->getRound( 1 );
      $anotherGame = $round->getGame( 2 );
      $this->assertEquals( 3, $anotherGame->getWinnerId() );

      $anotherGame = $round->getGame( 3 );
      $this->assertEquals( 6, $anotherGame->getWinnerId() );
   }
}
?>