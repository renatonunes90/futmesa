<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

/**
 * Exceção personalizada para erros no ChampionshipManager.
 */
class ChampionshipManagerException extends \Exception
{

   public function __construct( $message, $code = 0, \Exception $previous = null)
   {
      parent::__construct( $message, $code, $previous );
   }

   public function __toString()
   {
      return __CLASS__ . ": [{$this->code}]: {$this->message}\n";
   }
}
