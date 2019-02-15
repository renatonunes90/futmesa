<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/Season/DaoSeasonInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/Season.php";

/**
 * Objeto para acessar o banco de dados de testes das temporadas.
 */
class DaoTestSeason implements DaoSeasonInterface
{
   const PATH = "DataAccessObjects\\Season\\Mock\\SEASON.xml";

   /**
    *
    * {@inheritdoc}
    * @see DaoSeasonInterface::getAllSeasons()
    */
   public function getAllSeasons(): array
   {
      $players = array ();
      $database = new XMLInterface( self::PATH );
      $result = $database->getAllObjects( self::SEASON );

      foreach ( $result as &$item )
      {
         $players[] = $this->convertToSeason( $item );
      }

      return $players;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::insertTableObjects()
    */
   // public function insertTableObjects( array $objects ): bool
   // {
   // $database = new XMLInterface( self::PATH );
   // $result = true;
   // $input = array ();
   // $input[ self::ID ] = null;

   // foreach( $objects as &$objVO )
   // {
   // $input[ self::NAME ] = $objVO->name;
   // $input[ self::DESCRIPTION ] = $objVO->description;
   // $result &= ( $database->insertItem( $input ) > 0 );
   // }

   // return $result;
   // }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::updateTableObjects()
    */
   // public function updateTableObjects( array $objects ): bool
   // {
   // $database = new XMLInterface( self::PATH );
   // $result = true;

   // foreach( $objects as &$objVO )
   // {
   // $filter = array ();
   // $filter[ self::ID ] = $objVO->idasset;
   // $input = array ();
   // $input[ self::NAME ] = $objVO->name;
   // $input[ self::DESCRIPTION ] = $objVO->description;
   // $result &= $database->updateFile( $filter, $input );
   // }

   // return $result;
   // }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::deleteTableObjects()
    */
   // public function deleteTableObjects( array $ids ): bool
   // {
   // $database = new XMLInterface( self::PATH );
   // $result = true;

   // foreach( $ids as $id )
   // {
   // $filter = array ();
   // $filter[ self::ID ] = $id;
   // $result &= $database->removeItems( $filter );
   // }

   // return $result;
   // }

   /**
    * Converte o resultado do banco de dados em uma temporada.
    *
    * @param array $result
    *           Mapa de resultados do banco para uma temporada.
    * @return \ValueObject\Season Temporada.
    */
   private function convertToSeason( array $result ): \ValueObject\Season
   {
      $object = new \ValueObject\Season();
      $object->id = $result[ self::ID ];
      $object->name = $result[ self::NAME ];
      return $object;
   }
}
?>
