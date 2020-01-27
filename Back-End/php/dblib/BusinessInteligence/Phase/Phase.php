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

require_once "BusinessInteligence/Groups/Groups.php";
require_once "DataAccessObjects/Groups/DaoGroupsFactory.php";
require_once "DataAccessObjects/Round/DaoRoundFactory.php";
require_once "ValueObjects/Groups.php";
require_once "ValueObjects/Round.php";

/**
 */
class Phase
{

    /**
     *
     * @var \ValueObject\Phase
     */
    private $phaseVO_;

    /**
     *
     * @var Array Map with groups of phase indexed by your number.
     */
    private $groups_;
    
    /**
     *
     * @var Array Map with rounds of phase indexed by your number.
     */
    private $rounds_;

    /**
     *
     * @param \ValueObject\Phase $phaseVO
     */
    public function __construct(\ValueObject\Phase $phaseVO)
    {
        $this->phaseVO_ = $phaseVO;
        $this->rounds_ = array();
        $this->groups_ = array();
    }

    /**
     */
    public function __clone()
    {
        $this->phaseVO_ = clone $this->getPhaseVO();

        $newRounds = array();
        foreach ($this->rounds_ as $r) {
            $newRounds[] = clone $r;
        }
        $this->rounds_ = $newRounds;
        
        $newGroups = array();
        foreach ($this->groups_ as $g) {
            $newGroups[] = clone $g;
        }
        $this->groups_ = $newGroups;
    }

    /**
     *
     * @return \ValueObject\Round Phase database object.
     */
    public function getPhaseVO(): \ValueObject\Phase
    {
        return $this->phaseVO_;
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
     * @return array
     */
    public function getGroups(): array
    {
        $this->loadGroups();
        return $this->groups_;
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
    
    /**
     *
     * @param array $groups
     */
    public function setGroups(array $groups): void
    {
        $this->groups_ = $groups;
    }

    private function isGroupsPhase() 
    {
        return $this->phaseVO_->type === 2;    
    }
    
    private function loadRounds(bool $forceReload = false): void
    {
        if ( !$this->isGroupsPhase() && (sizeOf( $this->rounds_ ) == 0 || $forceReload ))
        {
            $daoGames = DaoGameFactory::getDaoGame();
            $allGames = $daoGames->getAllGames( $this->phaseVO_->idchampionship );
            $gamesByRound = array ();
            foreach ( $allGames as $g )
            {
                if ( !array_key_exists( $g->idround, $gamesByRound ) )
                {
                    $gamesByRound[ $g->idround ] = array ();
                }
                
                $game = new Game( $g );
                $gamesByRound[ $g->idround ][ $g->id ] = $game;
            }
            
            $daoRound = DaoRoundFactory::getDaoRound();
            $rounds = $daoRound->getRoundsByPhase( $this->phaseVO_->id );
            foreach ( $rounds as $r )
            {
                $this->rounds_[ $r->number ] = new Round( $r );
                if ( isset( $gamesByRound[ $r->id ] ) )
                {
                    $this->rounds_[ $r->number ]->setGames( $gamesByRound[ $r->id ] );
                }
            }
        }
    }
    
    private function loadGroups(bool $forceReload = false): void
    {
        if ( sizeOf( $this->groups_ ) == 0 || $forceReload )
        {
            $daoGroups = DaoGroupsFactory::getDaoGroups();
            $groups = $daoGroups->getAllGroups( $this->phaseVO_->idchampionship, $this->phaseVO_->id );
            foreach ( $groups as $g )
            {
                $this->groups_[ $g->id ] = new Groups( $g );
            }
        }
    }
}

