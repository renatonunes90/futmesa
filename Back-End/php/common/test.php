<?php

/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

/**
 * Esse arquivo implementa funções para facilitar a criação de testes
 * e exibição dos resultados.
 */

/**
 * Realiza um echo e formata a saída.
 *
 * @param Mixed $obj
 */
function test( $obj )
{
   $r = print_r( $obj, true );
   echo "<pre>$r</pre><br>";

   return $obj;
}

/**
 * Retorna a memória alocada pelo PHP.
 */
function echo_memory_usage()
{
   static $start = null;
   if( $start == null )
      $start = microtime( true );

   $mem_usage = round( memory_get_usage( true ) / 1048576, 2 );
   $mem_peak = round( memory_get_peak_usage( true ) / 1048576, 2 );
   $elapsed = round( microtime( true ) - $start, 2 );
   echo "Memória utilizada: $mem_usage / $mem_peak  MB em $elapsed s<br>";
}

?>