<?php
ob_start();

require_once "common/util.php";
require_once "base/includes.php";

echo "Hello world!";

echo "<br><br>";
echo "Teste inclusão DBLib:";

require_once "dblib/dblib.php";
echo "<br>incluiu";

echo "<br><br>";

ob_end_clean();
?>