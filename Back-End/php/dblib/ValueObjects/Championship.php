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
 * Tabela de campeonatos do banco de dados.
 */
class Championship extends ValueObject
{

   /**
    *
    * @var int Identificador do campeonato.
    */
   public $id;

   /**
    *
    * @var string Nome do campeonato.
    */
   public $name;

   /**
    *
    * @var mixed Data base dos jogos do campeonato.
    */
   public $basedate;

   /**
    *
    * @var int Dias de incremento de cada data de jogos.
    */
   public $dateincr;

   /**
    *
    * @var int Rodadas por dia de jogo.
    */
   public $roundsbyday;

   /**
    *
    * @var int Jogos por rodada.
    */
   public $gamesbyround;
}
