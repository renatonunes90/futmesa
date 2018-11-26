<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "ValueObjects/Season.php";

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas de acesso aos dados de uma temporada.
 */
class Season
{

   /**
    *
    * @var \ValueObject\Season VO da temporada.
    */
   private $seasonVO_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Season $seasonVO
    *           Objeto contendo as informações da temporada no banco de dados.
    */
   public function __construct( \ValueObject\Season $seasonVO )
   {
      $this->seasonVO_ = $seasonVO;
   }

   /**
    * Função para clonar um jogador.
    */
   public function __clone()
   {
      $this->seasonVO_ = clone $this->getSeasonVO();
   }

   /**
    * Obtém o VO da temporada.
    *
    * @return \ValueObject\Season Objeto contendo as informações da temporada no banco de dados.
    */
   public function getSeasonVO(): \ValueObject\Season
   {
      return $this->seasonVO_;
   }
}
