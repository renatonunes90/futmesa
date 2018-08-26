<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\Database;

require_once "DataAccessObjects/GameResult/DaoGameResultInterface.php";
require_once "ValueObjects/GameResult.php";

/**
 * Objeto para acessar o banco de dados dos resultados dos jogos.
 */
class DaoGameResult implements DaoGameResultInterface
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
    * @see \DAO\DaoGameResultInterface::getResult()
    */
   public function getResult( int $gameId ): ?\ValueObject\GameResult
   {
      $gameResult = null;
      $result = $this->db_->selectAll( "SELECT * FROM gameresult g WHERE g.idgame = $gameId" );

      foreach ( $result as &$r )
      {
         $gameResult = $this->convertToGameResult( $r );
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
      $objects = array ();
      $result = $this->db_->selectAll(
               "SELECT gr.*
                                          FROM gameresult gr
                                          JOIN game g ON ( g.id = gr.idgame )
                                          JOIN round r ON ( r.id = g.idround )
                                         WHERE r.idchampionship = $championshipId" );

      foreach ( $result as &$r )
      {
         $objects[ $r[ self::IDGAME ] ] = $this->convertToGameResult( $r );
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