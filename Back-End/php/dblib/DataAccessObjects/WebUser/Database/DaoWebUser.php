<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use Database\Database;
use ValueObject\WebUser;

require_once "DataAccessObjects/WebUser/DaoWebUserInterface.php";
require_once "ValueObjects/WebUser.php";

/**
 * Objeto para acessar o banco de dados de usuários.
 */
class DaoWebUser implements DaoWebUserInterface
{

   const COUNT = "COUNT";

   const CASE = "CASE";

   /**
    *
    * @var Database Conexão do banco de dados para realizar consultas.
    */
   private $db_;

   /**
    * Construtor padrão que recebe uma conexão do banco.
    *
    * @param Database $db
    *           Conexão do banco para realizar consultas.
    */
   public function __construct( Database $db )
   {
      $this->db_ = $db;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::getAllWebUsers()
    */
   public function getAllWebUsers(): array
   {
      $webUsers = array ();
      $result = $this->db_->selectAll( "SELECT * FROM webuser ORDER BY name ASC" );

      foreach( $result as &$r )
      {
         $wu = $this->convertToObject( $r );
         $webUsers [$wu->id] = $wu;
      }

      return $webUsers;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::getWebUser()
    */
   public function getWebUser( int $idwebuser ): ?WebUser
   {
      $result = $this->db_->selectAll( "SELECT * FROM webuser WHERE id = $idwebuser" );
      return count( $result ) > 0 ? $this->convertToObject( array_shift( $result ) ) : null;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see \DAO\DaoWebUserInterface::getWebUsers()
    */
   public function getWebUsers( array $idwebusers ): array
   {
      $result = array ();

      if( count( $idwebusers ) > 0 )
      {
         $sql = "SELECT * FROM webuser WHERE id IN( " . implode( ", ", $idwebusers ) . " )";
         $dbRst = $this->db_->selectAll( $sql );
         foreach( $dbRst as $r )
         {
            $result [] = $this->convertToObject( $r );
         }
      }

      return $result;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::getWebUserByLogin()
    */
   public function getWebUserByLogin( string $login ): ?WebUser
   {
      $result = $this->db_->selectAll( "SELECT * FROM webuser WHERE LOWER(login) = '" . strtolower( $login ) . "'" );
      return count( $result ) > 0 ? $this->convertToObject( array_shift( $result ) ) : null;
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::insertWebUser()
    */
   public function insertWebUser( WebUser $webUserVO ): bool
   {
      $query = "INSERT INTO webuser ( name, login, pass, email, isadmin ) VALUES ( ?, '";
      $query .= $webUserVO->login . "', '";
      $query .= md5( $webUserVO->pass ) . "', '";
      $query .= $webUserVO->email . "', ";
      $query .= $webUserVO->isadmin . " )";

      $this->db_->executeMultiplePrepared( $query, array (
         array (
            $webUserVO->name
         )
      ) );

      return ! $this->db_->hasError();
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::updateWebUser()
    */
   public function updateWebUser( WebUser $user ): bool
   {
      $query = "UPDATE webuser SET ";
      $query .= self::LOGIN . " = '" . $user->login . "', ";
      $query .= self::ISADMIN . " = " . $user->isadmin . ", ";
      $query .= self::NAME . " = ?, ";
      $query .= self::EMAIL . " = '" . $user->email . "' ";
      if( strlen( $user->pass ) > 0 )
      {
         $query .= ", " . self::PASS . " = '" . md5( $user->pass ) . "' ";
      }
      $query .= "WHERE " . self::ID . " = " . $user->id;

      return $this->db_->executeMultiplePrepared( $query, array (
         array (
            $user->name
         )
      ) );
   }

   /**
    *
    * {@inheritdoc}
    *
    * @see DaoWebUserInterface::removeWebUser()
    */
   public function removeWebUser( int $idwebuser ): bool
   {
      $result = false;
      $query = "DELETE FROM webuser WHERE id = $idwebuser";

      if( $this->db_->query( $query ) )
      {
         $result = true;
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
      $query = "DELETE FROM webuser WHERE id ";
      $in = "IN ( ";

      foreach( $idwebusers as $key => $id )
      {
         $in .= $id;
         $in .= ( ( isset( $idwebusers [$key + 1] ) ) ? ", " : " )" );
      }

      $query .= $in;

      if( $this->db_->query( $query ) )
      {
         $result = count( $idwebusers );
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
      $webUser->id = intval( $result [self::ID] );
      $webUser->email = $result [self::EMAIL];
      $webUser->login = $result [self::LOGIN];
      $webUser->name = $result [self::NAME];
      $webUser->pass = $result [self::PASS];
      $webUser->isadmin = $result [self::ISADMIN];
      return $webUser;
   }
}
