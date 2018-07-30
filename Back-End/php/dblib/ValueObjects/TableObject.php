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
 * Tabela do banco de dados.
 */
class TableObject extends ValueObject
{

   /**
    *
    * @var int Identificador do registro.
    */
   public $id;

   /**
    *
    * @var string Nome.
    */
   public $name;

   /**
    *
    * @var string Descrição.
    */
   public $description;
}
