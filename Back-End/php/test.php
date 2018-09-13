<?php
ob_start();

require_once "common/util.php";
require_once "base/includes.php";

echo "Hello world!";

echo "<br><br>";
echo "Teste inclusão DBLib:";

require_once "dblib/dblib.php";
echo "<br>incluiu";

echo "<br><br>Teste inclusão PHPUnit:<br>";
echo get_include_path();
set_include_path( get_include_path() . ":/usr/local/bin" );

use PHPUnit\TextUI\PHPUnit_TextUI_Command;

echo "<br>incluiu";
echo "<br><br>";

//require "PHPUnit/Autoload.php";
//use PHPUnit\Framework\PHPUnit_TextUI_Command;

//Prevent PHPUnit from outputing anything
// ob_start();
// //exec( "dblib/UnitTest/AllTests.bat" );

//Run PHPUnit and log results to results.xml in junit format
$command = new PHPUnit_TextUI_Command();
$command->run(array('phpunit', '--verbose', '--log-junit', 'results.xml', 'dblib/UnitTest/'),
    true);

$result = ob_get_clean();


// Print the output of PHPUnit wherever you want
print_r($result);

// ob_end_clean();

// $suite = new PHPUnit_Framework_TestSuite();
// $suite->addTestSuite('dblib/UnitTest/');

// // Shunt output of PHPUnit to a variable
// ob_start();
// $runner = new PHPUnit_TextUI_TestRunner();
// $runner->doRun($suite, [], false);
// $result = ob_get_clean();

// // Print the output of PHPUnit wherever you want
// print_r($result);

ob_end_clean();
?>