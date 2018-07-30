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
 * Tabela de histórico de campeonatos do banco de dados.
 */
class ChampionshipHistoric extends ValueObject
{

   /**
    *
    * @var int Identificador do campeonato.
    */
   public $idchampionship;

   /**
    *
    * @var int Identificador da rodada.
    */
   public $idround;

   /**
    *
    * @var int Identificador do jogador.
    */
   public $idplayer;

   /**
    *
    * @var int Posição do jogador na rodada do campeonato.
    */
   public $pos;
}
