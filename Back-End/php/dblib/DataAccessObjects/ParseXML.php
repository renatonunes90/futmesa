<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
set_include_path( get_include_path() . ";" . substr( PHP_BINARY, 0, strrpos( PHP_BINARY, "\\" ) ) . "\pear" );

require_once "Database\Database.php";
use Database\Database;

/**
 * Gera o arquivo XML da tabela especificada.
 */
class ParseXML
{

   /**
    *
    * @var DomDocument Documento DOM.
    */
   private $doc_;

   /**
    *
    * @var int Quantidade de linhas para transcrever.
    */
   private $qtd_;

   /**
    *
    * @var DOMElement Elemento de Dataset DOM.
    */
   private $xmlDataset;

   /**
    *
    * @var DOMElement Elemento de tabela DOM.
    */
   private $xmlTable;

   /**
    * Transcreve a tabela especificada para XML, desde que ela tenha ao menos 1 registro.
    *
    * @param string $table
    *           O nome da tabela a ser transcrita.
    * @param int $qtd
    *           [optional] A quantidade de linhas a serem transcritas
    *           ou zero para transcrever todas as linhas, default 5.
    * @param string $order
    *           [optional] Ordena a tabela a partir de um campo. Para gerar uma tabela equivalente,
    *           que precise inserir linhas, deve-se colocar o campo da chave primária em ordem ASC.
    * @param string $db
    *           [optional] O nome do banco de dados da tabela.
    * @return int Retorna 0 se a query não retornou linhas, ou 1 se o arquivo foi criado.
    */
   public function parseToXML( string $table, int $qtd = 5, string $order = "", string $db = "PGA"): int
   {
      $this->qtd_ = $qtd;
      $sql = new Database( $db, true );

      $query = ( ( $qtd > 0 ) ? "SELECT FIRST $qtd * FROM $table" : "SELECT * FROM $table" );
      $query .= $order;

      $rows = $sql->selectAll( $query );

      if( !( empty( $rows ) ) )
      {
         $this->writeFile( $rows, $table );
         return 1;
      }
      else
      {
         return 0;
      }
   }

   /**
    * Monta e salva o arquivo XML da tabela informada.
    *
    * @param array $rows
    *           As linhas obtidas no SELECT * da tabela.
    * @param string $table
    *           O nome da tabela.
    */
   private function writeFile( array $rows, string $table ): void
   {
      // configura o print do XML.
      $implementation = new DOMImplementation();
      $dtd = $implementation->createDocumentType( "xml" );

      $this->doc_ = $implementation->createDocument( null, null, $dtd );
      $this->doc_->encoding = "utf-8";
      $this->doc_->formatOutput = true;
      $this->doc_->preserveWhiteSpace = false;

      $this->xmlDataset = $this->doc_->createElement( "dataset" );
      $this->xmlTable = $this->doc_->createElement( "table" );

      // obtém as chaves primárias da tabela.
      $table = ucfirst( strtolower( $table ) );

      require_once "ValueObjects//ValueObject.php";
      require_once "ValueObjects//$table.php";

      $tableInNamespace = "ValueObject\\$table";
      $tableObj = new $tableInNamespace();
      $primary = $tableObj->getPrimaryKey();

      if( count( $primary ) >= 1 )
      {
         $primary = implode( ",", $primary );
      }

      // especifica os atributos da tabela.
      $table = strtoupper( $table );
      $this->xmlTable->setAttribute( "name", $table );
      $this->xmlTable->setAttribute( "primary", strtoupper( $primary ) );

      $this->genBody( $rows );
      $this->xmlDataset->appendChild( $this->xmlTable );
      $this->doc_->appendChild( $this->xmlDataset );

      $this->doc_->save( "$table.xml", LIBXML_NOEMPTYTAG );
   }

   /**
    * Gera o corpo da tabela.
    *
    * @param array $rows
    *           As linhas da tabela original.
    */
   private function genBody( array $rows ): void
   {
      $i = 0;
      $keys = array_keys( $rows[ 0 ] );

      // gera as colunas da tabela.
      foreach( $keys as $col )
      {
         $column = $this->doc_->createElement( "column", $col );
         $this->xmlTable->appendChild( $column );
      }

      // gera as linhas da tabela.
      while( $i < count( $rows ) )
      {
         $line = $this->doc_->createElement( "row" );

         foreach( $rows[ $i ] as $value )
         {
            $valObj = $this->doc_->createElement( "value", $value );
            $line->appendChild( $valObj );
         }

         $this->xmlTable->appendChild( $line );
         $i ++;
      }
   }
}
?>

