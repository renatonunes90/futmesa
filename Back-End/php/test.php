<?php
use DBLib\ChampionshipProvider;

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

echo ChampionshipProvider::getInstance()->getAllChampionships();
?>