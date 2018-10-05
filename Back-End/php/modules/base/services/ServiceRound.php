<?php 
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

use DBLib\ChampionshipProvider;

require_once "dto/DtoRound.php";

class ServiceRound
{
   
   private $provider_;
   
   public function __construct() 
   {
      $this->provider_ = ChampionshipProvider::getInstance();
   }
   
   public function getAllRounds( int $idChampionship ): array
   {
      $result = array();
      
      $championship = $this->provider_->getChampionship( $idChampionship );
      if ( $championship != null )
      {
         $rounds = $championship->getRounds();
         foreach ( $rounds as $r )
         {
            $result[] = $r->getRoundVO();
         }
      }
      
      return $result;
   }
   
   public function getAllRoundsInfo( int $idChampionship ): array
   {    
      $result = array();
      
      $championship = $this->provider_->getChampionship( $idChampionship );
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
   
   public function getRoundByNumber( int $idChampionship, int $roundNumber ): ?ValueObject\Round
   {     
      $result = array();
      
      $championship = $this->provider_->getChampionship( $idChampionship );
      if ( $championship != null )
      {
         $round = $championship->getRound( $roundNumber );
         if ( $round != null )
         {
            $result = $round->getRoundVO();
         }
      }
      
      return $result;
   }
   
   public function getRoundInfoByNumber( int $idChampionship, int $roundNumber  ): ?DtoRound
   {
      $result = null;
      
      $championship = $this->provider_->getChampionship( $idChampionship );
      if ( $championship != null )
      {
         $round = $championship->getRound( $roundNumber );
         if ( $round != null )
         {
            $result = new DtoRound( $round );
         }
      }
      
      return $result;
   }
   
}

?>