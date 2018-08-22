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
 * Factory para retornar a instância do DAO de campeonato conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoChampionshipFactory
{

   /**
    *
    * @var DaoChampionshipInterface Instância de DAO carregada para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {}

   /**
    * Retorna um DaoChampionship.
    *
    * @return DaoChampionshipInterface DAO de Championship.
    */
   public static function getDaoChampionship(): DaoChampionshipInterface
   {
      if ( self::$loadedDao_ === null )
      {
         require_once "DataAccessObjects/Championship/DaoChampionshipInterface.php";
         if ( getenv( "UnitTest" ) != "1" )
         {
            require_once "DataAccessObjects/Championship/Database/DaoChampionship.php";
            self::$loadedDao_ = new DaoChampionship( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/Championship/Mock/DaoTestChampionship.php";
            self::$loadedDao_ = new DaoTestChampionship();
         }
      }

      return self::$loadedDao_;
   }
}
