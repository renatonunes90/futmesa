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
class ConnectionInfoDeploy extends ConnectionInfo
{

   public function __construct() 
   {
      $this->username = "keko";
      $this->pass = "mesa6277";
      $this->host = "firebird04-farm60.kinghost.net";
      $this->database = "/firebird/keko.gdb";
   }

}

?>