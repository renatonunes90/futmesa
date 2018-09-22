<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/Game/DaoGameInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/Game.php";

/**
 * Objeto para acessar o banco de dados de testes dos jogos.
 */
class DaoTestGame implements DaoGameInterface
{
   const PATH = "DataAccessObjects\\Game\\Mock\\GAME.xml";

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoGameInterface::getAllGames()
    */
   public function getAllGames( int $championshipId ): array
   {
      $games = array ();
      $rounds = DaoRoundFactory::getDaoRound()->getAllRounds( $championshipId );

      $database = new XMLInterface( self::PATH );
      $result = $database->getFilteredObjects( self::GAME, array ( self::IDROUND => array_keys( $rounds ) ) );

      foreach ( $result as &$item )
      {
         $games[ $item[ self::ID ] ] = $this->convertToGame( $item );
      }

      return $games;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoGameInterface::getAllGamesByRound()
    */
   public function getAllGamesByRound( int $roundId ): array
   {
      $games = array ();
      $database = new XMLInterface( self::PATH );
      $result = $database->getFilteredObjects( self::GAME, array ( self::IDROUND => $roundId ) );

      foreach ( $result as &$item )
      {
         $games[ $item[ self::ID ] ] = $this->convertToGame( $item );
      }

      return $games;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoGameInterface::updateResult()
    */
   public function updateResult( \ValueObject\Game $result ): bool
   {
      $database = new XMLInterface( self::PATH );
      $res = true;

      $filter = array ();
      $filter[ self::ID ] = $result->id;

      $input = array ();
      $input[ self::SCORE1 ] = $result->score1;
      $input[ self::SCORE2 ] = $result->score2;
      $input[ self::INPUTDATE ] = $result->inputdate;
      $input[ self::IDWINNER ] = $result->idwinner;

      $res = ( $database->updateFile( $filter, $input ) > -1 );

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
    * Converte o resultado do banco de dados em um jogo.
    *
    * @param array $result
    *           Mapa de resultados do banco para um jogo.
    * @return \ValueObject\Game Jogo.
    */
   private function convertToGame( array $result ): \ValueObject\Game
   {
      $object = new \ValueObject\Game();
      $object->id = $result[ self::ID ];
      $object->idround = $result[ self::IDROUND ];
      $object->idplayer1 = $result[ self::IDPLAYER1 ];
      $object->idplayer2 = $result[ self::IDPLAYER2 ];
      $object->gametable = $result[ self::GAMETABLE ];
      $object->score1 = $result[ self::SCORE1 ];
      $object->score2 = $result[ self::SCORE2 ];
      $object->inputdate = $result[ self::INPUTDATE ];
      $object->idwinner = $result[ self::IDWINNER ];
      return $object;
   }
}
?>
