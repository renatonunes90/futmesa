<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace Database;

// Verifica se o include path ainda não tem o PEAR.
$include_path = get_include_path();
if( strpos( $include_path, "pear" ) === false )
{
   set_include_path( "$include_path;" . substr( PHP_BINARY, 0, strrpos( PHP_BINARY, "\\" ) ) . "\pear" );
}

require_once "DB.php";

/**
 * Classe para fazer a conexão e consultas ao banco de dados.
 */
class Database
{

   /**
    * Conexão com o banco de dados.
    *
    * @var \DB
    */
   private $db_;

   /**
    * Flag para determinar se é para mostrar mensagens de desenvolvimento.
    *
    * @var bool
    */
   private $debug_;

   /**
    * Última mensagem de erro.
    *
    * @var string
    */
   private $lastError_;

   /**
    * Construtor padrão.
    *
    * @param string $database
    *           Nome do banco de dados.
    * @param bool $debug
    *           Indica se vai mostrar mensagens de desenvolvimento a cada consulta.
    */
   public function __construct( string $database, bool $debug = false)
   {
      $this->debug_ = $debug || ( isset( $_REQUEST[ "debug" ] ) && $_REQUEST[ "debug" ] );
      $dsn = array ( "phptype" => "ibase" ,"username" => "sysdba" ,"password" => "masterkey" ,"hostspec" => "localhost" ,"database" => $database );
      
      $this->db_ = \DB::connect( $dsn );
      if( \PEAR::isError( $this->db_ ) )
      {
         $this->debugMessages( array ( "Standard Message: " . $this->db_->getMessage() . "\n" ,"Standard Code: " . $this->db_->getCode() . "\n" ,"DBMS/User Message: " . $this->db_->getUserInfo() . "\n" ,"DBMS/Debug Message: " . $this->db_->getDebugInfo() . "\n" ) );
         $this->lastError_ = "Falha na conexão com o banco de dados. Verifique a configuração do seu ODBC: " . $this->db_->getMessage();
      }
      else
      {
         $this->db_->autoCommit( false );
         $this->db_->setFetchMode( DB_FETCHMODE_ASSOC );
      }
   }

   /**
    * Mostra mensagens com timestamp se o banco tiver em debug.
    *
    * @param array $messages
    *           Lista de mensagens a mostrar.
    */
   private function debugMessages( array $messages ): void
   {
      if( $this->debug_ && count( $messages ) > 0 && strlen( $messages[ 0 ] ) > 0 )
      {
         $this->debugTime();
         echo "<pre><code>";
         echo implode( "\n", $messages );
         echo "</code></pre>";
      }
   }

   /**
    * Mostra o tempo passado desde o início da requisição.
    */
   private function debugTime(): void
   {
      if( $this->debug_ )
      {
         $elapsed = number_format( microtime( true ) - $GLOBALS[ "debugtime" ], 3 );
         echo "<pre>$elapsed seconds</pre>";
      }
   }

   /**
    * Prepara e executa um SQL com múltiplos campos.
    *
    * @param string $sql
    *           Consulta SQL preparada para executar.
    * @param array $values
    *           Array de valores por consulta que será realizada.
    * @return bool Flag indicando se as consultas finalizaram com sucesso.
    */
   public function executeMultiplePrepared( string $sql, array $values ): bool
   {
      $result = true;

      $this->debugMessages( array ( $sql ,count( $values ) . " instâncias" ) );
      $this->lastError_ = "";

      // Prepara a consulta.
      $prepared = ibase_prepare( $this->db_->connection, $sql );
      if( $prepared === false )
      {
         $this->lastError_ = "Erro preparando SQL:\n$sql";
      }
      else
      {
         foreach( $values as $v )
         {
            // workaround para passar o parâmetros como array para o execute
            array_unshift( $v, $prepared );
            $rc = call_user_func_array('ibase_execute', $v );
            if( !$rc ) 
            {
               $this->lastError_ = "Erro com dados:\n" . implode( ",", $v );
               $result = false;
               break;
            }
         }
      }

      $this->hasError() ? $this->debugMessages( array ( $this->lastError_ ) ) : $this->debugTime();

      return $result;
   }

