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
    * @var int Identificador da temporada do campeonato.
    */
   public $idseason;

   /**
    *
    * @var string Nome do campeonato.
    */
   public $name;

   /**
    *
    * @var int Tipo de campeonato.
    */
   public $type;

   /**
    *
    * @var int Indica se o campeonato já foi finalizado.
    */
   public $isfinished;

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

   /**
    * Preenche as informações deste objeto a partir de outro do mesmo tipo.
    *
    * @param Championship $championship
    */
   public function copyData( Championship $championship ): void
   {
      $this->id = $championship->id;
      $this->idseason = $championship->idseason;
      $this->name = $championship->name;
      $this->type = $championship->type;
      $this->isfinished = $championship->isfinished;
      $this->basedate = $championship->basedate;
      $this->dateincr = $championship->dateincr;
      $this->roundsbyday = $championship->roundsbyday;
      $this->gamesbyround = $championship->gamesbyround;
   }
}
