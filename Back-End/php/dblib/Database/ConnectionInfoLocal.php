<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace Database;

require_once "ConnectionInfo.php";

/**
 * Configurações de deploy do banco de dados.
 */
class ConnectionInfoLocal extends ConnectionInfo
{
   
   public function __construct()
   {
      $this->username = "SYSDBA";
      $this->pass = "15ecd39ea0b46ede3cf5";
      $this->host = "db";
      $this->database = "/firebird/data/FUTMESA.FDB";
   }
   
}

?>