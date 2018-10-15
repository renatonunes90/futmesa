<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
/**
 * Objeto que encapsula um JSON de resposta do servidor.
 */
class RequestRecord
{

   /**
    *
    * @var string Mensagem de erro, se houver.
    */
   public $errMsg;

   /**
    *
    * @var string Dados da requisição, se não houver erro.
    */
   public $records;

   /**
    * Construtor padrão.
    */
   private function __construct()
   {
      $this->errMsg = null;
      $this->records = null;
   }

   /**
    * 
    * @return bool Flag indicando se o record é de erro ou não.
    */
   public function hasError() : bool
   {
      return $this->errMsg !== null;   
   }
   
   /**
    * Define uma mensagem de erro para arequisição e limpa os dados da mesma.
    *
    * @param string $errMsg
    *           Mensagem de erro.
    */
   public function setError( string &$errMsg ): void
   {
      $this->errMsg = $errMsg;
      $this->records = null;
   }

   /**
    * Define os dados da requisição e limpa a mensagem de erro da mesma.
    *
    * @param $records mixed
    *           Dados da requisição.
    */
   public function setRecords( $records ): void
   {
      $this->errMsg = null;
      $this->records = $records;
   }

   /**
    * Cria uma resposta de erro para a requisição.
    *
    * @param string $errMsg
    *           Mensagem de erro.
    */
   public static function createError( string $errMsg ): RequestRecord
   {
      $errorRecord = new RequestRecord();
      $errorRecord->setError( $errMsg );
      return $errorRecord;
   }

   /**
    * Cria uma resposta de dados para a requisição.
    *
    * @param $records mixed
    *           Dados da requisição.
    */
   public static function createRecords( $records ): RequestRecord
   {
      $errorRecord = new RequestRecord();
      $errorRecord->setRecords( $records );
      return $errorRecord;
   }
}
?>