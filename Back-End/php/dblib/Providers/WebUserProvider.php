<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "BusinessInteligence/WebUser.php";
require_once "DataAccessObjects/WebUser/DaoWebUserFactory.php";

/**
 * A responsabilidade desta classe é manter uma instância única para acessar o DataAccessObject de usuário e realizar a camada
 * de inteligência que transforma um objeto do banco de dados em um objeto com sentido lógico no programa.
 */
class WebUserProvider
{

   /**
    *
    * @var string Última mensagem de erro.
    */
   private $lastError_;

   /**
    *
    * @var \DAO\DaoWebUserInterface DAO de usuário.
    */
   private $daoWebUser_;

   /**
    *
    * @var WebUserProvider Singleton do Provider.
    */
   private static $instance_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {
      $this->daoWebUser_ = \DAO\DaoWebUserFactory::getDaoWebUser();
      $this->lastError_ = "";
   }

   /**
    * Retorna o singleton da classe.
    *
    * @return WebUserProvider Instância do Provider.
    */
   public static function getInstance(): WebUserProvider
   {
      if( !self::$instance_ )
      {
         self::$instance_ = new self();
      }

      return self::$instance_;
   }

   /**
    * Retorna a última mensagem de erro.
    *
    * @return string String contendo a mensagem.
    */
   public function getLastError(): string
   {
      return $this->lastError_;
   }

   /**
    * Retorna um usuário determinado.
    *
    * @param int $idwebuser
    *           Identificador do usuário.
    * @return WebUser|NULL Usuário ou nulo se não existir.
    */
   public function getWebUser( int $idwebuser ): ?WebUser
   {
      $webUserVO = $this->daoWebUser_->getWebUser( $idwebuser );
      return $webUserVO != null ? new WebUser( $webUserVO, $this->daoWebUser_->getRight( $webUserVO->idwebuser ) ) : null;
   }

   /**
    * Retorna um usuário determinado.
    *
    * @param string $login
    *           Login do usuário.
    * @return WebUser|NULL Usuário ou nulo se não existir.
    */
   public function getWebUserByLogin( string $login ): ?WebUser
   {
      $webUserVO = $this->daoWebUser_->getWebUserByLogin( $login );
      return $webUserVO != null ? new WebUser( $webUserVO, $this->daoWebUser_->getRight( $webUserVO->idwebuser ) ) : null;
   }

   /**
    * Retorna uma lista de usuários com os identificadores passados.
    *
    * @param array $ids
    *           Lista de identificadres de WebUsers.
    * @return array Mapa de objetos \DBLib\WebUser ordenados pelo identificador.
    */
   public function getWebUsers( array $ids ): array
   {
      $users = array ();
      $allUsers = $this->getAllWebUsers();

      foreach( $ids as $id )
      {
         if( isset( $allUsers[ $id ] ) )
         {
            $users[ $id ] = $allUsers[ $id ];
         }
      }

      return $users;
   }

   /**
    * Retorna todos os usuários.
    *
    * @return array Mapa de usuários do banco ordenados por seu identificador.
    */
   public function getAllWebUsers(): array
   {
      $webUsers = array ();

      $webUsersVO = $this->daoWebUser_->getAllWebUsers();

      foreach( $webUsersVO as $idWebUser => &$u )
      {
         $webUsers[ $idWebUser ] = new WebUser( $u );
      }

      return $webUsers;
   }

   /**
    * Insere um novo usuário.
    *
    * @param \ValueObject\WebUser $webUserVO
    *           Os dados do usuário a ser inserido.
    * @return bool Indica se foi possível inserir o usuário ou não.
    */
   public function insertWebUser( \ValueObject\WebUser $webUserVO ): bool
   {
      return $this->daoWebUser_->insertWebUser( $webUserVO );
   }

   /**
    * Atualiza os dados de um usuário se for o próprio usuário ou um admin.
    *
    * @param \ValueObject\WebUser $userVO
    *           Os dados modificados em ValueObject.
    * @return bool Indica se foi possível atualizar ou não.
    */
   public function updateWebUser( \ValueObject\WebUser $userVO ): bool
   {
      return $this->daoWebUser_->updateWebUser( $userVO );
   }

   /**
    * Remove um array de usuários a partir de seus IDs.
    *
    * @param array $idwebusers
    *           Array de identificadores de usuários.
    * @return int A quantidade de usuários removidos.
    */
   public function deleteWebUsers( array $idwebusers ): int
   {
      $removed = 0;
      $this->lastError_ = "";

      if( !empty( $idwebusers ) )
      {
         $removed = $this->daoWebUser_->removeWebUsers( $idwebusers );
      }
      else
      {
         $this->lastError_ = "";
      }

      return $removed;
   }
}
