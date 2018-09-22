<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoGameResultFactory;

require_once "BusinessInteligence\Championship\Championship.php";
require_once "BusinessInteligence\Championship\Classification.php";
require_once "ErrorHandlers\ChampionshipManagerException.php";

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
         $result = new \ValueObject\GameResult();
         $result->idgame = $gameId;
         $result->score1 = $score1;
         $result->score2 = $score2;
         $result->inputdate = date( "m-d-Y H:i" );
         $result->idwinner = 0;

         if ( $score1 > $score2 )
         {
            $result->idwinner = $game->getGameVO()->idplayer1;
         }
         else if ( $score1 < $score2 )
         {
            $result->idwinner = $game->getGameVO()->idplayer2;
         }

         $daoResult = DaoGameResultFactory::getDaoGameResult();
         if ( $daoResult->insertResult( $result ) )
         {
            // atualiza o jogo com o resultado em memória
            $game->setResult( new Result( $result ) );
         }
         else
         {
            throw new ChampionshipManagerException( "Erro inserindo resultado no banco de dados." );
         }
      }
      else
      {
         throw new ChampionshipManagerException( "Tentativa de atribuir o resultado a uma partida inexistente neste campeonato." );
      }
   }

   /**
    * Calcula a classificação do campeonato em uma dada rodada.
    *
    * @param int $roundNumber
    * @return array Lista ordenada do melhor ao pior classificado no campeonato, com suas estatísticas.
    */
   public function getClassification( int $roundNumber ): array
   {
      $classifications = array ();

      $players = $this->getPlayers();
      foreach ( $players as $p )
      {
         $classifications[] = $this->evaluateClassification( $p->getPlayerVO()->id, $roundNumber );
      }

      usort( $classifications,
               function ( $a, $b )
               {
                  if ( $a->getPoints() > $b->getPoints() )
                  {
                     return -1;
                  }
                  else if ( $a->getPoints() < $b->getPoints() )
                  {
                     return 1;
                  }
                  else
                  {
                     if ( $a->getWins() > $b->getWins() )
                     {
                        return -1;
                     }
                     elseif ( $a->getWins() < $b->getWins() )
                     {
                        return 1;
                     }
                     else
                     {
                        if ( $a->getGoalDIfference() > $b->getGoalDIfference() )
                        {
                           return -1;
                        }
                        elseif ( $a->getGoalDIfference() < $b->getGoalDIfference() )
                        {
                           return 1;
                        }
                        else
                        {
                           return 0;
                        }
                     }
                  }
               } );

      return $classifications;
   }

   private function evaluateClassification( int $playerId, int $roundNumber ): Classification
   {
      $games = $this->getGamesOfPlayer( $playerId, $roundNumber );
      $classification = new Classification( $playerId, $roundNumber );

      foreach ( $games as $g )
      {
         $result = $g->getResult();

         // verifica vitória/empate/derrota
         if ( $result->getWinnerId() == $playerId )
         {
            $classification->addWin();
         }
         else if ( $result->getWinnerId() == 0 )
         {
            $classification->addTie();
         }
         else
         {
            $classification->addLoss();
         }

         // verifica saldo
         if ( $g->getPlayer1()->getPlayerVO()->id == $playerId )
         {
            $classification->addGoalsPro( $result->getScore1() );
            $classification->addGoalsCon( $result->getScore2() );
         }
         else
         {
            $classification->addGoalsPro( $result->getScore2() );
            $classification->addGoalsCon( $result->getScore1() );
         }
      }

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

   private function getGamesOfPlayer( int $playerId, int $roundNumber ): array
   {
      $games = array ();
      for ( $i = 1; $i <= $roundNumber; $i++ )
      {
         $round = $this->getRound( $i );
         $game = $round->getGameOfPlayer( $playerId );
         if ( $game != null && $game->getResult() != null )
         {
            $games[] = $game;
         }
      }
      return $games;
   }
}
