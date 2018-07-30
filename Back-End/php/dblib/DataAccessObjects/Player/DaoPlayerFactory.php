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
 * Factory para retornar a instância do DAO de jogador conforme o contexto.
 * Atualmente pode retornar o objeto que acessa o banco de dados ou o objeto com dados de teste.
 */
class DaoPlayerFactory
{

   /**
    *
    * @var DaoPlayerInterface Instância de DAO carregada para evitar carregamentos desnecessários.
    */
   private static $loadedDao_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {}

   /**
    * Retorna um DaoPlayer.
    *
    * @return DaoPlayerInterface DAO de Player.
    */
   public static function getDaoPlayer(): DaoPlayerInterface
   {
      if( self::$loadedDao_ === null )
      {
         require_once "DataAccessObjects/Player/DaoPlayerInterface.php";
         if( !isset( $_ENV[ "UnitTest" ] ) )
         {
            require_once "DataAccessObjects/Player/Database/DaoPlayer.php";
            self::$loadedDao_ = new DaoPlayer( ODBCConnection::getConnection() );
         }
         else
         {
            require_once "DataAccessObjects/Player/Mock/DaoTestPlayer.php";
            self::$loadedDao_ = new DaoTestPlayer();
         }
      }

      return self::$loadedDao_;
   }
}
