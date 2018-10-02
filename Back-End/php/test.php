<?php
use DBLib\ChampionshipProvider;
use DBLib\PlayerProvider;

// grava hora para benchmark no caso de debug
$debugtime = microtime( true );

require_once "common/util.php";
require_once "base/includes.php";

echo "Hello world!";
echo "<br><br>";
echo "Sistema operacional: ";
echo php_uname( "s" );

echo "<br><br>";
echo "Teste inclusão DBLib:";

require_once "dblib/dblib.php";
echo "<br>incluiu";

echo "<br><br>";
echo "Todos os campeonatos:<BR>";
test( ChampionshipProvider::getInstance()->getAllChampionships() );

echo "<br><br>";
echo "Todos os jogadores:<BR>";
test( PlayerProvider::getInstance()->getAllPlayers() );
?>