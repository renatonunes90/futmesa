<?php
/**
 * Projeto PHPSample
 *
 * Ponto de entrada do PHP. Arquivo para tratar as requisições de serviços.
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use Database\ODBCConnection;

// grava hora para benchmark no caso de debug
$debugtime = microtime( true );

// Adicionando o caminho do PEAR relativo ao PHP usado.
if ( stripos( php_uname( "s" ), "Linux" ) !== false )
{
    set_include_path( get_include_path() . ":" . substr( PHP_BINARY, 0, strrpos( PHP_BINARY, "\\" ) ) . "/pear" );
}
else
{
    set_include_path( get_include_path() . ";" . substr( PHP_BINARY, 0, strrpos( PHP_BINARY, "\\" ) ) . "\pear" );
}

// Inicia a sessão para termos dados.
session_start();

require_once "common/util.php";

getHeader();

debugInterfaceMessage( "Inclusão de cabeçalhos" );

require_once "dblib/dblib.php";

debugInterfaceMessage( "Inclusão de scripts da DBLib" );

require_once "base/includes.php";

debugInterfaceMessage( "Conexão com o banco e inclusão de scripts padrões" );

// recebe domínio e serviço a ser executado
$module = request( "module" );
$service = "Service" . request( "service" );
$function = request( "function" );

// Se o domínio não for de autenticação, verifica se o usuário tem acesso.
// if( $service != "serviceauth" )
// {
// require_once "modules/basemodule/services/serviceauth.php";
// global $serviceAuth;
// $serviceAuth = new ServiceAuth();
// if( !$serviceAuth->checkAuth() )
// {
// serverError( "Usuário não autenticado." );
// }

// // Fecha a sessão para o resto das requisições continuarem.
// session_write_close();
// }

$result = null;

// inclui o script que possui o serviço.
includeService( $module, $service );

// Verifica se existe a classe do serviço.
if( !class_exists( $service ) )
{
   $result = json_encode( RequestRecord::createError( "Domínio '$service' não encontrado." ) );
}
// Verifica se existe o método no serviço.
else if( !method_exists( $service, $function ) )
{
   $result = json_encode( RequestRecord::createError( "Função '$function' não encontrado no domínio '$service'." ) );
}
else
{
   // Se tiver timeout a requisição, altera o limite do php.
   $timeout = request( "timeout", false );
   if( $timeout != null )
   {
      set_time_limit( intval( $timeout ) );
   }

   // busca os parâmetros da função
   $func = new ReflectionMethod( $service, $function );
   $params = array ();
   foreach( $func->getParameters() as $param )
   {
      $params[] = request( $param->name, $param->isOptional() );
   }

   // Executa serviço e pega o resultado.
   $executor = new $service();
   $records = $func->invokeArgs( $executor, $params );

   // Verifica se não é download de arquivo, pois aí não retorna em JSON.
   if( isset( $_REQUEST[ "download" ] ) )
   {
      $result = $records;
   }
   else
   {
      $result = json_encode( RequestRecord::createRecords( $records ) );
   }
}

// Formata legal os resultados se for requisição de debug.
if( isset( $_REQUEST[ "debug" ] ) )
{
   if( isset( $_REQUEST[ "download" ] ) )
   {
      $result = print_r( $result, true );
   }
   else
   {
      $result = print_r( json_decode( $result ), true );
   }
   echo "<pre>$result</pre>";
}
else
{
   echo $result;
}

// Se deu tudo certo, salva as alterações do banco de dados.
ODBCConnection::getConnection()->commit();

debugInterfaceMessage( "Total da requisição" );
?>