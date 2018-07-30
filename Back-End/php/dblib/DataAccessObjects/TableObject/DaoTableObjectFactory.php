<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\ODBCConnection;

require_once "Database/ODBCConnection.php";

/**
 * Factory para retornar a instância do DAO de objeto de tabela conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoTableObjectFactory
{

   /**
    *
    * @var DaoTableObjectInterface Instância de DAO carregada para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {
   }

   /**
    * Retorna um DaoTableObject.
    *
    * @return DaoTableObjectInterface DAO de TableObject.
    */
   public static function getDaoTableObject(): DaoTableObjectInterface
   {
      if( self::$loadedDao_ == null )
      {
         require_once "DataAccessObjects/TableObject/DaoTableObjectInterface.php";
         if( ! isset( $_ENV[ "UnitTest" ] ) )
         {
            require_once "DataAccessObjects/TableObject/Database/DaoTableObject.php";
            self::$loadedDao_ = new DaoTableObject( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/TableObject/Mock/DaoTestTableObject.php";
            self::$loadedDao_ = new DaoTestTableObject();
         }
      }

      return self::$loadedDao_;
   }
}
