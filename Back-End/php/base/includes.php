<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

/**
 * Arquivo: includes.php
 *
 * Arquivo para includes organizado do projeto.
 */
debugInterfaceMessage( "Antes dos includes" );

// deve ser incluído primeiro para tratar inclusive os erros do connection
require_once "common/requestrecord.php";
require_once "base/errorhandler.php";

require_once "common/test.php";

debugInterfaceMessage( "Após os includes" );
?>