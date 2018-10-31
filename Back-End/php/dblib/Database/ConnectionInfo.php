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
}

?>