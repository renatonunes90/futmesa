<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/Round.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de uma rodada.
 */
class Round
{

   /**
    *
    * @var \ValueObject\Round VO da rodada.
    */
   private $roundVO_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Round $roundVO
    *           Objeto contendo as informações da rodada no banco de dados.
    */
   public function __construct( \ValueObject\Round $roundVO )
   {
      $this->roundVO_ = $roundVO;
   }

   /**
    * Função para clonar uma rodada.
    */
   public function __clone()
   {
      $this->roundVO_ = clone $this->getRoundVO();
   }

   /**
    * Obtém o VO da rodada.
    *
    * @return \ValueObject\Round Objeto contendo as informações da rodada no banco de dados.
    */
   public function getRoundVO(): \ValueObject\Round
   {
      return $this->roundVO_;
   }
}
