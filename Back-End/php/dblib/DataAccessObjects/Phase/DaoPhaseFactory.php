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
 * Factory used to get phase DAO instance according the context.
 */
class DaoPhaseFactory
{

    /**
     *
     * @var DaoPhaseInterface DAO loaded.
     */
    private static $loadedDao_;

    /**
     * Should not be used.
     */
    private function __construct()
    {}

    /**
     *
     * @return DaoPhaseInterface Phase DAO.
     */
    public static function getDaoPhase(): DaoPhaseInterface
    {
        if (self::$loadedDao_ === null) {
            require_once "DataAccessObjects/Phase/DaoPhaseInterface.php";
            if (getenv("UnitTest") != "1") {
                require_once "DataAccessObjects/Phase/Database/DaoPhase.php";
                self::$loadedDao_ = new DaoPhase(ODBCConnection::getConnection());
            } else {
                require_once "DataAccessObjects/Phase/Mock/DaoTestPhase.php";
                self::$loadedDao_ = new DaoTestPhase();
            }
        }

        return self::$loadedDao_;
    }
}
