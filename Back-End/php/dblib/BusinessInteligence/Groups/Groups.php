<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoGameFactory;
use DAO\DaoGroupsFactory;
use DAO\DaoRoundFactory;
require_once "DataAccessObjects/Round/DaoRoundFactory.php";
require_once "ValueObjects/Groups.php";

/**
 */
class Groups
{

    /**
     *
     * @var \ValueObject\Groups
     */
    private $groupVO_;

    
    /**
     *
     * @var Array Map with rounds of group indexed by your number.
     */
    private $rounds_;

    /**
     *
     * @param \ValueObject\Groups $phaseVO
     */
    public function __construct(\ValueObject\Groups $groupsVO)
    {
        $this->groupVO_ = $groupsVO;
        $this->rounds_ = array();
    }

    /**
     */
    public function __clone()
    {
        $this->groupVO_ = clone $this->getGroupsVO();

        $newRounds = array();
        foreach ($this->rounds_ as $r) {
            $newRounds[] = clone $r;
        }
        $this->rounds_ = $newRounds;
    }

    /**
     *
     * @return \ValueObject\Groups Groups database object.
     */
    public function getGroupsVO(): \ValueObject\Groups
    {
        return $this->groupVO_;
    }

    /**
     *
     * @return array
     */
    public function getRounds(): array
    {
        $this->loadRounds();
        return $this->rounds_;
    }
    
    /**
     *
     * @param int $roundNumber
     * @return \DBLib\Round|NULL
     */
    public function getRound(int $roundNumber): ?\DBLib\Round
    {
        $round = null;
        $this->loadRounds();

        if (array_key_exists($roundNumber, $this->rounds_)) {
            $round = $this->rounds_[$roundNumber];
        }

        return $round;
    }
    
    /**
     *
     * @param array $rounds
     */
    public function setRounds(array $rounds): void
    {
        $this->rounds_ = $rounds;
    }

    private function loadRounds(bool $forceReload = false): void
    {
        if ( sizeOf( $this->rounds_ ) == 0 || $forceReload )
        {
//             $daoGames = DaoGameFactory::getDaoGame();
//             $allGames = $daoGames->getAllGames( $this->phaseVO_->idchampionship );
//             $gamesByRound = array ();
//             foreach ( $allGames as $g )
//             {
//                 if ( !array_key_exists( $g->idround, $gamesByRound ) )
//                 {
//                     $gamesByRound[ $g->idround ] = array ();
//                 }
                
//                 $game = new Game( $g );
//                 $gamesByRound[ $g->idround ][ $g->id ] = $game;
//             }
            
//             $daoRound = DaoRoundFactory::getDaoRound();
//             $rounds = $daoRound->getRoundsByPhase( $this->phaseVO_->id );
//             foreach ( $rounds as $r )
//             {
//                 $this->rounds_[ $r->number ] = new Round( $r );
//                 if ( isset( $gamesByRound[ $r->id ] ) )
//                 {
//                     $this->rounds_[ $r->number ]->setGames( $gamesByRound[ $r->id ] );
//                 }
//             }
         }
    }
}

