<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
require_once "BusinessInteligence/Championship/Classification.php";

require_once "dto/DtoPlayer.php";

/**
 * A responsabilidade desta classe é encapsular os atributos de uma classificação de jogador para retonar em uma requisição HTTP.
 */
class DtoClassification
{

   /**
    *
    * @var DtoPlayer Jogador.
    */
   public $player;

   /**
    *
    * @var int Número da rodada.
    */
   public $roundNumber;

   /**
    *
    * @var int Número de vitórias do jogador.
    */
   public $wins;

   /**
    *
    * @var int Número de empates do jogador.
    */
   public $ties;

   /**
    *
    * @var int Número de derrotas do jogador.
    */
   public $losses;

   /**
    *
    * @var int Número de gols marcados do jogador.
    */
   public $goalsPro;

   /**
    *
    * @var int Número de gols sofridos do jogador.
    */
   public $goalsCon;

   /**
    *
    * @var int Número de partidas jogadas pelo jogador.
    */
   public $numberOfGames;
   
   /**
    * 
    * @var int Número de pontos do jogador.
    */
   public $points;
   
   /**
    * 
    * @var int Percentual de aproveitamento do jogador.
    */
   public $winRate;
   
   /**
    * Construtor padrão.
    *
    * @param \DbLib\Classification $classification
    *           Objeto retornado da DBLib contendo as informações de uma classificação.
    */
   public function __construct( \DbLib\Classification $classification )
   {
      $this->player = new DtoPlayer( $classification->getPlayer() );
      $this->roundNumber = $classification->getRoundNumber();
      $this->wins = $classification->getWins();
      $this->ties = $classification->getTies();
      $this->losses = $classification->getLosses();
      $this->goalsPro = $classification->getGoalsPro();
      $this->goalsCon = $classification->getGoalsCon();
      $this->numberOfGames = $classification->getNumberOfGames();
      $this->points = $classification->getPoints();
      $this->winRate = $classification->getWinRate();
   }

   public function __clone()
   {
      $this->player = clone $this->getPlayer();
   }
   
   /**
    *
    * @return DtoPlayer
    */
   public function getPlayer(): DtoPlayer
   {
      return $this->player;
   }
}
