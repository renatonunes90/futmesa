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
}
?>