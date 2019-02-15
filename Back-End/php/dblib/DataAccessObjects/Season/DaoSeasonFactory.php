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
 * Factory para retornar a instância do DAO de temporada conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoSeasonFactory
{

   /**
    *
    * @var DaoSeasonInterface Instância de DAO carregada para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {}

   /**
    * Retorna um DaoSeason.
    *
    * @return DaoSeasonInterface DAO de Season.
    */
   public static function getDaoSeason(): DaoSeasonInterface
   {
      if ( self::$loadedDao_ === null )
      {
         require_once "DataAccessObjects/Season/DaoSeasonInterface.php";
         if ( getenv( "UnitTest" ) != "1" )
         {
            require_once "DataAccessObjects/Season/Database/DaoSeason.php";
            self::$loadedDao_ = new DaoSeason( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/Season/Mock/DaoTestSeason.php";
            self::$loadedDao_ = new DaoTestSeason();
         }
      }

      return self::$loadedDao_;
   }
}
