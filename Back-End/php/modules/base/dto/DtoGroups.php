<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Groups/Groups.php";
require_once "ValueObjects/Groups.php";

require_once "dto/DtoRound.php";

/**
 */
class DtoGroups extends \ValueObject\Groups
{
    
    /**
     *
     * @var Array
     */
    public $members;
    
    /**
     *
     * @var Array
     */
    public $rounds;

    /**
     *
     * @param \DbLib\Groups $groups
     */
    public function __construct(\DbLib\Groups $groups)
    {
        $this->copyData($groups->getGroupsVO());
        $this->members = array();
        $allMembers = $groups->getMembers();
        foreach ($allMembers as $m) {
            $this->members[] = new DtoPlayer($m);
        }
        $this->rounds = array();
        $allRounds = $groups->getRounds();
        foreach ($allRounds as $r) {
            $this->rounds[] = new DtoRound($r);
        }
    }

    /**
     */
    public function __clone()
    {
        $newMembers = array();
        foreach ($this->members as $m) {
            $newMembers[] = clone $m;
        }
        $this->members = $newMembers;
        $newRounds = array();
        foreach ($this->rounds as $r) {
            $newRounds[] = clone $r;
        }
        $this->rounds = $newRounds;
    }

    /**
     *
     * @return array
     */
    public function getMembers(): array
    {
        return $this->members;
    }
    
    /**
     *
     * @return array
     */
    public function getRounds(): array
    {
        return $this->rounds;
    }
}
