<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */

/**
 * A responsabilidade desta classe é encapsular os atributos para retonar em uma requisição HTTP.
 */
class DtoReviewInfo
{
   
   public $key;
   
   public $value;

   public function __construct( string $key, string $value )
   {
      $this->key = $key;
      $this->value = $value;
   }

}
