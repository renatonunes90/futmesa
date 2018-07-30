<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/Player.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de jogador.
 */
class Player
{

   /**
    *
    * @var \ValueObject\Player VO do jogador.
    */
   public $playerVO_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Player $playerVO
    *           Objeto contendo as informações do jogador no banco de dados.
    */
   public function __construct( \ValueObject\Player $playerVO )
   {
      $this->playerVO_ = $playerVO;
   }

   /**
    * Função para clonar um jogador.
    */
   public function __clone()
   {
      $this->playerVO_ = clone $this->getPlayerVO();
   }

   /**
    * Obtém o VO do jogador.
    *
    * @return \ValueObject\Player Objeto contendo as informações do jogador no banco de dados.
    */
   public function getPlayerVO(): \ValueObject\Player
   {
      return $this->playerVO_;
   }
}
