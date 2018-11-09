<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace Database;

/**
 * Classe para encapsular as configurações de conexão o banco de dados.
 */
class ConnectionInfo
{

   /**
    * Nome do usuário da conexão.
    *
    * @var string
    */
   public $username = "";

   /**
    * Senha do usuário da conexão.
    *
    * @var string
    */
   public $pass = "";

   /**
    * Máquina onde está o banco de dados.
    *
    * @var string
    */
   public $host = "";

   /**
    * Nome do banco na máquina.
    *
    * @var string
    */
   public $database = "";

   /**
    * Cria o objeto com as propriedades da conexão.
    *
    * @param bool $deploy
    *           Flag indicando se devem ser usadas configurações de deploy. Caso negativo serão utilizadas configurações para conectar com o
    *           Docker.
    * @param bool $docker
    *           Flag indicando se o deploy será feito via docker.
    */
   public function __construct( bool $deploy = false, bool $docker = true)
   {
      if ( $deploy )
      {
         $this->username = "keko";
         $this->pass = "mesa6277";
         $this->host = "firebird04-farm60.kinghost.net";
         $this->database = "/firebird/keko.gdb";
      }
      else if ( $docker )
      {
         $this->username = "SYSDBA";
         $this->pass = "15ecd39ea0b46ede3cf5";
         $this->host = "db";
         $this->database = "/firebird/data/FUTMESA.FDB";
      }
      else
      {
         $this->username = "SYSDBA";
         $this->pass = "masterkey";
         $this->host = "localhost";
         $this->database = "D:/Projetos/Futmesa/Database/FUTMESA.FDB";
      }
   }
}

?>