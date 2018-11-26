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
 * Tabela de temporadas do banco de dados.
 */
class Season extends ValueObject
{

   /**
    *
    * @var int Identificador da temporada.
    */
   public $id;

   /**
    *
    * @var string Nome da temporada.
    */
   public $name;

   /**
    * Preenche as informações deste objeto a partir de outro do mesmo tipo.
    *
    * @param Season $season
    */
   public function copyData( Season $season ): void
   {
      $this->id = $season->id;
      $this->name = $season->name;
   }
}
