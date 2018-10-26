<?php 
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use DBLib\PlayerProvider;

require_once "dto/DtoPlayer.php";

class ServicePlayer
{
   
   private $provider_;
   
   public function __construct() 
   {
      $this->provider_ = PlayerProvider::getInstance();
   }
   
   public function getAllPlayers(): array
   {
      $result = array();
      
      $players = $this->provider_->getAllPlayers();
      foreach ( $players as $p )
      {
         $result[] = $p->getPlayerVO();
      }
      
      return $result;
   }
   
   public function getPlayer( int $id ): DtoPlayer
   {     
      $result = new DtoPlayer( null );
      
      $player = $this->provider_->getPlayer( $id );
      if ( $player != null )
      {
         $result = new DtoPlayer( $player );
      }
      
      return $result;
   }
   
}

?>