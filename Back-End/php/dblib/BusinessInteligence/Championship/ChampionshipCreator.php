<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoChampionshipFactory;
use DAO\DaoRoundFactory;
use DAO\DaoGameFactory;

require_once "BusinessInteligence/Game/Game.php";
require_once "BusinessInteligence/Round/Round.php";
require_once "ValueObjects/Game.php";
require_once "ValueObjects/Round.php";

/**
 
 */
class ChampionshipCreator
{

   /**
    *
    * @var Championship Campeonato.
    */
   private $championship_;


   private $playerIds_;

   /**
    * Construtor padrão.
    *
    * @param Championship $championship
    *           Objeto contendo as informações do campeonato no banco de dados.
    */
   public function __construct( Championship $championship )
   {
      $this->championship_ = $championship;

      $this->playerIds_ = array();
      foreach ( $this->championship_->getPlayers() as $p ) {
        $this->playerIds_[] =$p->getPlayerVO()->id;
      }
   }

   public function populateRoundsAndGames() : bool {

        $roundsNumber = sizeOf( $this->championship_->getPlayers() ) - 1;
        $playersNumber = $roundsNumber+1;
        $alreadyAdded = array();
        $rounds = array();

        for ( $i = 1; $i <= $roundsNumber; $i++ ) {
            $playersByRound = array();

            $currentRoundVO = new \ValueObject\Round();
            $currentRoundVO->number = $i;
            $currentRoundVO->idchampionship = $this->championship_->getChampionshipVO()->id;
            
            $currentRound = new Round( $currentRoundVO );

            $games = array();
            for ( $j=0; $j<($roundsNumber/2); $j++ ) {
                $currentGameVO = new \ValueObject\Game();  
                $currentGameVO->idround = $i;
                $currentGameVO->gametable = $j+1;

                $currentGameVO->idplayer1 = $this->getNextAvailablePlayer( $playersByRound );
                $playersByRound[] = $currentGameVO->idplayer1;

                $currentGameVO->idplayer2 = $this->getNextAvailableOpponent( $currentGameVO->idplayer1, $playersByRound, $alreadyAdded );
                $playersByRound[] = $currentGameVO->idplayer2;
 
                $currentGame = new Game( $currentGameVO );
                $games[] = $currentGame;
                if ( !isset( $alreadyAdded[$currentGameVO->idplayer1] ) ) {
                    $alreadyAdded[$currentGameVO->idplayer1] = array();
                }
                $alreadyAdded[$currentGameVO->idplayer1][$currentGameVO->idplayer2] = true;
            }            

            $currentRound->setGames($games);
            $rounds[] = $currentRound;
        }

        $this->championship_->setRounds( $rounds );
      return true;
   }

   private function getNextAvailablePlayer( $playersByRound ) : int {
        $nextAVailable = 0;
        foreach ( $this->playerIds_ as $id ) {
            if ( array_search( $id, $playersByRound ) === false) {
                $nextAvailable = $id;
                break;
            }
        } 
        return $nextAvailable;

   }

   private function getNextAvailableOpponent( $player1, $playersByRound, $alreadyAdded ) : int {

        $nextAvailable = 0;
        foreach ( $this->playerIds_ as $id ) {
            if ( $id !== $player1 && array_search( $id, $playersByRound ) === false ) {
                if ( !isset( $alreadyAdded[$id][$player1] ) && !isset( $alreadyAdded[$player1][$id] ) ) {
                    $nextAvailable = $id;
                    break;
                }

            }
        } 
        if ( $nextAvailable === 0 ) {
            test( "não achou adversário para " . $player1 );
            test( $playersByRound );
            test( $alreadyAdded );
        }
        return $nextAvailable;
   }
}
