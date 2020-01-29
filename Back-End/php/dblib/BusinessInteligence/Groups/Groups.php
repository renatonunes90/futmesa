<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoGameFactory;
use DAO\DaoPlayerFactory;
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
     * @var Array Map with members of group indexed by your number.
     */
    private $members_;
    
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
        $this->members_ = array();
        $this->rounds_ = array();
    }

    /**
     */
    public function __clone()
    {
        $this->groupVO_ = clone $this->getGroupsVO();

        $newMembers = array();
        foreach ($this->members_ as $m) {
            $newMembers[] = clone $m;
        }
        $this->members_ = $newMembers;
        
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
     * @return array
     */
    public function getMembers(): array
    {
        $this->loadMembers();
        return $this->members_;
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
     * @param array $members
     */
    public function setMembers(array $members): void
    {
        $this->members_ = $members;
    }
    
    /**
     *
     * @param array $rounds
     */
    public function setRounds(array $rounds): void
    {
        $this->rounds_ = $rounds;
    }

    private function loadMembers(bool $forceReload = false): void
    {
        if (sizeOf($this->members_) == 0 || $forceReload) {
            $daoPlayer = DaoPlayerFactory::getDaoPlayer();
            $players = $daoPlayer->getByGroup($this->groupVO_->id);
            foreach ($players as $p) {
                $this->members_[] = new Player($p);
            }
        }
    }
    
    private function loadRounds(bool $forceReload = false): void
    {
        if (sizeOf($this->rounds_) == 0 || $forceReload) {
            $daoGames = DaoGameFactory::getDaoGame();
            $allGames = $daoGames->getAllGames($this->groupVO_->idchampionship);
            $gamesByRound = array();
            foreach ($allGames as $g) {
                if (! array_key_exists($g->idround, $gamesByRound)) {
                    $gamesByRound[$g->idround] = array();
                }

                $game = new Game($g);
                $gamesByRound[$g->idround][$g->id] = $game;
            }

            $daoRound = DaoRoundFactory::getDaoRound();
            $rounds = $daoRound->getRoundsByGroup($this->groupVO_->id);
            foreach ($rounds as $r) {
                $this->rounds_[$r->number] = new Round($r);
                if (isset($gamesByRound[$r->id])) {
                    $this->rounds_[$r->number]->setGames($gamesByRound[$r->id]);
                }
            }
         }
    }
}

