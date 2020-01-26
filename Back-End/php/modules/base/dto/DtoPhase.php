<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Phase/Phase.php";
require_once "ValueObjects/Phase.php";

require_once "dto/DtoRound.php";

/**
 */
class DtoPhase extends \ValueObject\Phase
{

    /**
     *
     * @var Array
     */
    public $rounds;

    /**
     *
     * @param \DbLib\Phase $phase
     */
    public function __construct(\DbLib\Phase $phase)
    {
        $this->copyData($phase->getPhaseVO());
        $this->rounds = array();
        $allRounds = $phase->getRounds();
        foreach ($allRounds as $r) {
            $this->rounds[] = new DtoRound($r);
        }
    }

    /**
     */
    public function __clone()
    {
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
    public function getRounds(): array
    {
        return $this->rounds;
    }
}
