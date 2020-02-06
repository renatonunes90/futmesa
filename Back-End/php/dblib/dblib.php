<?php
/**
 * PHP utilizado somente para incluir a DBLib no Path do sistema.
 * Os lugares que forem usar a DBLib devem incluir este script.
 */
if ( stripos( php_uname( "s" ), "Linux" ) !== false ) 
{
    set_include_path( get_include_path() . ":./dblib" );
}
else
{
    set_include_path( get_include_path() . ";dblib" );
}

require_once "BusinessInteligence/Classification/EvalClassification.php";
require_once "Providers/ChampionshipProvider.php";
require_once "Providers/PlayerProvider.php";
require_once "Providers/WebUserProvider.php";
