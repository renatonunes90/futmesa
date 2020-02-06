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
 * Groups table
 */
class Groups extends ValueObject
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
    public $idphase;

    /**
     *
     * @var string
     */
    public $name;

    /**
     *
     * @param Groups $group
     */
    public function copyData(Groups $group): void
    {
        $this->id = $group->id;
        $this->idchampionship = $group->idchampionship;
        $this->idphase = $group->idphase;
        $this->name = $group->name;
    }
}
