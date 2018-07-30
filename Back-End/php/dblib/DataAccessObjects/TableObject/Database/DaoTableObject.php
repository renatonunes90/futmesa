<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\Database;

require_once "DataAccessObjects/TableObject/DaoTableObjectInterface.php";
require_once "ValueObjects/TableObject.php";

/**
 * Objeto para acessar o banco de dados das análises.
 */
class DaoTableObject implements DaoTableObjectInterface
{

   /**
    *
    * @var Database Instância de Database.
    */
   private $db_;

   /**
    * Construtor padrão da classe com instância de database.
    *
    * @param Database $db
    *           Instância de database.
    */
   public function __construct( Database $db )
   {
      $this->db_ = $db;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::getAllAssets()
    */
   public function getAllTableObjects(): array
   {
      $objects = array ();
      $result = $this->db_->selectAll( "SELECT * FROM tableobject" );

      foreach( $result as &$r )
      {
         $objects[] = $this->convertToTableObject( $r );
      }

      return $objects;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::getTableObject()
    */
   public function getTableObject( int $id ): ?\ValueObject\TableObject
   {
      $object = null;
      $result = $this->db_->selectAll( "SELECT * FROM tableobject WHERE id = $id" );

      if( count( $result ) > 0 )
      {
         $object = $this->convertToTableObject( $result[ 0 ] );
      }

      return $object;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::insertTableObjects()
    */
   public function insertTableObjects( array $objects ): bool
   {
      $result = true;

      if( count( $objects ) > 0 )
      {
         $values = array ();
         foreach( $objects as $objVO )
         {
            $val = array_values( ( array ) $objVO );
            array_shift( $val );
            $values[] = $val;
         }

         $query = "INSERT INTO tableobject ( name, description )
                     VALUES ( ?, ? )";
         $result = $this->db_->executeMultiplePrepared( $query, $values );
      }

      return $result;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::updateTableObjects()
    */
   public function updateTableObjects( array $objects ): bool
   {
      $result = true;

      if( count( $objects ) > 0 )
      {
         $values = array ();
         foreach( $objects as $objVO )
         {
            $id = $objVO->id;
            $val = array_values( ( array ) $objVO );

            // passa o id para o final pois é o último argumento da query
            array_shift( $val );
            array_push( $val, $id );

            $values[] = $val;
         }
         $sql = "UPDATE tableobject
                    SET name = ?, description = ?
                  WHERE id = ?";
         $result = $this->db_->executeMultiplePrepared( $sql, $values );
      }

      return $result;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::deleteTableObjects()
    */
   public function deleteTableObjects( array $ids ): bool
   {
      $result = true;

      if( count( $ids ) > 0 )
      {
         $values = array ();
         foreach( $ids as $id )
         {
            $values[] = array ( $id );
         }
         $query = "DELETE FROM tableobject
                         WHERE id = ?";
         $result = $this->db_->executeMultiplePrepared( $query, $values );
      }

      return $result;
   }

   /**
    * Converte o resultado do banco de dados em um objeto do Asset.
    *
    * @param array $result
    *           Mapa de resultados do banco para um objeto.
    * @return \ValueObject\TableObject Objeto de tabela.
    */
   private function convertToTableObject( array $result ): \ValueObject\TableObject
   {
      $object = new \ValueObject\TableObject();
      $object->id = $result[ self::ID ];
      $object->name = $result[ self::NAME ];
      $object->description = $result[ self::DESCRIPTION ];
      return $object;
   }
}

?>