<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/GameResult/DaoGameResultInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/GameResult.php";

/**
 * Objeto para acessar o banco de dados de testes dos resultados dos jogos.
 */
class DaoTestGameResult implements DaoGameResultInterface
{
   const PATH = "DataAccessObjects\\GameResult\\Mock\\GAMERESULT.xml";

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoGameResultInterface::getResult()
    */
   public function getResult( int $gameId ): ?\ValueObject\GameResult
   {
      $gameResult = null;
      $database = new XMLInterface( self::PATH );
      $result = $database->getFilteredObjects( self::GAMERESULT, array ( self::IDGAME => $gameId ) );

      foreach ( $result as &$item )
      {
         $gameResult = $this->convertToGameResult( $item );
      }

      return $gameResult;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoGameResultInterface::getAllResults()
    */
   public function getAllResults( int $championshipId ): array
   {
      $gameResults = array ();
      $games = DaoGameFactory::getDaoGame()->getAllGames( $championshipId );

      $database = new XMLInterface( self::PATH );
      $result = $database->getFilteredObjects( self::GAMERESULT, array ( self::IDGAME => array_keys( $games ) ) );

      foreach ( $result as &$item )
      {
         $gameResults[ $item[ self::IDGAME ] ] = $this->convertToGameResult( $item );
      }

      return $gameResults;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoGameResultInterface::insertResult()
    */
   public function insertResult( \ValueObject\GameResult $result ): bool
   {
      $database = new XMLInterface( self::PATH );
      $res = true;

      $input = array ();
      $input[ self::IDGAME ] = $result->idgame;
      $input[ self::SCORE1 ] = $result->score1;
      $input[ self::SCORE2 ] = $result->score2;
      $input[ self::INPUTDATE ] = $result->inputdate;
      $input[ self::IDWINNER ] = $result->idwinner;

      $res = ( $database->insertItem( $input ) > -1 );

      return $res;
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
    * Converte o resultado do banco de dados em um resultado de jogo.
    *
    * @param array $result
    *           Mapa de resultados do banco para um resultado de jogo.
    * @return \ValueObject\GameResult Jogo.
    */
   private function convertToGameResult( array $result ): \ValueObject\GameResult
   {
      $object = new \ValueObject\GameResult();
      $object->idgame = $result[ self::IDGAME ];
      $object->score1 = $result[ self::SCORE1 ];
      $object->score2 = $result[ self::SCORE2 ];
      $object->inputdate = $result[ self::INPUTDATE ];
      $object->idwinner = $result[ self::IDWINNER ];
      return $object;
   }
}
?>
