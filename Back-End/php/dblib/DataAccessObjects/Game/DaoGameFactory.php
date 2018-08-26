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
 * Factory para retornar a instância do DAO de jogos conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoGameFactory
{

   /**
    *
    * @var DaoGameInterface Instância de DAO carregada para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {}

   /**
    * Retorna um DaoGame.
    *
    * @return DaoGameInterface DAO de rodada.
    */
   public static function getDaoGame(): DaoGameInterface
   {
      if ( self::$loadedDao_ === null )
      {
         require_once "DataAccessObjects/Game/DaoGameInterface.php";
         if ( getenv( "UnitTest" ) != "1" )
         {
            require_once "DataAccessObjects/Game/Database/DaoGame.php";
            self::$loadedDao_ = new DaoGame( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/Game/Mock/DaoTestGame.php";
            self::$loadedDao_ = new DaoTestGame();
         }
      }

      return self::$loadedDao_;
   }
}
