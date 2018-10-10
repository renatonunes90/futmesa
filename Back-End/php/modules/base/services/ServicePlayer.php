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
   
   public function getPlayer( int $idPlayer ): ?DtoPlayer
   {     
      $result = array();
      
      $player = $this->provider_->getPlayer( $idPlayer );
      if ( $player != null )
      {
         $result = $player->getPlayerVO();
      }
      
      return $result;
   }
   
}

?>