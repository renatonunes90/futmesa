<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use PHPUnit\Framework\TestCase;
use DBLib\EvalClassification;

require_once "BusinessInteligence\Classification\Classification.php";
require_once "BusinessInteligence\Classification\EvalClassification.php";
require_once "Providers\ChampionshipProvider.php";

/**
 * Testes unitários para a classe EvalClassification.
 */
class EvalClassificationTest extends TestCase
{

   /**
    * Instância do objeto testado.
    *
    * @var \DBLib\ChampionshipManager
    */
   private $instance_;
   
   private $championship_;

   public function setUp()
   {
      $this->instance_ = new EvalClassification();
      $this->championship_ = \DBLib\ChampionshipProvider::getInstance()->getChampionship( 1 );
   }

   public function testGetClassification()
   {
       $roundsToQualify = [];
       $roundsToQualify[] = $this->championship_->getPhase(1)->getRound(1);
       $roundsToQualify[] = $this->championship_->getPhase(1)->getRound(2);
       $roundsToQualify[] = $this->championship_->getPhase(1)->getRound(3);
       
       $classification = $this->instance_->getClassification( $roundsToQualify, $this->championship_->getPlayers() );

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