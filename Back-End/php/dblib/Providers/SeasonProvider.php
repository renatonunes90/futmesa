<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "BusinessInteligence/Season/Season.php";
require_once "DataAccessObjects/Season/DaoSeasonFactory.php";

/**
 * A responsabilidade desta classe é manter uma instância única para acessar o DataAccessObject de temproadas e realizar a camada
 * de inteligência que transforma um objeto do banco de dados em um objeto com sentido lógico no programa.
 */
class SeasonProvider
{

   /**
    *
    * @var \DAO\DaoSeasonInterface Instância do DAO a ser usada pelo provider.
    */
   private $daoSeason_;

   /**
    *
    * @var array Lista de todas as temporadas do banco de dados.
    */
   private $allSeasons_;

   /**
    *
    * @var SeasonProvider Singleton do SeasonProvider para evitar carregamentos desnecessários.
    */
   private static $instance_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {
      $this->daoSeason_ = \DAO\DaoSeasonFactory::getDaoSeason();
   }

   /**
    * Retorna o singleton do provider.
    *
    * @return SeasonProvider Instância do provider.
    */
   public static function getInstance(): SeasonProvider
   {
      if ( !self::$instance_ )
      {
         self::$instance_ = new self();
      }

      return self::$instance_;
   }

   /**
    * Busca uma temporada pelo seu identificador.
    *
    * @param int $id
    *           Identificador da temporada.
    * @return Season|NULL Uma temporada ou nulo se ela não existir.
    */
   public function getSeason( int $id ): ?Season
   {
      $this->loadAllSeasons();
      return array_key_exists( $id, $this->allSeasons_ ) ? $this->allSeasons_[ $id ] : null;
   }

   /**
    * Busca uma lista de temporadas pelos seus identificadores.
    *
    * @param array $ids
    *           Identificadores das temporadas.
    * @return array Mapa de objetos indexados pelos seus identificadores.
    */
   public function getSeasons( array $ids ): array
   {
      $objects = array ();
      $this->loadAllSeasons();

      foreach ( $ids as &$id )
      {
         if ( isset( $this->allSeasons_[ $id ] ) )
         {
            $objects[ $id ] = $this->allSeasons_[ $id ];
         }
      }

      return $objects;
   }

   /**
    * Busca todas as temporadas.
    *
    * @return array Lista de temporadas que existem no banco de dados.
    */
   public function getAllSeasons(): array
   {
      $this->loadAllSeasons();
      return $this->allSeasons_;
   }

   /**
    * Limpa todos os objetos carregados.
    */
   public function clearSeasons(): void
   {
      $this->allSeasons_ = null;
   }

   /**
    * Insere um novo objeto.
    *
    * @param array $objects
    *           Lista de objetos a serem inseridos.
    * @return bool Indica se foi possível inserir o objeto ou não.
    */
   // public function insertTableObjects( array $objects ): bool
   // {
   // return $this->daoTableObject_->insertTableObjects( $objects );
   // }

   /**
    * Atualiza os dados de um objeto no banco de dados.
    *
    * @param array $objects
    *           Lista de objetos a serem atualizados.
    * @return bool Indica se foi possível atualizar ou não.
    */
   // public function updateTableObjects( array $objects ): bool
   // {
   // return $this->daoTableObject_->updateTableObjects( $objects );
   // }

   /**
    * Remove um array de objetos a partir de seus IDs.
    *
    * @param array $ids
    *           Array de identificadores de objetos.
    * @return int A quantidade de objetos removidos.
    */
   // public function deleteWebUsers( array $ids ): int
   // {
   // return $this->daoTableObject_->deleteTableObjects( $ids );
   // }

   /**
    * Carrega um mapa de todas as temporadas.
    *
    * @param bool $forceReload
    *           Se eles já estiverem carregados, não serão novamente se a flag não for true.
    */
   private function loadAllSeasons( bool $forceReload = false): void
   {
      if ( $this->allSeasons_ == null || $forceReload )
      {
         $this->allSeasons_ = array ();
         $objectsVO = $this->daoSeason_->getAllSeasons();

         foreach ( $objectsVO as &$obj )
         {
            $this->allSeasons_[ $obj->id ] = new Season( $obj );
         }
      }
   }
}
?>