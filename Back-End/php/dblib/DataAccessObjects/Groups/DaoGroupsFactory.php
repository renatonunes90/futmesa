<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\ODBCConnection;
require_once "Database/ODBCConnection.php";

/**
 * Factory used to get groups DAO instance according the context.
 */
class DaoGroupsFactory
{

    /**
     *
     * @var DaoGroupsInterface DAO loaded.
     */
    private static $loadedDao_;

    /**
     * Should not be used.
     */
    private function __construct()
    {}

    /**
     *
     * @return DaoGroupsInterface Groups DAO.
     */
    public static function getDaoGroups(): DaoGroupsInterface
    {
        if (self::$loadedDao_ === null) {
            require_once "DataAccessObjects/Groups/DaoGroupsInterface.php";
            if (getenv("UnitTest") != "1") {
                require_once "DataAccessObjects/Groups/Database/DaoGroups.php";
                self::$loadedDao_ = new DaoGroups(ODBCConnection::getConnection());
            } else {
                require_once "DataAccessObjects/Groups/Mock/DaoTesGroups.php";
                self::$loadedDao_ = new DaoTestGroups();
            }
        }

        return self::$loadedDao_;
    }
}
