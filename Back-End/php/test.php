<?php

echo "Hello world!";

set_include_path( get_include_path() . ":./dblib" );

require_once "dblib/BusinessInteligence/Player/Player.php";

echo "incluiu";

?>