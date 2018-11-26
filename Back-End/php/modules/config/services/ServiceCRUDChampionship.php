<?php 
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

use DBLib\ChampionshipProvider;

require_once "modules/common/dto/DtoChampionship.php";

class ServiceCRUDChampionship
{
   
   private $provider_;
   
   public function __construct() 
   {
      $this->provider_ = ChampionshipProvider::getInstance();
   }
   
   public function createChampionship( string $championship ): bool
   {
      $obj = json_decode( $championship );
      $champ = new \ValueObject\Championship();
      $champ->idseason = $obj->idseason;
      $champ->name = $obj->name;
      $champ->type = $obj->type;
      $champ->isfinished = $obj->isfinished;
      $champ->basedate = $obj->basedate;
      $champ->dateincr = $obj->dateincr;
      $champ->roundsbyday = $obj->roundsbyday;
      $champ->gamesbyround = $obj->gamesbyround;
      $champs = array( $champ );
      return $this->provider_->createChampionships( $champs ); 
   }
   
   public function deleteChampionship( string $id ): bool
   {
      $champs = array( $id );
      return $this->provider_->deleteChampionships( $champs ); 
   }
}

?>