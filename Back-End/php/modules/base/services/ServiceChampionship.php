<?php 
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

use DBLib\ChampionshipProvider;

require_once "dto/DtoChampionship.php";

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
      if ( $championship != null ) {
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
   
   public function getAllRounds( int $id ): array
   {
      $result = null;
      
      $championship = $this->provider_->getChampionship( $id );
      if ( $championship != null )
      {
         $rounds = $championship->getRounds();
         foreach ( $rounds as $r )
         {
            $result[] = new DtoRound( $r );
         }
      }
      
      return $result;
   }
   
   public function insertResults( int $id, string $results ) : bool
   {
      $result = true;
      
      $championship = $this->provider_->getChampionship( $id );
      if ( $championship != null )
      {
         $resultObjects = json_decode( $results );
         foreach ( $resultObjects as $r ) 
         {
            try 
            {
               if ( $r->score1 !== null && $r->score2 !== null )
               {
                  $championship->insertResult( $r->id, $r->score1, $r->score2 );
               } 
            }
            catch ( Exception $e ) 
            {
               $result = false;
               throwServerError( $e->getMessage() );
            }
         }
      }
      
      return $result;
   }
}

?>