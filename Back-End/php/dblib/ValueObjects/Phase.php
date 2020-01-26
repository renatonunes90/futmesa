<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace ValueObject;

require_once "ValueObjects/ValueObject.php";

/**
 * Phase table
 */
class Phase extends ValueObject
{

    /**
     *
     * @var int
     */
    public $id;

    /**
     *
     * @var int
     */
    public $idchampionship;

    /**
     *
     * @var int
     */
    public $type;

    /**
     *
     * @var int Phase number on championship.
     */
    public $number;

    /**
     *
     * @param Phase $phase
     */
    public function copyData(Phase $phase): void
    {
        $this->id = $phase->id;
        $this->idchampionship = $phase->idchampionship;
        $this->type = $phase->type;
        $this->number = $phase->number;
    }
}
