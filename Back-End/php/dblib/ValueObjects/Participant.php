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
 * Tabela de participantes dos campeonatos do banco de dados.
 */
class Participant extends ValueObject
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
}
