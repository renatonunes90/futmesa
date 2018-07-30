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
 * Tabela de rodades dos campeonatos do banco de dados.
 */
class Round extends ValueObject
{

   /**
    *
    * @var int Identificador da rodada.
    */
   public $id;

   /**
    *
    * @var int Identificador do campeonato.
    */
   public $idchampionship;

   /**
    *
    * @var mixed Data base da rodada.
    */
   public $basedate;

   /**
    *
    * @var mixed Horário base da rodada.
    */
   public $basehour;

   /**
    *
    * @var int Número de da rodada no campeonato.
    */
   public $number;
}
