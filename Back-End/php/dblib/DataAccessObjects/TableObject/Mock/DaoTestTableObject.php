<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/TableObject/DaoTableObjectInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/TableObject.php";

/**
 * Objeto para acessar o banco de dados de testes dos objetos.
 */
class DaoTestTableObject implements DaoTableObjectInterface
{

   const PATH = "DataAccessObjects\\TableObject\\Mock\\TABLEOBJECT.xml";

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::getAllTableObjects()
    */
   public function getAllTableObjects(): array
   {
      $assets = array ();
      $database = new XMLInterface( self::PATH );
      $result = $database->getAllObjects( self::TABLEOBJECT );

      foreach( $result as &$item )
      {
         $assets[] = $this->convertToTableObject( $item );
      }

      return $assets;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::getTableObject()
    */
   public function getTableObject( int $id ): ?\ValueObject\TableObject
   {
      $database = new XMLInterface( self::PATH );
      $result = $database->getObject( self::TABLEOBJECT, $id );

      if( empty( $result ) )
      {
         $asset = null;
      }
      else
      {
         $asset = $this->convertToTableObject( $result );
      }

      return $asset;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::insertTableObjects()
    */
   public function insertTableObjects( array $objects ): bool
   {
      $database = new XMLInterface( self::PATH );
      $result = true;
      $input = array ();
      $input[ self::ID ] = null;

      foreach( $objects as &$objVO )
      {
         $input[ self::NAME ] = $objVO->name;
         $input[ self::DESCRIPTION ] = $objVO->description;
         $result &= ( $database->insertItem( $input ) > 0 );
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
      $database = new XMLInterface( self::PATH );
      $result = true;

      foreach( $objects as &$objVO )
      {
         $filter = array ();
         $filter[ self::ID ] = $objVO->idasset;
         $input = array ();
         $input[ self::NAME ] = $objVO->name;
         $input[ self::DESCRIPTION ] = $objVO->description;
         $result &= $database->updateFile( $filter, $input );
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
      $database = new XMLInterface( self::PATH );
      $result = true;

      foreach( $ids as $id )
      {
         $filter = array ();
         $filter[ self::ID ] = $id;
         $result &= $database->removeItems( $filter );
      }

      return $result;
   }

   /**
    * Converte o resultado do banco de dados em um objeto.
    *
    * @param array $result
    *           Mapa de resultados do banco para um objeto.
    * @return \ValueObject\TableObject Objeto da tabela.
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
