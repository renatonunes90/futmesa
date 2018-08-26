<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

use DAO\DaoGameResultFactory;

require_once "Result.php";
require_once "DataAccessObjects/GameResult/DaoGameResultFactory.php";
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
    *
    * @var \DBLib\Player
    */
   private $player1_;

   /**
    *
    * @var \DBLib\Player
    */
   private $player2_;

   /**
    *
    * @var \DBLib\Result
    */
   private $result_;

   /**
    * Construtor padrão.
    *
    * @param \ValueObject\Game $gameVO
    *           Objeto contendo as informações do jogo no banco de dados.
    */
   public function __construct( \ValueObject\Game $gameVO )
   {
      $this->gameVO_ = $gameVO;
      $this->player1_ = null;
      $this->player2_ = null;
      $this->result_ = null;
   }

   /**
    * Função para clonar um jogo.
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

   /**
    *
    * @return \DbLib\Player
    */
   public function getPlayer1(): \DbLib\Player
   {
      $this->loadPlayers();
      return $this->player1_;
   }

   /**
    *
    * @return \DbLib\Player
    */
   public function getPlayer2(): \DbLib\Player
   {
      $this->loadPlayers();
      return $this->player2_;
   }

   /**
    *
    * @return \DbLib\Result
    */
   public function getResult(): ?\DbLib\Result
   {
      $this->loadResult();
      return $this->result_;
   }

   /**
    *
    * @return \DbLib\Result
    */
   public function setResult( \DbLib\Result $result ): void
   {
      $this->result_ = $result;
   }

   private function loadPlayers( bool $forceReload = false): void
   {
      if ( $this->player1_ == null || $this->player2_ == null || $forceReload )
      {
         $this->player1_ = PlayerProvider::getInstance()->getPlayer( $this->gameVO_->idplayer1 );
         $this->player2_ = PlayerProvider::getInstance()->getPlayer( $this->gameVO_->idplayer2 );
      }
   }

   private function loadResult( bool $forceReload = false): void
   {
      if ( $this->result_ == null || $forceReload )
      {
         $dao = DaoGameResultFactory::getDaoGameResult();
         $result = $dao->getResult( $this->gameVO_->id );
         if ( $result !== null )
         {
            $this->result_ = new Result( $result );
         }
      }
   }
}
