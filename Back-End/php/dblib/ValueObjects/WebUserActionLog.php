<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace ValueObject;

require_once "ValueObjects/ValueObject.php";

/**
 * Tabela WebUserActionLog do banco de dados.
 */
class WebUserActionLog extends ValueObject
{

   /**
    *
    * @var string timestamp Data da ação.
    */
   public $actiontimestamp;

   /**
    *
    * @var int Identificador do usuário.
    */
   public $idwebuser;

   /**
    *
    * @var int Identificador do log da ação de usuário.
    */
   public $idwebuseractionlog;

   /**
    *
    * @var string Parâmetros.
    */
   public $params;
}
?>