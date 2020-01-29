<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;



/**

 */
class EvalClassification
{
    
    /**
     */
    public function getClassification( array $rounds, array $players ): array
    {
        $classifications = array ();      

        foreach ( $players as $p ) {
            $classifications[] = $this->evaluateClassification( $p->getPlayerVO()->id, $rounds );
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
    
    private function evaluateClassification( int $playerId, array $rounds ): Classification
    {
        $games = $this->getGamesOfPlayer( $playerId, $rounds );
        $classification = new Classification( $playerId );
        
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
    
    private function getGamesOfPlayer( int $playerId, array $rounds ): array
    {
        $games = array ();
        foreach ( $rounds as $round ) {
            $playedGames = $round->getGamesOfPlayer( $playerId );
            foreach ( $playedGames as $pg ) {
                if ( $pg != null && $pg->hasResult() ) {
                    $games[] = $pg;
                }
            }
        }
        return $games;
    }
}
