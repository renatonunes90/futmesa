<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoChampionshipFactory;

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
   private $championshipVO_;

   /**
    * 
    * @var array Mapa de objetos do tipo Player com os participantes do campeonato.
    */
   private $players_;
   
   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Championship $championshipVO
    *           Objeto contendo as informações do campeonato no banco de dados.
    */
   public function __construct( \ValueObject\Championship $championshipVO )
   {
      $this->championshipVO_ = $championshipVO;
      $this->players_ = array();
   }

   /**
    * Função para clonar um campeonato.
    */
   public function __clone()
   {
      $this->championshipVO_ = clone $this->getChampionshipVO();
      $newPlayers = array();
      foreach ( $this->players_ as $p )
      {
         $newPlayers = clone $p;
      }
      $this->players_ = $newPlayers;
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
   
   /**
    * 
    * @return array Lista de objetos de tipo Player que participam do campeonato.
    */
   public function getPlayers() : array
   {      
      $this->loadPlayers();
      return $this->players_;
   }
   
   private function loadPlayers( bool $forceReload = false ) : void
   {
      if ( $this->players_ == null || $forceReload )
      {
         $dao = DaoChampionshipFactory::getDaoChampionship();
         $participantIds = $dao->getParticipants( $this->championshipVO_->id );
         $this->players_ = PlayerProvider::getInstance()->getPlayers( $participantIds );
      }
   }
}
