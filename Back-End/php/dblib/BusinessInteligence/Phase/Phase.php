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
     * @param int $groupId
     * @return \DBLib\Groups|NULL
     */
    public function getGroup(int $groupId): ?\DBLib\Groups
    {
        $group = null;
        $this->loadGroups();

        if (array_key_exists($groupId, $this->groups_)) {
            $group = $this->groups_[$groupId];
        }

        return $group;
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
     * Insere um novo resultado no banco de dados.
     *
     * @param int $gameId
     *            Identificador do jogo.
     * @param int $score1
     *            Número de gols do jogador 1.
     * @param int $score2
     *            Número de gols do jogador 2.
     * @throws ChampionshipManagerException
     */
    public function insertResult(int $gameId, int $score1, int $score2): void
    {
        $game = $this->getGame($gameId);
        if ($game != null) {
            $game->getGameVO()->score1 = $score1;
            $game->getGameVO()->score2 = $score2;
            $game->getGameVO()->inputdate = date("m-d-Y H:i");
            $game->getGameVO()->idwinner = 0;

            if ($score1 > $score2) {
                $game->getGameVO()->idwinner = $game->getGameVO()->idplayer1;
            } else if ($score1 < $score2) {
                $game->getGameVO()->idwinner = $game->getGameVO()->idplayer2;
            }

            $daoResult = DaoGameFactory::getDaoGame();
            if (! $daoResult->updateResult($game->getGameVO())) {
                throw new ChampionshipManagerException("Erro inserindo resultado no banco de dados.");
            }
        } else {
            throw new ChampionshipManagerException("Tentativa de atribuir o resultado a uma partida inexistente neste campeonato.");
        }
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

    private function getGame(int $gameId): ?\DbLib\Game
    {
        $game = null;
        foreach ($this->getRounds() as $round) {
            if ($round->getGame($gameId) != null) {
                $game = $round->getGame($gameId);
                break;
            }
        }
        return $game;
    }

    private function isGroupsPhase()
    {
        return $this->phaseVO_->type === 2;
    }

    private function loadRounds(bool $forceReload = false): void
    {
        if (! $this->isGroupsPhase() && (sizeOf($this->rounds_) == 0 || $forceReload)) {
            $daoGames = DaoGameFactory::getDaoGame();
            $allGames = $daoGames->getAllGames($this->phaseVO_->idchampionship);
            $gamesByRound = array();
            foreach ($allGames as $g) {
                if (! array_key_exists($g->idround, $gamesByRound)) {
                    $gamesByRound[$g->idround] = array();
                }

                $game = new Game($g);
                $gamesByRound[$g->idround][$g->id] = $game;
            }

            $daoRound = DaoRoundFactory::getDaoRound();
            $rounds = $daoRound->getRoundsByPhase($this->phaseVO_->id);
            foreach ($rounds as $r) {
                $this->rounds_[$r->number] = new Round($r);
                if (isset($gamesByRound[$r->id])) {
                    $this->rounds_[$r->number]->setGames($gamesByRound[$r->id]);
                }
            }
        }
    }

    private function loadGroups(bool $forceReload = false): void
    {
        if (sizeOf($this->groups_) == 0 || $forceReload) {
            $daoGroups = DaoGroupsFactory::getDaoGroups();
            $groups = $daoGroups->getAllGroups($this->phaseVO_->idchampionship, $this->phaseVO_->id);
            foreach ($groups as $g) {
                $this->groups_[$g->id] = new Groups($g);
            }
        }
    }
}

