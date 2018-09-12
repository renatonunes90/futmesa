<?php
/**
 * PHP utilizado somente para incluir a DBLib no Path do sistema.
 * Os lugares que forem usar a DBLib devem incluir este script.
 */
set_include_path( get_include_path() . ":./dblib" );

require_once "Providers/PlayerProvider.php";
require_once "Providers/WebUserProvider.php";
