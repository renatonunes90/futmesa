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
 * Tabela de jogadores do banco de dados.
 */
class Player extends ValueObject
{

   /**
    *
    * @var int Identificador do jogador.
    */
   public $id;

   /**
    *
    * @var string Nome do jogador.
    */
   public $name;
   
   /**
    * Preenche as informações deste objeto a partir de outro do mesmo tipo.
    *
    * @param Player $player 
    */
   public function copyData( Player $player ) : void
   {
      $this->id = $player->id;
      $this->name = $player->name;
   }
}
