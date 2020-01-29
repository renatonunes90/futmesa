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
require_once "BusinessInteligence/Classification/Classification.php";
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
   
   /**
    * Calcula a classificação do campeonato em uma dada rodada.
    *
    * @param int $roundNumber
    * @return array Lista ordenada do melhor ao pior classificado no campeonato, com suas estatísticas.
    */
   public function getClassification( int $phaseNumber, int $roundNumber = 0 ): array
   {
      $classifications = array ();

      $phase = $this->getPhase($phaseNumber);
      $players = $this->getPlayers();
      foreach ( $players as $p )
      {
          $classifications[] = $this->evaluateClassification( $p->getPlayerVO()->id, $phase, $roundNumber );
      }

      usort( $classifications,
               function ( $a, $b )
               {
                  if ( $a->getPoints() != $b->getPoints() )
                  {
                     return $b->getPoints() - $a->getPoints();
                  }
                  else
                  {
                     if ( $a->getWins() != $b->getWins() )
                     {
                        return $b->getWins() - $a->getWins();
                     }
                     else
                     {
                        if ( $a->getGoalDIfference() != $b->getGoalDIfference() )
                        {
                           return $b->getGoalDIfference() - $a->getGoalDIfference();
                        }
                        else
                        {
                           return $b->getWinRate() - $a->getWinRate();
                        }
                     }
                  }
               } );

      // atualiza posição de cada jogador
      for ( $i=0; $i < sizeOf( $classifications ); $i++ )
      {
         $classifications[$i]->setPosition( $i + 1 );
      }
      
      return $classifications;
   }

   private function evaluateClassification( int $playerId, Phase $phase, int $roundNumber ): Classification
   {
       $games = $this->getGamesOfPlayer( $playerId, $phase, $roundNumber );
      $classification = new Classification( $playerId, $roundNumber );

      $gameStatus = array();
      for ( $i=0; $i < sizeOf($games); $i++ )
      {
         $g = $games[$i];
         
         // verifica vitória/empate/derrota
         if ( $g->getWinnerId() == $playerId )
         {
            $classification->addWin();
            $gameStatus[]  = "V";
         }
         else if ( $g->getWinnerId() == 0 )
         {
            $classification->addTie();
            $gameStatus[]  = "E";
         }
         else
         {
            $classification->addLoss();
            $gameStatus[]  = "D";
         }

         // verifica saldo
         if ( $g->getPlayer1()->getPlayerVO()->id == $playerId )
         {
            $classification->addGoalsPro( $g->getScore1() );
            $classification->addGoalsCon( $g->getScore2() );
         }
         else
         {
            $classification->addGoalsPro( $g->getScore2() );
            $classification->addGoalsCon( $g->getScore1() );
         }
      }
      
      // atualiza array com os últimos 5 jogos
      $classification->setLast5Games( array_slice( $gameStatus, -5, 5 ) );

      return $classification;
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

   private function getGamesOfPlayer( int $playerId, Phase $phase, int $roundNumber = 0 ): array
   {
       $roundNumber = $roundNumber == 0 ? $this->getMaxRounds($phase) : $roundNumber;

      $games = array ();
      for ( $i = 1; $i <= $roundNumber; $i++ )
      {
          $round = $phase->getRound( $i );
         $playedGames = $round->getGamesOfPlayer( $playerId );
         foreach ( $playedGames as $pg ) {
             if ( $pg != null && $pg->hasResult() )
             {
                $games[] = $pg;
             }
         }
      }
      return $games;
   }
   
   private function getMaxRounds( Phase $phase ) : int
   {
       return sizeOf( $phase->getRounds() );
   }
}
