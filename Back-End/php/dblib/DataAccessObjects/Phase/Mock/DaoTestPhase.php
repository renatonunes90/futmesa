<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/Round/DaoPhaseInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/Phase.php";

/**
 * DAO of tests.
 */
class DaoTestPhase implements DaoPhaseInterface
{

    const PATH = "DataAccessObjects\\Phase\\Mock\\PHASE.xml";

    /**
     *
     * {@inheritdoc}
     * @see \DAO\DaoPhaseInterface::getAllPhases()
     */
    public function getAllPhases(int $championshipId): array
    {
        $championshipIdphases = array();
        $database = new XMLInterface(self::PATH);
        $result = $database->getFilteredObjects(self::PHASE, array(
            self::IDCHAMPIONSHIP => $championshipId
        ));

        foreach ($result as &$item) {
            $championshipIdphases[$item[self::ID]] = $this->convertToPhase($item);
        }

        return $championshipIdphases;
    }

    /**
     * Converts return of database in an object.
     *
     * @param array $result
     *            Database row of result.
     * @return \ValueObject\Phase
     */
    private function convertToPhase(array $result): \ValueObject\Phase
    {
        $object = new \ValueObject\Phase();
        $object->id = $result[self::ID];
        $object->idchampionship = $result[self::IDCHAMPIONSHIP];
        $object->type = $result[self::TYPE];
        $object->number = $result[self::NUMBER];
        return $object;
    }
}
?>
