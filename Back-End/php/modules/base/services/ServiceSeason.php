<?php 
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use DBLib\SeasonProvider;

require_once "modules/common/dto/DtoSeason.php";
require_once "Providers/SeasonProvider.php";

class ServiceSeason
{
   
   private $provider_;
   
   public function __construct() 
   {
      $this->provider_ = SeasonProvider::getInstance();
   }
   
   public function getAllSeasons(): array
   {
      $result = array();
      
      $seasons = $this->provider_->getAllSeasons();
      foreach ( $seasons as $s )
      {
         $result[] = $s->getSeasonVO();
      }
      
      return $result;
   }
   
}

?>