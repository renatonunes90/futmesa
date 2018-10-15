<?php 
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

use DBLib\ChampionshipProvider;

require_once "dto/DtoChampionship.php";
require_once "dto/DtoClassification.php";

class ServiceChampionship
{
   
   private $provider_;
   
   public function __construct() 
   {
      $this->provider_ = ChampionshipProvider::getInstance();
   }
   
   public function getAllChampionships(): array
   {
      $result = array();
      
      $allChampionships = $this->provider_->getAllChampionships();
      foreach ( $allChampionships as $champ )
      {
         $result[] = $champ->getChampionshipVO();
      }
      
      return $result;
   }
   
   public function getAllChampionshipsInfo(): array
   {
      $result = array();
      
      $allChampionships = $this->provider_->getAllChampionships();
      foreach ( $allChampionships as $champ )
      {
         $result[] = new DtoChampionship( $champ );
      }
      
      return $result;
   }
   
   public function getChampionship( int $id ): ?ValueObject\Championship
   {
      $result = null;
      
      $championship = $this->provider_->getChampionship( $id );
      if ( $championship != null )
      {
         $result = $championship->getChampionshipVO();
      }
      
      return $result;
   }
   
   public function getChampionshipInfo( int $id ): ?DtoChampionship
   {
      $result = null;
      
      $championship = $this->provider_->getChampionship( $id );
      if ( $championship != null )
      {
         $result = new DtoChampionship( $championship );
      }
      
      return $result;
   }
   
   public function getLastClassifications( int $id ): array
   {
      $result = null;
      
      $championship = $this->provider_->getChampionship( $id );
      if ( $championship != null )
      {
         $classification = $championship->getClassification( 3 );
         foreach ( $classification as $c )
         {
            $result[] = new DtoClassification( $c );
         }
      }
      
      return $result;
   }
   
}

?>