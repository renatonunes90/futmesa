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
 * Tabela de pontos no ranking do banco de dados.
 */
class Ranking extends ValueObject
{

   /**
    *
    * @var int Identificador do campeonato.
    */
   public $idchampionship;

   /**
    *
    * @var int Identificador do jogador.
    */
   public $idplayer;

   /**
    *
    * @var int Número de pontos do jogador no campeonato.
    */
   public $points;
}
