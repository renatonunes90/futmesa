<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\Database;

require_once "DataAccessObjects/Round/DaoRoundInterface.php";
require_once "ValueObjects/Round.php";

/**
 * Objeto para acessar o banco de dados das rodadas.
 */
class DaoRound implements DaoRoundInterface
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
    * @see \DAO\DaoRoundInterface::getAllRounds()
    */
   public function getAllRounds( int $championshipId ): array
   {
      $objects = array ();
      $result = $this->db_->selectAll( "SELECT r.* FROM round r WHERE r.idchampionship = $championshipId" );

      foreach ( $result as &$r )
      {
         $objects[ $r[ self::ID ] ] = $this->convertToRound( $r );
      }

      return $objects;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoRoundInterface::getRoundsByPhase()
    */
   public function getRoundsByPhase( int $phaseId ): array
   {
       $objects = array ();
       $result = $this->db_->selectAll( "SELECT r.* FROM round r WHERE r.idphase = $phaseId" );
       
       foreach ( $result as &$r )
       {
           $objects[ $r[ self::ID ] ] = $this->convertToRound( $r );
       }
       
       return $objects;
   }
   
   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoRoundInterface::getRoundsByGroup()
    */
   public function getRoundsByGroup( int $groupId ): array
   {
       $objects = array ();
       $result = $this->db_->selectAll( "SELECT r.* FROM round r JOIN grouprounds gr ON gr.idround = r.id WHERE gr.idgroup = $groupId" );
       
       foreach ( $result as &$r )
       {
           $objects[ $r[ self::ID ] ] = $this->convertToRound( $r );
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
      $object->idphase = $result[ self::IDPHASE ];
      $object->basedate = $result[ self::BASEDATE ];
      $object->basehour = $result[ self::BASEHOUR ];
      $object->number = $result[ self::NUMBER ];
      return $object;
   }
}

?>