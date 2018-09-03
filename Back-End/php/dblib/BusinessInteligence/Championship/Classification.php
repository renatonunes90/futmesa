<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

/**
 * A responsabilidade desta classe é ter todos os atributos e funções lógicas da classificação de um jogador no campeonato.
 */
class Classification
{

   /**
    *
    * @var Player Jogador.
    */
   private $player_;

   /**
    *
    * @var int Número da rodada.
    */
   private $roundNumber_;

   /**
    *
    * @var int Número de vitórias do jogador.
    */
   private $wins_;

   /**
    *
    * @var int Número de empates do jogador.
    */
   private $ties_;

   /**
    *
    * @var int Número de derrotas do jogador.
    */
   private $losses_;

   /**
    *
    * @var int Número de gols marcados do jogador.
    */
   private $goalsPro_;

   /**
    *
    * @var int Número de gols sofridos do jogador.
    */
   private $goalsCon_;

   /**
    * Construtor padrão.
    *
    * @param int $playerId
    * @param int $roundId
    */
   public function __construct( int $playerId, int $roundNumber )
   {
      $this->player_ = PlayerProvider::getInstance()->getPlayer( $playerId );
      $this->roundNumber_ = $roundNumber;
      $this->wins_ = 0;
      $this->ties_ = 0;
      $this->losses_ = 0;
      $this->goalsPro_ = 0;
      $this->goalsCon_ = 0;
   }

   public function __clone()
   {
      $this->playerId_ = clone $this->playerId_;
      $this->roundNumber_ = clone $this->roundNumber_;
      $this->wins_ = clone $this->wins_;
      $this->ties_ = clone $this->ties_;
      $this->losses_ = clone $this->losses_;
      $this->goalsPro_ = clone $this->goalsPro_;
      $this->goalsCon_ = clone $this->goalsCon_;
   }

   public function addWin(): void
   {
      $this->wins_++;
   }

   public function addTie(): void
   {
      $this->ties_++;
   }

   public function addLoss(): void
   {
      $this->losses_++;
   }

   public function addGoalsPro( int $goals ): void
   {
      $this->goalsPro_ += $goals;
   }

   public function addGoalsCon( int $goals ): void
   {
      $this->goalsCon_ += $goals;
   }

   /**
    *
    * @return int
    */
   public function getNumberOfGames(): int
   {
      return $this->wins_ + $this->ties_ + $this->losses_;
   }

   /**
    *
    * @return int
    */
   public function getPoints(): int
   {
      return $this->wins_ * 3 + $this->ties_;
   }

   /**
    *
    * @return int
    */
   public function getGoalDifference(): int
   {
      return $this->goalsPro_ - $this->goalsCon_;
   }

   /**
    *
    * @return float
    */
   public function getWinRate(): float
   {
      return $this->getPoints() * 100 / ( $this->getNumberOfGames() * 3 );
   }

   /**
    *
    * @return Player
    */
   public function getPlayer(): Player
   {
      return $this->player_;
   }

   /**
    *
    * @return int
    */
   public function getRoundNumber(): int
   {
      return $this->roundNumber_;
   }

   /**
    *
    * @return int
    */
   public function getWins(): int
   {
      return $this->wins_;
   }

   /**
    *
    * @return int
    */
   public function getTies(): int
   {
      return $this->ties_;
   }

   /**
    *
    * @return int
    */
   public function getLosses(): int
   {
      return $this->losses_;
   }

   /**
    *
    * @return int
    */
   public function getGoalsPro(): int
   {
      return $this->goalsPro_;
   }

   /**
    *
    * @return int
    */
   public function getGoalsCon(): int
   {
      return $this->goalsCon_;
   }
}
