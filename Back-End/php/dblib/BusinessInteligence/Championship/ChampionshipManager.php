<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoGameFactory;

require_once "BusinessInteligence/Championship/Championship.php";
require_once "ErrorHandlers/ChampionshipManagerException.php";

/**
 * A responsabilidade desta classe é ter todas funções lógicas de gerenciamento de um campeonato.
 */
class ChampionshipManager extends Championship
{

   /**
    * Insere um novo resultado no banco de dados.
    *
    * @param int $gameId
    *           Identificador do jogo.
    * @param int $score1
    *           Número de gols do jogador 1.
    * @param int $score2
    *           Número de gols do jogador 2.
    * @throws ChampionshipManagerException
    */
   public function insertResult( int $gameId, int $score1, int $score2 ): void
   {
      $game = $this->getGame( $gameId );
      if ( $game != null )
      {
         $game->getGameVO()->score1 = $score1;
         $game->getGameVO()->score2 = $score2;
         $game->getGameVO()->inputdate = date( "m-d-Y H:i" );
         $game->getGameVO()->idwinner = 0;

         if ( $score1 > $score2 )
         {
            $game->getGameVO()->idwinner = $game->getGameVO()->idplayer1;
         }
         else if ( $score1 < $score2 )
         {
            $game->getGameVO()->idwinner = $game->getGameVO()->idplayer2;
         }

         $daoResult = DaoGameFactory::getDaoGame();
         if ( !$daoResult->updateResult( $game->getGameVO() ) )
         {
            throw new ChampionshipManagerException( "Erro inserindo resultado no banco de dados." );
         }
      }
      else
      {
         throw new ChampionshipManagerException( "Tentativa de atribuir o resultado a uma partida inexistente neste campeonato." );
      }
   }
   
   public function insertGames( array $games ): void {
       $daoGame = DaoGameFactory::getDaoGame();
       if ( !$daoGame->insertGames( $games ) )
       {
           throw new ChampionshipManagerException( "Erro inserindo resultado no banco de dados." );
       }
   }
   
   
   private function getGame( int $gameId ): ?\DbLib\Game
   {
      $game = null;
      foreach ( $this->getRounds() as $round )
      {
         if ( $round->getGame( $gameId ) != null )
         {
            $game = $round->getGame( $gameId );
            break;
         }
      }
      return $game;
   }

}
