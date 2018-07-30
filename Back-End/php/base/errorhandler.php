<?php

/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

/**
 * Captura e gerencia todos os erros de PHP.
 */
function errorHandler( int $errno, string $errstr, string $errfile, int $errline ): bool
{
   $record = null;

   // Imprime detalhes do erro somente se for passada a flag de debug.
   if( isset( $_REQUEST[ "debug" ] ) )
   {
      printError( $errno, $errstr, $errfile, $errline );
   }

   // senão retorna o json com a mensagem de erro
   else
   {
      // erros de programação devem ser logados no arquivo e não mostrados para o usuário
      if( $errno == E_USER_ERROR )
      {
         logFileError( $errstr . " on file '" . basename( $errfile ) . "'($errline)" );
         $errstr = "Erro interno na requisição ao servidor.";
      }

      $record = RequestRecord::createError( $errstr );
   }

   // Termina a requisição automaticamente.
   die( json_encode( $record ) );
}

/**
 * Chamado no final da requisição, trata erros de métodos depreciados.
 */
function fatalHandler(): void
{
   $error = error_get_last();

   // Verifica se o erro não é um erro que é pra ser reportado.
   if( $error !== null && ( ( $error[ "type" ] & error_reporting() ) != 0 ) )
   {
      $fatalErrMsg = "Fatal Error [" . $error[ "type" ] . "]: " . $error[ "message" ];
      $fatalErrMsg .= " on file '" . basename( $error[ "file" ] ) . "'(" . $error[ "line" ] . ")";
      trigger_error( $fatalErrMsg, E_USER_ERROR );
   }
}

/**
 * Imprime as linhas chamadas do PHP até o erro atual.
 */
function printCallStack( array $stacktrace ): void
{
   print str_repeat( "=", 50 ) . "\n\n";
   echo "CallStack:\n";
   $index = 0;
   foreach( $stacktrace as &$node )
   {
      $file = isset( $node[ "file" ] ) ? basename( $node[ "file" ] ) : "";
      $line = isset( $node[ "line" ] ) ? $node[ "line" ] : "";
      // Pula a linha desta função e da printError.
      print "# $file: " . $node[ "function" ] . "($line)\n";
      $index ++;
   }
}

/**
 * Imprime informações do erro na resposta.
 *
 * @param int $errno
 *           Tipo do erro.
 * @param string $errstr
 *           Mensagem do erro.
 */
function printError( int $errno, string $errstr ): void
{
   echo "<pre>Debug PHP " . PHP_VERSION . " (" . PHP_OS . ")\n[Error $errno]: $errstr\n\n";
   printCallStack( debug_backtrace() );
   echo "</pre>";
}

/**
 * Escreve uma mensagem de erro no arquivo de log do PGA.
 *
 * @param string $errMsg
 *           Mensagem de erro.
 */
function logFileError( string $errMsg ): void
{
   $logpath = "sample.log";
   if( isset( $_SERVER[ "REQUEST_URI" ] ) )
   {
      $baseDir = explode( "/", $_SERVER[ "REQUEST_URI" ] );
      if( count( $baseDir ) > 1 )
      {
         $logpath = $baseDir[ 1 ] . ".log";
      }
   }

   $logpath = sys_get_temp_dir() . "\\" . $logpath;

   if( !error_log( date( "Y/m/d H:i:s" ) . " - " . $errMsg . "\n", 3, $logpath ) )
   {
      // não conseguiu escrever no arquivo de log
   }
}

set_error_handler( "errorHandler", error_reporting() );
register_shutdown_function( "fatalHandler" );
if( isset( $_REQUEST[ "debug" ] ) )
{
   ini_set( "error_reporting", E_ALL & ~E_STRICT & ~E_NOTICE & ~E_DEPRECATED );
   ini_set( "display_errors", true );
}
?>