   /**
    * Retorna a última mensagem de erro.
    *
    * @return string
    */
   public function getLastError(): string
   {
      return $this->lastError_;
   }

   /**
    * Verifica se houve erro na consulta.
    *
    * @return bool
    */
   public function hasError(): bool
   {
      return strlen( $this->lastError_ ) > 0;
   }

   /**
    * Realiza uma consulta com tamanho de registro limitado no banco e verifica se ocorreu erro.
    *
    * @param
    *           string sql Consulta a ser realizada.
    * @param
    *           offset Registro inicial de retorno.
    * @param
    *           limit Limite de registros retornados.
    */
   public function limitQuery( string $sql, $offset, $limit ): array
   {
      $return = array ();
      $this->lastError_ = "";

      $this->debugMessages( array ( $sql ) );

      $result = $this->db_->limitQuery( $sql, $offset, $limit );
      if( \PEAR::isError( $result ) )
      {
         $this->lastError_ = $result->getMessage() . " " . $sql;
      }
      else if( $result instanceof \DB_result )
      {
         // converte para array
         $r = array ();
         while( $result->fetchInto( $row ) )
         {
            // converte para minúsculo os nome dos campos
            // para ficar igual ao print de objetos
            $newrow = array ();
            foreach( $row as $key => $value )
            {
               $newrow[ strtolower( $key ) ] = $value;
            }

            $r[] = $newrow;
         }

         $result->free();
         $return = $r;
      }

      $this->hasError() ? $this->debugMessages( array ( $this->lastError_ ) ) : $this->debugTime();

      return $return;
   }

   /**
    * Realiza uma consulta no banco e verifica se ocorreu erro.
    *
    * @param string $sql
    *           Consulta a ser realizada.
    */
   public function query( string $sql )
   {
      $this->debugMessages( array ( $sql ) );
      $this->lastError_ = "";
      $return = null;

      if( strlen( $sql ) < 65500 )
      {
         $result = $this->db_->query( $sql );

         if( \PEAR::isError( $result ) )
         {
            $this->lastError_ = $result->getMessage() . " " . $sql;
         }
         else
         {
            // caso de SELECT
            if( $result instanceof \DB_result )
            {
               // converte para array
               $r = array ();
               while( $result->fetchInto( $row ) )
               {
                  // converte para minúsculo os nome dos campos
                  // para ficar igual ao print de objetos
                  $newrow = array ();
                  foreach( $row as $key => $value )
                  {
                     $newrow[ strtolower( $key ) ] = $value;
                  }

                  $r[] = $newrow;
               }

               $result->free();
               $return = $r;
            }
            // caso INSERT, UPDATE, DELETE
            else
            {
               // caso UPDATE o resultado é o número de linhas afetadas.
               $return = strpos( $sql, "UPDATE" ) === 0 || strpos( $sql, "DELETE" ) === 0 ? $result : $this->db_->affectedRows();
            }
         }
      }
      else
      {
         $this->lastError_ = "Erro executando query: consulta muito longa (>64Kb).";
      }

      return $return;
   }

   /**
    * Realiza uma consulta no banco sem tratar os nomes das colunas e verifica se ocorreu erro.
    *
    * @param string $sql
    *           Consulta a ser realizada.
    */
   public function selectAll( string $sql, array $prepareFields = array()): array
   {
      $this->debugMessages( array ( $sql ) );
      $this->lastError_ = "";

      $return = array ();
      if( strlen( $sql ) < 65500 )
      {
         $results = $this->db_->getAll( $sql, $prepareFields, DB_FETCHMODE_ASSOC );

         if( $results instanceof \DB_Error )
         {
            $this->lastError_ = $results->getMessage() . " " . $sql;
         }
         else
         {
            $return = $results;
         }
      }
      else
      {
         $this->lastError_ = "Erro executando query: consulta muito longa (>64Kb).";
      }

      $this->hasError() ? $this->debugMessages( array ( $this->lastError_ ) ) : $this->debugTime();

      return $return;
   }

   /**
    * Envia todas as alterações no banco de dados.
    */
   public function commit(): void
   {
      $this->db_->commit();
   }

   /**
    * Desfaz todas as alterações no banco de dados.
    */
   public function rollback(): void
   {
      $this->db_->rollback();
   }
}

?>