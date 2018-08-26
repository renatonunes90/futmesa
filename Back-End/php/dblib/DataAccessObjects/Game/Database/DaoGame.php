<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\Database;

require_once "DataAccessObjects/Game/DaoGameInterface.php";
require_once "ValueObjects/Game.php";

/**
 * Objeto para acessar o banco de dados dos jogos.
 */
class DaoGame implements DaoGameInterface
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
    * @see \DAO\DaoGameInterface::getAllGames()
    */
   public function getAllGames( int $championshipId ): array
   {
      $objects = array ();
      $result = $this->db_->selectAll(
               "SELECT g.*
                                          FROM game g
                                          JOIN round r ON ( r.id = g.idround )
                                         WHERE r.idchampionship = $championshipId" );

      foreach ( $result as &$r )
      {
         $objects[] = $this->convertToGame( $r );
      }

      return $objects;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoGameInterface::getAllGamesByRound()
    */
   public function getAllGamesByRound( int $roundId ): array
   {
      $objects = array ();
      $result = $this->db_->selectAll( "SELECT g.* FROM game g WHERE g.idround = $roundId" );

      foreach ( $result as &$r )
      {
         $objects[] = $this->convertToGame( $r );
      }

      return $objects;
   }

   /**
    *
    * {@inheritdoc}
    * @see DaoTableObjectInterface::insertTableObjects()
    */
   // public function insertTableObjects( array $objects ): bool
   // {
   // $result = true;

   // if( count( $objects ) > 0 )
   // {
   // $values = array ();
   // foreach( $objects as $objVO )
   // {
   // $val = array_values( ( array ) $objVO );
   // array_shift( $val );
   // $values[] = $val;
   // }

   // $query = "INSERT INTO tableobject ( name, description )
   // VALUES ( ?, ? )";
   // $result = $this->db_->executeMultiplePrepared( $query, $values );
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
   // $result = true;

   // if( count( $objects ) > 0 )
   // {
   // $values = array ();
   // foreach( $objects as $objVO )
   // {
   // $id = $objVO->id;
   // $val = array_values( ( array ) $objVO );

   // // passa o id para o final pois é o último argumento da query
   // array_shift( $val );
   // array_push( $val, $id );

   // $values[] = $val;
   // }
   // $sql = "UPDATE tableobject
   // SET name = ?, description = ?
   // WHERE id = ?";
   // $result = $this->db_->executeMultiplePrepared( $sql, $values );
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
   // $result = true;

   // if( count( $ids ) > 0 )
   // {
   // $values = array ();
   // foreach( $ids as $id )
   // {
   // $values[] = array ( $id );
   // }
   // $query = "DELETE FROM tableobject
   // WHERE id = ?";
   // $result = $this->db_->executeMultiplePrepared( $query, $values );
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
      return $object;
   }
}

?>