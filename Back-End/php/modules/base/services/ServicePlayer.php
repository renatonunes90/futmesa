<?php 
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use DBLib\PlayerProvider;

require_once "dto/DtoPlayer.php";
require_once "dto/DtoReviewInfo.php";

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
   
   public function getReviewInfo( int $id ): array
   {
      $result = array();
      
      $player = $this->provider_->getPlayer( $id );
      if ( $player != null )
      {
         $result[] = new DtoReviewInfo( "Posição no Ranking", "2º" );
         $result[] = new DtoReviewInfo( "Campeonatos Vencidos", "2" );
         $result[] = new DtoReviewInfo( "Melhor Posição", "1º" );
         $result[] = new DtoReviewInfo( "Melhor Temporada", "2018" );
         $result[] = new DtoReviewInfo( "Maior Invencibilidade", "15 jogos" );
         $result[] = new DtoReviewInfo( "Maior Goleada", "6 x 1" );
         $result[] = new DtoReviewInfo( "Pior Derrota", "2 x 4" );
         $result[] = new DtoReviewInfo( "Placar Frequente", "0 x 0" );
         $result[] = new DtoReviewInfo( "Freguês", "Jogador A" );
         $result[] = new DtoReviewInfo( "Carrasco", "Jogador H" );
      }
      
      return $result;
   }
   
   public function getStatisticsInfo( int $id ): array
   {
      $result = array();
      
      $player = $this->provider_->getPlayer( $id );
      if ( $player != null )
      {
         $result[] = new DtoReviewInfo( "Vitórias", "54" );
         $result[] = new DtoReviewInfo( "Empates", "49" );
         $result[] = new DtoReviewInfo( "Derrotas", "45" );
         $result[] = new DtoReviewInfo( "Aproveitamento", "47%" );
         $result[] = new DtoReviewInfo( "Gols Pró", "70" );
         $result[] = new DtoReviewInfo( "Gols Contra", "65" );
         $result[] = new DtoReviewInfo( "Saldo de gols", "5" );
      }
      
      return $result;
   }
   
}

?>