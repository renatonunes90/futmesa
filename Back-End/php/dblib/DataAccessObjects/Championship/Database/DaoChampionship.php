<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\Database;

require_once "DataAccessObjects/Championship/DaoChampionshipInterface.php";
require_once "ValueObjects/Championship.php";

/**
 * Objeto para acessar o banco de dados dos campeonatos.
 */
class DaoChampionship implements DaoChampionshipInterface
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
    * @see \DAO\DaoChampionshipInterface::getAllChampionships()
    */
   public function getAllChampionships(): array
   {
      $objects = array ();
      $result = $this->db_->selectAll( "SELECT * FROM championship" );

      foreach ( $result as &$r )
      {
         $objects[] = $this->convertToChampionship( $r );
      }

      return $objects;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::getParticipants()
    */
   public function getParticipants( int $championshipId ): array
   {
      $objects = array ();
      $result = $this->db_->selectAll( "SELECT p.idplayer FROM participant p WHERE p.idchampionship = $championshipId" );

      foreach ( $result as &$r )
      {
         $objects[] = $r[ "IDPLAYER" ];
      }

      return $objects;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::createChampionships()
    */
   public function createChampionships( array $championships ): bool
   {
      $result = true;

      if( count( $championships ) > 0 )
      {
         $values = array ();
         foreach( $championships as $c )
         {
            $val = array();
            $val[] = $c->name;
            $val[] = $c->basedate;
            $val[] = $c->dateincr;
            $val[] = $c->roundsbyday;
            $val[] = $c->gamesbyround;
            $values[] = $val;
         }
  
         $query = "INSERT INTO championship ( name, basedate, dateincr, roundsbyday, gamesbyround )
                   VALUES ( ?, ?, ?, ?, ? )";
         $result = $this->db_->executeMultiplePrepared( $query, $values );
      }

      return $result;
   }

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
    * Converte o resultado do banco de dados em um campeonato.
    *
    * @param array $result
    *           Mapa de resultados do banco para um campeonato.
    * @return \ValueObject\Championship Campeonato.
    */
   private function convertToChampionship( array $result ): \ValueObject\Championship
   {
      $object = new \ValueObject\Championship();
      $object->id = $result[ self::ID ];
      $object->name = $result[ self::NAME ];
      $object->basedate = $result[ self::BASEDATE ];
      $object->dateincr = $result[ self::DATEINCR ];
      $object->roundsbyday = $result[ self::ROUNDSBYDAY ];
      $object->gamesbyround = $result[ self::GAMESBYROUND ];
      return $object;
   }
}

?>