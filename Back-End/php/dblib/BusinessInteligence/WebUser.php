<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/WebUser.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de um usuário.
 */
class WebUser
{

   /**
    *
    * @var \ValueObject\Webuser Objeto do usuário do banco.
    */
   public $webUserVO_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\WebUser $webUser
    *           Usuário do banco.
    */
   public function __construct( \ValueObject\WebUser $webUser )
   {
      $this->webUserVO_ = $webUser;
   }

   /**
    * Retorna o objeto do banco.
    *
    * @return \ValueObject\WebUser VO do usuário.
    */
   public function getWebUserVO(): \ValueObject\WebUser
   {
      return $this->webUserVO_;
   }

   /**
    * Retorna se o usuário é administrador ou não.
    *
    * @return bool Flag.
    */
   public function isAdmin(): bool
   {
      return $this->webUserVO_->isadmin;
   }
}
