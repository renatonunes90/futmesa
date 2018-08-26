<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;
use DBLib\ChampionshipProvider;

require_once "BusinessInteligence\Game\Result.php";

/**
 * Testes unitários para a classe Result.
 */
class ResultTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Result
    */
   private $instance_;

   /**
    * Rodada com todos os jogos de teste.
    *
    * @var \DbLib\Round
    */
   private $round_;

   public function setUp()
   {
      $championship = ChampionshipProvider::getInstance()->getChampionship( 1 );
      $this->round_ = $championship->getRound( 1 );
      $game = $this->round_->getGame( 1 );
      $this->instance_ = $game->getResult();
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

      $anotherGame = $this->round_->getGame( 2 );
      $result = $anotherGame->getResult();
      $this->assertEquals( 1, $result->getWinner() );

      $anotherGame = $this->round_->getGame( 3 );
      $result = $anotherGame->getResult();
      $this->assertEquals( 2, $result->getWinner() );
   }

   public function testGetWinnerId()
   {
      $this->assertEquals( 0, $this->instance_->getWinnerId() );

      $anotherGame = $this->round_->getGame( 2 );
      $result = $anotherGame->getResult();
      $this->assertEquals( 3, $result->getWinnerId() );

      $anotherGame = $this->round_->getGame( 3 );
      $result = $anotherGame->getResult();
      $this->assertEquals( 6, $result->getWinnerId() );
   }
}
?>