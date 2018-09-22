<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "BusinessInteligence\Championship\ChampionshipManager.php";
require_once "Providers\ChampionshipProvider.php";

/**
 * Testes unitários para a classe ChampionshipManager.
 */
class ChampionshipManagerTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\ChampionshipManager
    */
   private $instance_;

   public function setUp()
   {
      $this->instance_ = \DBLib\ChampionshipProvider::getInstance()->getChampionship( 1 );
   }

   public function testInsertResult()
   {
      $this->instance_->insertResult( 13, 3, 0 );

      $game = $this->instance_->getRound( 4 )->getGame( 13 );
      $this->assertInstanceOf( "\DbLib\Result", $game->getResult() );
      $this->assertEquals( 1, $game->getResult()->getWinner() );

      $this->instance_->refresh();
      $game = $this->instance_->getRound( 4 )->getGame( 13 );
      $this->assertInstanceOf( "\DbLib\Result", $game->getResult() );
      $this->assertEquals( 1, $game->getResult()->getWinner() );
   }

   /**
    *
    * @expectedException Exception
    * @expectedExceptionMessage Tentativa de atribuir o resultado a uma partida inexistente neste campeonato.
    */
   public function testInsertResultException()
   {
      $this->instance_->insertResult( 29, 3, 0 );
   }

   public function testGetClassification()
   {
      $classification = $this->instance_->getClassification( 3 );

      $this->assertEquals( "Jogador C", $classification[ 0 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 7, $classification[ 0 ]->getPoints() );

      $this->assertEquals( "Jogador F", $classification[ 1 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 6, $classification[ 1 ]->getPoints() );

      $this->assertEquals( "Jogador H", $classification[ 2 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 6, $classification[ 2 ]->getPoints() );

      $this->assertEquals( "Jogador B", $classification[ 3 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 5, $classification[ 3 ]->getPoints() );

      $this->assertEquals( "Jogador A", $classification[ 4 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 4, $classification[ 4 ]->getPoints() );

      $this->assertEquals( "Jogador G", $classification[ 5 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 4, $classification[ 5 ]->getPoints() );

      $this->assertEquals( "Jogador E", $classification[ 6 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 1, $classification[ 6 ]->getPoints() );

      $this->assertEquals( "Jogador D", $classification[ 7 ]->getPlayer()->getPlayerVO()->name );
      $this->assertEquals( 0, $classification[ 7 ]->getPoints() );
   }
}
?>