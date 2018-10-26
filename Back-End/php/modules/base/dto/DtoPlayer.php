<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Player/Player.php";
require_once "ValueObjects/Player.php";

/**
 * A responsabilidade desta classe é encapsular os atributos de um jogador para retonar em uma requisição HTTP.
 */
class DtoPlayer extends \ValueObject\Player
{

   /**
    * Construtor padrão.
    *
    * @param ?\DbLib\Championship $player
    *           Objeto retornado da DBLib contendo as informações de um jogador.
    */
   public function __construct( ?\DbLib\Player $player )
   {
      if ( $player != null ) 
      {
         $this->copyData( $player->getPlayerVO() );
      }
      else 
      {
         $this->id = -1;
         $this->name = "";
      }
   }

}
