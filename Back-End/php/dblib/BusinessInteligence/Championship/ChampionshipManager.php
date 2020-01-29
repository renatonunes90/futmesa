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
   
   public function insertGames( array $games ): void {
       $daoGame = DaoGameFactory::getDaoGame();
       if ( !$daoGame->insertGames( $games ) )
       {
           throw new ChampionshipManagerException( "Erro inserindo resultado no banco de dados." );
       }
   }
}
