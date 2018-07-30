<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

use ValueObject\WebUser;

/**
 * Interface com as funções que uma implementação de DAO de usuário deve possuir.
 */
interface DaoWebUserInterface
{

   const WEBUSER = "WEBUSER";

   // -- ValueObjects --
   // WebUser
   const ID = "ID";

   const EMAIL = "EMAIL";

   const LOGIN = "LOGIN";

   const NAME = "NAME";

   const PASS = "PASS";

   const ISADMIN = "ISADMIN";

   /**
    * Busca todas os usuários do banco de dados.
    *
    * @return array Todos os usuários do banco de dados.
    */
   public function getAllWebUsers(): array;

   /**
    * Busca um usuário específico.
    *
    * @param int $idwebuser
    *           Identificador do usuário.
    * @return WebUser|NULL Objeto do usuário ou nulo se não existir.
    */
   public function getWebUser( int $idwebuser ): ?WebUser;

   /**
    * Retorna os usuários a partir de uma lista de identificadores de usuários
    *
    * @param array $idwebusers
    *           Array com identificadores de usuários
    * @return array Array de WebUserVOs
    */
   public function getWebUsers( array $idwebusers ): array;

   /**
    * Busca um usuário específico pelo seu login.
    *
    * @param string $login
    *           Login do usuário.
    * @return WebUser|NULL Objeto do usuário ou nulo se não existir.
    */
   public function getWebUserByLogin( string $login ): ?WebUser;

   /**
    * Insere um novo usuário.
    *
    * @param WebUser $webUserVO
    *           ValueObject que deve ser inserido no banco de dados.
    * @return bool Indica se o usuário foi inserido.
    */
   public function insertWebUser( WebUser $webUserVO ): bool;

   /**
    * Atualiza os dados de um usuário.
    *
    * @param \ValueObject\WebUser $user
    *           O ValueObject de usuário com os dados alterados.
    * @return bool Se foi possível atualizar ou não.
    */
   public function updateWebUser( WebUser $user ): bool;

   /**
    * Remove um usuário do banco de dados.
    *
    * @param int $idwebuser
    *           O identificador do usuário.
    * @return bool Se foi possível removê-lo ou não.
    */
   public function removeWebUser( int $idwebuser ): bool;

   /**
    * Remove um array de usuários.
    *
    * @param array $idwebusers
    *           Array de identificador de usuários.
    * @return int A quantidade de usuários removidos.
    */
   public function removeWebUsers( array $idwebusers ): int;
}
