<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

require_once "DataAccessObjects/Championship/DaoChampionshipInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/Championship.php";

/**
 * Objeto para acessar o banco de dados de testes dos campeonatos.
 */
class DaoTestChampionship implements DaoChampionshipInterface
{
   const PATH = "DataAccessObjects\\Championship\\Mock\\CHAMPIONSHIP.xml";
   const PARTICIPANT_PATH = "DataAccessObjects\\Championship\\Mock\\PARTICIPANT.xml";

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::getAllChampionships()
    */
   public function getAllChampionships(): array
   {
      $championships = array ();
      $database = new XMLInterface( self::PATH );
      $result = $database->getAllObjects( self::CHAMPIONSHIP );

      foreach ( $result as &$item )
      {
         $championships[] = $this->convertToChampionship( $item );
      }

      return $championships;
   }

   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::getParticipants()
    */
   public function getParticipants( int $championshipId ): array
   {
      $participants = array ();
      $database = new XMLInterface( self::PARTICIPANT_PATH );
      $result = $database->getFilteredObjects( "PARTICIPANT", array ( "IDCHAMPIONSHIP" => $championshipId ) );

      foreach ( $result as &$item )
      {
         $participants[] = $item[ "IDPLAYER" ];
      }

      return $participants;
   }
   
   /**
    *
    * {@inheritdoc}
    * @see \DAO\DaoChampionshipInterface::getLastInsertedId()
    */
   public function getLastInsertedId(): int
   {
      $id = 0;
      $database = new XMLInterface( self::PATH );
      $result = $database->getAllObjects( self::CHAMPIONSHIP );
      
      foreach ( $result as &$item )
      {
         if ( $id < $item[ self::ID ] )
         {
            $id = $item[ self::ID ];
         }
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
      $database = new XMLInterface( self::PATH );
      $result = true;
      $input = array ();
      $input[ self::ID ] = null;

      foreach( $championships as &$c )
      {
         $input[ self::IDSEASON ] = $c->idseason;
         $input[ self::NAME ] = $c->name;
         $input[ self::TYPE ] = $c->type;
         $input[ self::ISFINISHED ] = $c->isfinished;
         $input[ self::BASEDATE ] = $c->basedate;
         $input[ self::DATEINCR ] = $c->dateincr;
         $input[ self::ROUNDSBYDAY ] = $c->roundsbyday;
         $input[ self::GAMESBYROUND ] = $c->gamesbyround;
         $result &= ( $database->insertItem( $input ) > 0 );
       }

      return $result;
   }

   /**
    *
    * {@inheritdoc}
    *  @see \DAO\DaoChampionshipInterface::updateChampionships()
    */
   public function updateChampionships( array $championships ): bool
   {
      $database = new XMLInterface( self::PATH );
      $result = true;
   
      foreach( $championships as &$c )
      {
         $filter = array ();
         $filter[ self::ID ] = $c->id;
         $input = array ();
         $input[ self::IDSEASON ] = $c->idseason;
         $input[ self::NAME ] = $c->name;
         $input[ self::TYPE ] = $c->type;
         $input[ self::ISFINISHED ] = $c->isfinished;
         $input[ self::BASEDATE ] = $c->basedate;
         $input[ self::DATEINCR ] = $c->dateincr;
         $input[ self::ROUNDSBYDAY ] = $c->roundsbyday;
         $input[ self::GAMESBYROUND ] = $c->gamesbyround;
         $result &= $database->updateFile( $filter, $input );
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
      $database = new XMLInterface( self::PATH );
      $result = true;

      foreach( $ids as $id )
      {
         $filter = array ();
         $filter[ "ID" ] = $id;
         $result &= $database->removeItems( $filter );
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
      $database = new XMLInterface( self::PATH );
      $result = true;
      $input = array ();
      
      foreach( $participants as &$p )
      {
         $input[ "idchampionship" ] = $p->idchampionship;
         $input[ "idplayer" ] = $p->idplayer;
         $result &= ( $database->insertItem( $input ) > 0 );
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
