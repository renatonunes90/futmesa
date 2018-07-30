<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace ValueObject;

require_once "ValueObjects/ValueObject.php";

/**
 * Tabela WebUser do banco de dados.
 */
class WebUser extends ValueObject
{

   /**
    *
    * @var int Identificador do usuário.
    */
   public $id;

   /**
    *
    * @var string Login do usuário.
    */
   public $login;

   /**
    *
    * @var string Nome do usuário.
    */
   public $name;

   /**
    *
    * @var string Senha do usuário (criptografada).
    */
   public $pass;
}
?>