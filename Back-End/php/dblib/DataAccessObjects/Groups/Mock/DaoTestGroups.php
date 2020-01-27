<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/Groups/DaoGroupsInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/Groups.php";

/**
 * DAO of tests.
 */
class DaoTestGroups implements DaoGroupsInterface
{

    const PATH = "DataAccessObjects\\Phase\\Mock\\GROUPS.xml";

    /**
     *
     * {@inheritdoc}
     * @see \DAO\DaoGroupsInterface::getAllGroups()
     */
    public function getAllGroups(int $championshipId, int $phaseId): array
    {
        $groups = array();
        $database = new XMLInterface(self::PATH);
        $result = $database->getFilteredObjects(self::PHASE, array(
            self::IDCHAMPIONSHIP => $championshipId, self::IDPHASE => $phaseId
        ));

        foreach ($result as &$item) {
            $groups[$item[self::ID]] = $this->convertToGroups($item);
        }

        return $groups;
    }

    /**
     * Converts return of database in an object.
     *
     * @param array $result
     *            Database row of result.
     * @return \ValueObject\Groups
     */
    private function convertToGroups(array $result): \ValueObject\Groups
    {
        $object = new \ValueObject\Groups();
        $object->id = $result[self::ID];
        $object->idchampionship = $result[self::IDCHAMPIONSHIP];
        $object->idphase = $result[self::IDPHASE];
        $object->name = $result[self::NAME];
        return $object;
    }
}
?>
