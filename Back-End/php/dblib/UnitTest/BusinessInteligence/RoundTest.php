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

   public function testGetGames()
   {
      $games = $this->instance_->getGames();
      $this->assertCount( 4, $games );
      foreach ( $games as $g )
      {
         $this->assertInstanceOf( "\DbLib\Game", $g );
      }
   }

   public function testGetGame()
   {
      $game = $this->instance_->getGame( 1 );
      $this->assertInstanceOf( "\DbLib\Game", $game );

      $game = $this->instance_->getGame( -1 );
      $this->assertNull( $game );

      $game = $this->instance_->getGame( 5 );
      $this->assertNull( $game );
   }

   public function getGameOfPlayer()
   {
      $game = $this->instance_->getGameOfPlayer( 5 );
      $this->assertInstanceOf( "\DbLib\Game", $game );

      $this->assertEquals( 5, $game->getPlayer1()->getPlayerVO()->id );
      $this->assertEquals( 6, $game->getPlayer1()->getPlayerVO()->id );
   }
}
?>