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
 * Factory para retornar a instância do DAO de usuário conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoWebUserFactory
{

   /**
    *
    * @var DaoWebUserInterface Armazena o DAO que foi carregado para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {
   }

   /**
    * Retorna uma DAO de usuário.
    *
    * @return DaoWebUserInterface Instância de WebUser DAO.
    */
   public static function getDaoWebUser(): DaoWebUserInterface
   {
      if( self::$loadedDao_ == null )
      {
         require_once "DataAccessObjects/WebUser/DaoWebUserInterface.php";
         if( ! isset( $_ENV ["UnitTest"] ) )
         {
            require_once "DataAccessObjects/WebUser/Database/DaoWebUser.php";
            self::$loadedDao_ = new DaoWebUser( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/WebUser/Mock/DaoTestWebUser.php";
            self::$loadedDao_ = new DaoTestWebUser();
         }
      }

      return self::$loadedDao_;
   }
}
