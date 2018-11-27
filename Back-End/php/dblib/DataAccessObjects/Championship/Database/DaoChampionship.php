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
    * @see \DAO\DaoChampionshipInterface::getLastInsertedId()
    */
   public function getLastInsertedId(): int
   {
      $id = 0;
      $result = $this->db_->selectAll( "SELECT MAX(ID) as ID FROM championship" );
      
      foreach ( $result as &$r )
      {
         $id = $r[ self::ID ];
      }
      
      return $id;
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
            $val[] = $c->idseason;
            $val[] = $c->name;
            $val[] = $c->type;
            $val[] = $c->isfinished;
            $val[] = $c->basedate;
            $val[] = $c->dateincr;
            $val[] = $c->roundsbyday;
            $val[] = $c->gamesbyround;
            $values[] = $val;
         }
  
         $query = "INSERT INTO championship ( idseason, name, type, isfinished, basedate, dateincr, roundsbyday, gamesbyround )
                   VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
         $result = $this->db_->executeMultiplePrepared( $query, $values );
      }

      return $result;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::updateChampionships()
    */
   public function updateChampionships( array $championships ): bool
   {
      $result = true;
   
      $values = array ();
      if( count( $championships ) > 0 )
      {
         foreach( $championships as $c )
         {
            $val = array();
            $val[] = $c->idseason;
            $val[] = $c->name;
            $val[] = $c->type;
            $val[] = $c->isfinished;
            $val[] = $c->basedate;
            $val[] = $c->dateincr;
            $val[] = $c->roundsbyday;
            $val[] = $c->gamesbyround;
            $val[] = $c->id;
            $values[] = $val;
         }
         
         $query = "UPDATE championship 
                      SET idseason=?, name=?, type=?, isfinished=?, basedate=?, dateincr=?, roundsbyday=?, gamesbyround=?
                    WHERE id=?";
         $result = $this->db_->executeMultiplePrepared( $query, $values );
      }
   
      return $result;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::deleteChampionships()
    */
   public function deleteChampionships( array $ids ): bool
   {
      $result = true;

      if( count( $ids ) > 0 )
      {
         $values = array ();
         foreach( $ids as $id )
         {
            $values[] = array ( $id );
         }
         $query = "DELETE FROM championship
                    WHERE id = ?";
         $result = $this->db_->executeMultiplePrepared( $query, $values );
      }

      return $result;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::saveParticipants()
    */
   public function saveParticipants( array $participants ) : bool
   {
      $result = true;
      
      if( count( $participants ) > 0 )
      {
         $values = array ();
         foreach( $participants as $p )
         {
            $val = array();
            $val[] = $p->idchampionship;
            $val[] = $p->idplayer;
            $values[] = $val;
         }
         
         $query = "INSERT INTO participant ( idchampionship, idplayer )
                   VALUES ( ?, ? )";
         $result = $this->db_->executeMultiplePrepared( $query, $values );
      }
      
      return $result;
   }
   
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
      $object->idseason = $result[ self::IDSEASON ];
      $object->name = $result[ self::NAME ];
      $object->type = $result[ self::TYPE ];
      $object->isfinished = $result[ self::ISFINISHED ];
      $object->basedate = $result[ self::BASEDATE ];
      $object->dateincr = $result[ self::DATEINCR ];
      $object->roundsbyday = $result[ self::ROUNDSBYDAY ];
      $object->gamesbyround = $result[ self::GAMESBYROUND ];
      return $object;
   }
}

?>