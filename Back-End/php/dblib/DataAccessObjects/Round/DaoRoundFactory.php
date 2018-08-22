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
 * Factory para retornar a instância do DAO de rodadas conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoRoundFactory
{

   /**
    *
    * @var DaoRoundInterface Instância de DAO carregada para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {}

   /**
    * Retorna um DaoRound.
    *
    * @return DaoRoundInterface DAO de rodada.
    */
   public static function getDaoRound(): DaoRoundInterface
   {
      if ( self::$loadedDao_ === null )
      {
         require_once "DataAccessObjects/Round/DaoRoundInterface.php";
         if ( getenv( "UnitTest" ) != "1" )
         {
            require_once "DataAccessObjects/Round/Database/DaoRound.php";
            self::$loadedDao_ = new DaoRound( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/Round/Mock/DaoTestRound.php";
            self::$loadedDao_ = new DaoTestRound();
         }
      }

      return self::$loadedDao_;
   }
}
