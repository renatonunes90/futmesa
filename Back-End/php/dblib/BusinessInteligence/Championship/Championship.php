<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/Championship.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de campeonatos.
 */
class Championship
{

   /**
    *
    * @var \ValueObject\Championship VO do campeonato.
    */
   public $championshipVO_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Championship $championshipVO
    *           Objeto contendo as informações do campeonato no banco de dados.
    */
   public function __construct( \ValueObject\Championship $championshipVO )
   {
      $this->championshipVO_ = $championshipVO;
   }

   /**
    * Função para clonar um campeonato.
    */
   public function __clone()
   {
      $this->championshipVO_ = clone $this->getChampionshipVO();
   }

   /**
    * Obtém o VO do campeonato.
    *
    * @return \ValueObject\Championship Objeto contendo as informações do campeonato no banco de dados.
    */
   public function getChampionshipVO(): \ValueObject\Championship
   {
      return $this->championshipVO_;
   }
}
