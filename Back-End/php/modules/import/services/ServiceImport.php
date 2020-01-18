<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use DBLib\ChampionshipProvider;
use ValueObject\Game;
use DBLib\PlayerProvider;

class ServiceImport
{

    private $champProvider_;
    private $playerProvider_;

    public function __construct()
    {
        $this->champProvider_ = ChampionshipProvider::getInstance();
        $this->playerProvider_ = PlayerProvider::getInstance();
    }

    public function importGames(int $id, string $filename): bool
    {
        $result = true;

        $championship = $this->champProvider_->getChampionship($id);
        $file = fopen($filename, "r");
        if ($championship !== null && $file !== null) {
            $games = array();
            $i=0;
            while ( ($line = fgets($file)) !== false ) {
                $data = explode(";", $line);
                $round = $championship->getRound($data[0]);
                $player1 = $this->playerProvider_->getPlayerByName($data[1]);
                if ( $player1 === null ) {
                    throwServerError("Jogador {$data[1]} não encontrado!");
                }
                
                $player2 = $this->playerProvider_->getPlayerByName($data[4]);
                if ( $player2 === null ) {
                    throwServerError("Jogador {$data[4]} não encontrado!");
                }
                $game = new Game();
                $game->idround = $round->getRoundVO()->id;
                $game->idplayer1 = $player1->getPlayerVO()->id;
                $game->score1 = $data[2];
                $game->idplayer2 = $player2->getPlayerVO()->id;
                $game->score2 = $data[3];
                $game->gametable = trim($data[5]);
                $game->inputdate = date( "m-d-Y H:i" );
                $game->idwinner = $data[2] == $data[3] ? 0 : ( $data[2] > $data[3] ? $game->idplayer1 : $game->idplayer2 );    
                
                debugInterfaceMessage("imported line " . ++$i);
                $games[]= $game;
            }
            try {
                if (sizeOf($games) > 0) {
                    $championship->insertGames($games);
                }
            } catch (Exception $e) {
                $result = false;
                throwServerError($e->getMessage());
            }
            
        }

        return $result;
    }
}

?>