<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "BusinessInteligence/TableObject/TableObject.php";
require_once "DataAccessObjects/TableObject/DaoTableObjectFactory.php";

/**
 * A responsabilidade desta classe é manter uma instância única para acessar o DataAccessObject de objetos e realizar a camada
 * de inteligência que transforma um objeto do banco de dados em um objeto com sentido lógico no programa.
 */
class TableObjectProvider
{

   /**
    *
    * @var \DAO\DaoTableObjectInterface Instância do DAO a ser usada pelo provider.
    */
   private $daoTableObject_;

   /**
    *
    * @var array Lista de todos os objetos possíveis com os dados utilizados.
    */
   private $allObjects_;

   /**
    *
    * @var TableObjectProvider Singleton do TableObjectProvider para evitar carregamentos desnecessários.
    */
   private static $instance_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {
      $this->daoTableObject_ = \DAO\DaoTableObjectFactory::getDaoTableObject();
   }

   /**
    * Retorna o singleton do provider.
    *
    * @return TableObjectProvider Instância do provider.
    */
   public static function getInstance(): TableObjectProvider
   {
      if( !self::$instance_ )
      {
         self::$instance_ = new self();
      }

      return self::$instance_;
   }

   /**
    * Busca um objeto pelo seu identificador.
    *
    * @param int $id
    *           Identificador do objeto.
    * @return TableObject|NULL Um objeto ou nulo se ele não existir.
    */
   public function getTableObject( int $id ): ?TableObject
   {
      $this->loadAllObjects();
      return $this->allObjects_[ $id ];
   }

   /**
    * Busca uma lista de objetos pelos seus identificadores.
    *
    * @param array $ids
    *           Identificadores dos objetos.
    * @return array Array de objetos.
    */
   public function getTableObjects( array $ids ): array
   {
      $objects = array ();
      $this->loadAllObjects();

      foreach( $ids as &$id )
      {
         if( isset( $this->allObjects_[ $id ] ) )
         {
            $objects[] = $this->allObjects_[ $id ];
         }
      }

      return $objects;
   }

   /**
    * Busca todos os objetos.
    *
    * @return array Lista de objetos que existem no banco de dados.
    */
   public function getAllObjects(): array
   {
      $this->loadAllObjects();
      return $this->allObjects_;
   }

   /**
    * Retorna a quantidade de objetos existentes.
    *
    * @return int A quantidade de objetos no banco.
    */
   public function getObjectCount(): int
   {
      return count( $this->getAllObjects() );
   }

   /**
    * Limpa todos os objetos carregados.
    */
   public function clearObjects(): void
   {
      $this->allObjects_ = null;
   }

   /**
    * Insere um novo objeto.
    *
    * @param array $objects
    *           Lista de objetos a serem inseridos.
    * @return bool Indica se foi possível inserir o objeto ou não.
    */
   public function insertTableObjects( array $objects ): bool
   {
      return $this->daoTableObject_->insertTableObjects( $objects );
   }

   /**
    * Atualiza os dados de um objeto no banco de dados.
    *
    * @param array $objects
    *           Lista de objetos a serem atualizados.
    * @return bool Indica se foi possível atualizar ou não.
    */
   public function updateTableObjects( array $objects ): bool
   {
      return $this->daoTableObject_->updateTableObjects( $objects );
   }

   /**
    * Remove um array de objetos a partir de seus IDs.
    *
    * @param array $ids
    *           Array de identificadores de objetos.
    * @return int A quantidade de objetos removidos.
    */
   public function deleteWebUsers( array $ids ): int
   {
      return $this->daoTableObject_->deleteTableObjects( $ids );
   }

   /**
    * Carrega um mapa de todos os objetos.
    *
    * @param bool $forceReload
    *           Se eles já estiverem carregados, não serão novamente se a flag não for true.
    */
   private function loadAllObjects( bool $forceReload = false): void
   {
      if( $this->allObjects_ == null || $forceReload )
      {
         $this->allObjects_ = array ();
         $objectsVO = $this->daoTableObject_->getAllTableObjects();

         foreach( $objectsVO as &$obj )
         {
            $this->allObjects_[ $obj->id ] = new TableObject( $obj, true );
         }
      }
   }
}
?>