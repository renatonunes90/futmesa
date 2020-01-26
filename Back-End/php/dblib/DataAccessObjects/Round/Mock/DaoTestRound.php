<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/Round/DaoRoundInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/Round.php";

/**
 * Objeto para acessar o banco de dados de testes das rodadas.
 */
class DaoTestRound implements DaoRoundInterface
{
   const PATH = "DataAccessObjects\\Round\\Mock\\ROUND.xml";

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoRoundInterface::getAllRounds()
    */
   public function getAllRounds( int $championshipId ): array
   {
      $rounds = array ();
      $database = new XMLInterface( self::PATH );
      $result = $database->getFilteredObjects( self::ROUND, array ( self::IDCHAMPIONSHIP => $championshipId ) );

      foreach ( $result as &$item )
      {
         $rounds[ $item[ self::ID ] ] = $this->convertToRound( $item );
      }

      return $rounds;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoRoundInterface::getRoundsByPhase()
    */
   public function getRoundsByPhase( int $phaseId ): array
   {
       $rounds = array ();
       $database = new XMLInterface( self::PATH );
       $result = $database->getFilteredObjects( self::ROUND, array ( self::IDPHASE => $phaseId ) );
       
       foreach ( $result as &$item )
       {
           $rounds[ $item[ self::ID ] ] = $this->convertToRound( $item );
       }
       
       return $rounds;
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
    * Converte o resultado do banco de dados em uma rodada.
    *
    * @param array $result
    *           Mapa de resultados do banco para uma rodada.
    * @return \ValueObject\Round Rodada.
    */
   private function convertToRound( array $result ): \ValueObject\Round
   {
      $object = new \ValueObject\Round();
      $object->id = $result[ self::ID ];
      $object->idchampionship = $result[ self::IDCHAMPIONSHIP ];
      $object->basedate = $result[ self::BASEDATE ];
      $object->basehour = $result[ self::BASEHOUR ];
      $object->number = $result[ self::NUMBER ];
      return $object;
   }
}
?>
