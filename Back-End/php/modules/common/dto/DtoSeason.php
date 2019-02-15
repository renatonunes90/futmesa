<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Season/Season.php";
require_once "ValueObjects/Season.php";

/**
 * A responsabilidade desta classe é encapsular os atributos de uma temporada para retonar em uma requisição HTTP.
 */
class DtoSeason extends \ValueObject\Season
{

   /**
    * Construtor padrão.
    *
    * @param ?\DbLib\Season $season
    *           Objeto retornado da DBLib contendo as informações de uma temporada.
    */
   public function __construct( ?\DbLib\Season $season )
   {
      if ( $season != null ) 
      {
         $this->copyData( $season->getSeasonVO() );
      }
      else 
      {
         $this->id = -1;
         $this->name = "";
      }
   }

}
