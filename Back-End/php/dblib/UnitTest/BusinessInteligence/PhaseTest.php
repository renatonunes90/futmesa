<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;

require_once "BusinessInteligence\Phase\Phase.php";
require_once "Providers\ChampionshipProvider.php";

/**
 * Testes unitários para a classe ChampionshipManager.
 */
class PhaseTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\Phase
    */
   private $instance_;
   
   private $championship_;

   public function setUp()
   {
       $this->championship_ = \DBLib\ChampionshipProvider::getInstance()->getChampionship( 1 );
       $this->instance_ = $this->championship_->getPhase(1);
   }

   public function testInsertResult()
   {
      $this->instance_->insertResult( 13, 3, 0 );

      $game = $this->instance_->getRound( 4 )->getGame( 13 );
      $this->assertEquals( 1, $game->getWinner() );

      $this->championship_->refresh();
      $game = $this->instance_->getRound( 4 )->getGame( 13 );
      $this->assertEquals( 1, $game->getWinner() );
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