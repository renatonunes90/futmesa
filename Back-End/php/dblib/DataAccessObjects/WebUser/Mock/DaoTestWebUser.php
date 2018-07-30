<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use ValueObject\WebUser;

require_once "DataAccessObjects/WebUser/DaoWebUserInterface.php";
require_once "DataAccessObjects/XMLInterface.php";
require_once "ValueObjects/WebUser.php";

/**
 * Objeto para acessar o banco de dados de testes dos Usuários.
 */
class DaoTestWebUser implements DaoWebUserInterface
{

   const CONNWU = "DataAccessObjects\\WebUser\\Mock\\WEBUSER.xml";

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::getAllWebUsers()
    */
   public function getAllWebUsers(): array
   {
      $database = new XMLInterface( self::CONNWU );
      $results = $database->getAllObjects( self::WEBUSER );

      $webusers = array ();
      $this->sortItems( $results, self::NAME );

      foreach( $results as &$uniObj )
      {
         $wu = $this->convertToObject( $uniObj );
         $webusers[ $uniObj[ self::ID ] ] = $wu;
      }

      return $webusers;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::getWebUser()
    */
   public function getWebUser( int $idwebuser ): ?WebUser
   {
      $database = new XMLInterface( self::CONNWU );
      $result = $database->getObject( self::WEBUSER, $idwebuser );

      if( empty( $result ) )
      {
         $user = null;
      }
      else
      {
         $user = $this->convertToObject( $result );
      }

      return $user;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see \DAO\DaoWebUserInterface::getWebUsers()
    */
   public function getWebUsers( array $idwebusers ): array
   {
      $users = array ();

      foreach( $idwebusers as $id )
      {
         $users[] = $this->getWebUser( $id );
      }

      return $users;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::getWebUserByLogin()
    */
   public function getWebUserByLogin( string $login ): ?WebUser
   {
      $database = new XMLInterface( self::CONNWU );
      $filter = array ();
      $filter[ "LOGIN" ] = $login;
      $result = $database->getFilteredObjects( self::WEBUSER, $filter )[ 0 ];

      if( empty( $result ) )
      {
         $user = null;
      }
      else
      {
         $user = $this->convertToObject( $result );
      }

      return $user;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see \DAO\DaoWebUserInterface::insertWebUser()
    */
   public function insertWebUser( WebUser $webUserVO ): bool
   {
      $database = new XMLInterface( self::CONNWU );
      $data = array ();
      $data[ self::ID ] = null;
      $data[ self::NAME ] = $webUserVO->name;
      $data[ self::LOGIN ] = $webUserVO->login;
      $data[ self::PASS ] = $webUserVO->pass;
      $data[ self::EMAIL ] = $webUserVO->email;
      $data[ self::ISADMIN ] = $webUserVO->isadmin;
      return ( bool ) ( $database->insertItem( $data ) != - 1 );
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::updateWebUser()
    */
   public function updateWebUser( \ValueObject\WebUser $user ): bool
   {
      $database = new XMLInterface( self::CONNWU );

      $filter = array ();
      $filter[ self::ID ] = $user->id;
      $keep = array (
         self::LOGIN,
         self::NAME,
         self::EMAIL,
         self::ISADMIN
      );

      if( $user->isadmin )
      {
         $keep[] = self::PASS;
      }

      $keep = array_map( "strtolower", $keep );
      $changes = array ();
      foreach( $keep as $col )
      {
         $changes[ strtoupper( $col ) ] = $user->$col;
      }

      return ( bool ) $database->updateFile( $filter, $changes );
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::removeWebUser()
    */
   public function removeWebUser( int $idwebuser ): bool
   {
      $database = new XMLInterface( self::CONNWU );
      $result = false;
      $filter = array (
         self::ID => $idwebuser
      );

      if( $database->removeItems( $filter ) )
      {
         $result = true;

         // remove de todos os grupos
         $wugm = new XMLInterface( self::CONNWUGM );
         $wugm->removeItems( $filter );
      }

      return $result;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::removeWebUsers()
    */
   public function removeWebUsers( array $idwebusers ): int
   {
      $result = 0;

      foreach( $idwebusers as $id )
      {
         $result += ( ( $this->removeWebUser( $id ) ) ? 1 : 0 );
      }

      return $result;
   }

   /**
    * Converte o resultado do banco de dados em um objeto do usuário.
    *
    * @param array $result
    *           Mapa de resultados do banco para um usuário.
    * @return WebUser Objeto do usuário.
    */
   private function convertToObject( array $result ): WebUser
   {
      $webUser = new WebUser();
      $webUser->id = intval( $result[ self::ID ] );
      $webUser->email = $result[ self::EMAIL ];
      $webUser->login = $result[ self::LOGIN ];
      $webUser->name = $result[ self::NAME ];
      $webUser->pass = $result[ self::PASS ];
      $webUser->isadmin = boolval( $result[ self::ISADMIN ] );
      return $webUser;
   }

   /**
    * Ordena o array de objetos em ordem crescente ou descrescente, a partir de uma coluna.
    *
    * @param array $items
    *           O array de objetos a ser ordenado.
    * @param string $colName
    *           O nome da coluna pela qual deve-se ordenar o array.
    * @param string $sortOrder
    *           [optional] A ordem em que deve ser ordenado.
    * @return array Array ordenado.
    */
   private function sortItems( array &$items, string $colName, string $sortOrder = "ASC"): array
   {
      $this->colName_ = $colName;

      if( ( strtolower( $sortOrder ) == "desc" ) || ( strtolower( $sortOrder ) == "asc" ) )
      {
         $this->order_ = strtoUpper( $sortOrder );
      }
      else
      {
         $this->order_ = "ASC";
      }

      uasort( $items, function ( $item, $item2 ) {
         $i = strcmp( $item[ strtoupper( $this->colName_ ) ], $item2[ strtoupper( $this->colName_ ) ] );
         $value = ( ( $this->order_ == "DESC" ) ? - ( $i ) : ( $i ) );
         return ( $value );
      } );

      return $items;
   }
}
?>