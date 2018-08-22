<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/Game.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de um jogo.
 */
class Game
{

   /**
    *
    * @var \ValueObject\Game VO do jogo.
    */
   private $gameVO_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Game $gameVO
    *           Objeto contendo as informações do jogo no banco de dados.
    */
   public function __construct( \ValueObject\Game $gameVO )
   {
      $this->gameVO_ = $gameVO;
   }

   /**
    * Função para clonar uma rodada.
    */
   public function __clone()
   {
      $this->gameVO_ = clone $this->getGameVO();
   }

   /**
    * Obtém o VO da rodada.
    *
    * @return \ValueObject\Game Objeto contendo as informações da rodada no banco de dados.
    */
   public function getGameVO(): \ValueObject\Game
   {
      return $this->gameVO_;
   }
}
