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
 * Factory para retornar a instância do DAO de resultados de jogos conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoGameResultFactory
{

   /**
    *
    * @var DaoGameResultInterface Instância de DAO carregada para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {}

   /**
    * Retorna um DaoGameResult.
    *
    * @return DaoGameResultInterface DAO de resultado de jogo.
    */
   public static function getDaoGameResult(): DaoGameResultInterface
   {
      if ( self::$loadedDao_ === null )
      {
         require_once "DataAccessObjects/GameResult/DaoGameResultInterface.php";
         if ( getenv( "UnitTest" ) != "1" )
         {
            require_once "DataAccessObjects/GameResult/Database/DaoGameResult.php";
            self::$loadedDao_ = new DaoGameResult( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/GameResult/Mock/DaoTestGameResult.php";
            self::$loadedDao_ = new DaoTestGameResult();
         }
      }

      return self::$loadedDao_;
   }
}
