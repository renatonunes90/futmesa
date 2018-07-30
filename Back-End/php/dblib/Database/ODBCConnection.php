<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace Database;

require_once "Database.php";

/**
 * Classe para encapsular a conexão com o banco de dados e disponibilizar o objeto único para quem precisar.
 */
class ODBCConnection
{

   /**
    * Nome da conexão odbc que será utilizada.
    *
    * @var string
    */
   private static $odbcName_ = "SAMPLE";

   /**
    * Flag para determinar se é para mostrar mensagens de desenvolvimento.
    *
    * @var bool
    */
   private static $debug_ = false;

   /**
    * Conexão com o banco de dados.
    *
    * @var Database
    */
   private static $dbConnection_;

   /**
    * Retorna a conexão com o banco de dados.
    * Se ela ainda não existir, cria ela.
    *
    * @return Database Objeto contendo a conexão com o banco de dados.
    */
   public static function getConnection(): Database
   {
      if( self::$dbConnection_ == null )
      {
         self::$dbConnection_ = new Database( self::$odbcName_, self::$debug_ );
      }

      return self::$dbConnection_;
   }
}

?>