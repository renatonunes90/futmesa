<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "BusinessInteligence/Championship/Championship.php";
require_once "DataAccessObjects/Championship/DaoChampionshipFactory.php";

/**
 * A responsabilidade desta classe é manter uma instância única para acessar o DataAccessObject de campeonatos e realizar a camada
 * de inteligência que transforma um objeto do banco de dados em um objeto com sentido lógico no programa.
 */
class ChampionshipProvider
{

   /**
    *
    * @var \DAO\DaoChampionshipInterface Instância do DAO a ser usada pelo provider.
    */
   private $daoChampionship_;

   /**
    *
    * @var array Lista de todos os campeonatos do banco de dados.
    */
   private $allChampionships_;

   /**
    *
    * @var ChampionshipProvider Singleton do ChampionshipProvider para evitar carregamentos desnecessários.
    */
   private static $instance_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {
      $this->daoChampionship_ = \DAO\DaoChampionshipFactory::getDaoChampionship();
   }

   /**
    * Retorna o singleton do provider.
    *
    * @return ChampionshipProvider Instância do provider.
    */
   public static function getInstance(): ChampionshipProvider
   {
      if( !self::$instance_ )
      {
         self::$instance_ = new self();
      }

      return self::$instance_;
   }

   /**
    * Busca um campeonato pelo seu identificador.
    *
    * @param int $id
    *           Identificador do campeonato.
    * @return Championship|NULL Um campeonato ou nulo se ele não existir.
    */
   public function getChampionship( int $id ): ?Championship
   {
      $this->loadAllChampionships();
      return array_key_exists( $id, $this->allChampionships_ ) ? $this->allChampionships_[ $id ] : null;
   }

   /**
    * Busca uma lista de campeonatos pelos seus identificadores.
    *
    * @param array $ids
    *           Identificadores dos campeonatos.
    * @return array Array de objetos.
    */
   public function getChampionships( array $ids ): array
   {
      $objects = array ();
      $this->loadAllChampionships();

      foreach( $ids as &$id )
      {
         if( isset( $this->allChampionships_[ $id ] ) )
         {
            $objects[] = $this->allChampionships_[ $id ];
         }
      }

      return $objects;
   }

   /**
    * Busca todos os campeonatos.
    *
    * @return array Lista de campeonatos que existem no banco de dados.
    */
   public function getAllChampionships(): array
   {
      $this->loadAllChampionships();
      return $this->allChampionships_;
   }

   /**
    * Limpa todos os objetos carregados.
    */
   public function clearChampionships(): void
   {
      $this->allChampionships_ = null;
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
    * Carrega um mapa de todos os campeonatos.
    *
    * @param bool $forceReload
    *           Se eles já estiverem carregados, não serão novamente se a flag não for true.
    */
   private function loadAllChampionships( bool $forceReload = false): void
   {
      if( $this->allChampionships_ == null || $forceReload )
      {
         $this->allChampionships_ = array ();
         $objectsVO = $this->daoChampionship_->getAllChampionships();

         foreach( $objectsVO as &$obj )
         {
            $this->allChampionships_[ $obj->id ] = new Championship( $obj );
         }
      }
   }
}
?>