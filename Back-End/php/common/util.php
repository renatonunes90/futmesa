<?php

/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

/**
 * Sobrescrita a função strtoupper para converter caracteres
 * com acentos em maiúscula também.
 */
function upper( $a )
{
   return mb_strtoupper( $a );
}

function lower( $a )
{
   return mb_strtolower( $a );
}

/**
 * Retorna a string de header da resposta da requisição.
 */
function getHeader()
{
   header( "Access-Control-Allow-Origin: *" );
   if( request( "download", false ) == "1" )
   {
      // header( "Content-Type: application/octet-stream" );
      if( request( "filename", false ) != null )
      {
         header( "Content-Disposition: attachment; charset=UTF-8; filename=\"" . request( "filename" ) . "\"" );
      }
      else
      {
         header( "Content-Disposition: attachment; charset=UTF-8; filename=\"dados.csv\"" );
      }
   }
   else
   {
      if( request( "image", false ) == "1" )
      {
         header( "Content-Type: image" );
      }
      else
      {
         header( "Content-Type: text/html; charset=UTF-8" );
      }
   }
}

/**
 * Inclui o script que possui a classe do serviço.
 *
 * @param string $serviceName
 */
function includeService( string $moduleName, string $serviceName ): void
{
   // require_once "base/serviceabstract.php";
   if( file_exists( "modules/$moduleName/services/$serviceName.php" ) )
   {
      if ( stripos( php_uname( "s" ), "Linux" ) !== false )
      {
         set_include_path( get_include_path() . ":" . "./modules/$moduleName" );
      }
      else
      {
         set_include_path( get_include_path() . ";" . "modules/$moduleName" );
      }
      
      require_once "modules/$moduleName/services/$serviceName.php";
   }
}

/**
 * Busca por um parâmetro no request html.
 * Caso a flag '$run_trigger_error'
 * em 'true', dispara um erro caso não encontre o parâmetro no $_REQUEST.
 *
 * @param
 *           string param Nome do campo a ser buscado no $_REQUEST
 * @param bool $runTriggerError
 *           Flag para saber se deve disparar erro
 */
function request( string $param, bool $runTriggerError = true): ?string
{
   $value = null;
   if( array_key_exists( $param, $_REQUEST ) )
   {
      $value = $_REQUEST[ $param ];

      // if para corrigir o problema de encode nos forms
      if( ! ( isset( $_SERVER[ "CONTENT_TYPE" ] ) && strpos( $_SERVER[ "CONTENT_TYPE" ], "multipart/form-data" ) === false ) )
      {
         $value = utf8_encode( $value );
      }

      return get_magic_quotes_gpc() ? stripslashes( $value ) : $value;
   }
   else if( $runTriggerError )
   {
      $r = print_r( $_REQUEST, true );
      serverError( "Parâmetro '$param' não encontrado na requisição.<br> \$_REQUEST = $r<br>", true );
   }
   else
   {
      return $value;
   }
}

/**
 * Verifica se a variável possui um valor numérico válido.
 *
 * @param number $number
 * @return boolean
 */
function isValidNumber( $number ): bool
{
   return ! ( ! is_numeric( $number ) || is_nan( $number ) || is_infinite( $number ) || abs( $number ) >= 1e300 );
}

/**
 * Dispara um erro no php, interrompendo o processamento e retornando o erro.
 *
 * @param string $errorMessage
 *           Mensagem de erro.
 * @param boolean $logError
 *           Flag indicando se o erro deve ser salvo no log (true) ou mostrado ao usuário (false).
 */
function serverError( $errorMessage, $logError = false): void
{
   // loga o erro no arquivo e força mostrar o erro padrão para o usuário
   if( $logError )
   {
      trigger_error( $errorMessage, E_USER_ERROR );
   }
   // ou retorna o erro para o usuário
   else
   {
      trigger_error( $errorMessage );
   }
}

/**
 * Verifica se a string está codificada em UTF-8
 *
 * @param string $string
 *           a ser verificada
 * @return bool true se está codificada em UTF-8, false se não.
 */
function isUTF8( $string ): bool
{
   return preg_match( '%(?:
        [\xC2-\xDF][\x80-\xBF]        # non-overlong 2-byte
        |\xE0[\xA0-\xBF][\x80-\xBF]               # excluding overlongs
        |[\xE1-\xEC\xEE\xEF][\x80-\xBF]{2}      # straight 3-byte
        |\xED[\x80-\x9F][\x80-\xBF]               # excluding surrogates
        |\xF0[\x90-\xBF][\x80-\xBF]{2}    # planes 1-3
        |[\xF1-\xF3][\x80-\xBF]{3}                  # planes 4-15
        |\xF4[\x80-\x8F][\x80-\xBF]{2}    # plane 16
        )+%xs', $string ) == 1;
}

/**
 * Cria um mapa de objetos a partir de um array de objetos e a propriedade dos objetos que deve ser a chave.
 *
 * @param array $array
 *           Array dos objetos que o mapa possuirá.
 * @param string $property
 *           Nome da propriedade dos objetos que será a chave do mapa.
 *
 * @return array <string, unknown> Mapa dos objetos.
 */
function createMapByProperty( array $array, string $property ): array
{
   $return = array ();

   // executa todas as verificações necessárias para criar o mapa
   if( count( $array ) > 0 && isset( reset( $array )->$property ) )
   {
      foreach( $array as &$obj )
      {
         $return[ $obj->$property ] = $obj;
      }
   }

   return $return;
}

/**
 * Imprime no console a mensagem passada como parâmetro quando a flag de debug estiver setada.
 *
 * @param string $message
 *           Mensagem para o log.
 */
function debugInterfaceMessage( string $message )
{
   if( isset( $_REQUEST[ "debug" ] ) )
   {
      echo "$message : " . number_format( microtime( true ) - $GLOBALS[ 'debugtime' ], 3 ) . " seconds<br><br>";
   }
}

?>