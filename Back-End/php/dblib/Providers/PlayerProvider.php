<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DBLib;

require_once "BusinessInteligence/Player/Player.php";
require_once "DataAccessObjects/Player/DaoPlayerFactory.php";

/**
 * A responsabilidade desta classe é manter uma instância única para acessar o DataAccessObject de jogadores e realizar a camada
 * de inteligência que transforma um objeto do banco de dados em um objeto com sentido lógico no programa.
 */
class PlayerProvider
{

   /**
    *
    * @var \DAO\DaoPlayerInterface Instância do DAO a ser usada pelo provider.
    */
   private $daoPlayer_;

   /**
    *
    * @var array Lista de todos os jogadores do banco de dados.
    */
   private $allPlayers_;

   /**
    *
    * @var PlayerProvider Singleton do PlayerProvider para evitar carregamentos desnecessários.
    */
   private static $instance_;

   /**
    * Construtor privado para não ser usado.
    */
   private function __construct()
   {
      $this->daoPlayer_ = \DAO\DaoPlayerFactory::getDaoPlayer();
   }

   /**
    * Retorna o singleton do provider.
    *
    * @return PlayerProvider Instância do provider.
    */
   public static function getInstance(): PlayerProvider
   {
      if ( !self::$instance_ )
      {
         self::$instance_ = new self();
      }

      return self::$instance_;
   }

   /**
    * Busca um jogador pelo seu identificador.
    *
    * @param int $id
    *           Identificador do jogador.
    * @return Player|NULL Um jogador ou nulo se ele não existir.
    */
   public function getPlayer( int $id ): ?Player
   {
      $this->loadAllPlayers();
      return array_key_exists( $id, $this->allPlayers_ ) ? $this->allPlayers_[ $id ] : null;
   }
   
   /**
    * Search for a player by name.
    *
    * @param string $name
    * @return Player|NULL A plyer or null if doesn't found.
    */
   public function getPlayerByName(string $name ): ?Player
   {
       $this->loadAllPlayers();
       $result = array_filter($this->allPlayers_ , function($p) use ($name)  {
           return $p->getPlayerVO()->name === $name;
       });
       return sizeOf($result) > 0 ? array_pop($result) : null;
   }

   /**
    * Busca uma lista de jogadores pelos seus identificadores.
    *
    * @param array $ids
    *           Identificadores dos jogadores.
    * @return array Mapa de objetos indexados pelos seus identificadores.
    */
   public function getPlayers( array $ids ): array
   {
      $objects = array ();
      $this->loadAllPlayers();

      foreach ( $ids as &$id )
      {
         if ( isset( $this->allPlayers_[ $id ] ) )
         {
            $objects[ $id ] = $this->allPlayers_[ $id ];
         }
      }

      return $objects;
   }

   /**
    * Busca todos os jogadores.
    *
    * @return array Lista de jogadores que existem no banco de dados.
    */
   public function getAllPlayers(): array
   {
      $this->loadAllPlayers();
      return $this->allPlayers_;
   }

   /**
    * Limpa todos os objetos carregados.
    */
   public function clearPlayers(): void
   {
      $this->allPlayers_ = null;
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
    * Carrega um mapa de todos os jogadores.
    *
    * @param bool $forceReload
    *           Se eles já estiverem carregados, não serão novamente se a flag não for true.
    */
   private function loadAllPlayers( bool $forceReload = false): void
   {
      if ( $this->allPlayers_ == null || $forceReload )
      {
         $this->allPlayers_ = array ();
         $objectsVO = $this->daoPlayer_->getAllPlayers();

         foreach ( $objectsVO as &$obj )
         {
            $this->allPlayers_[ $obj->id ] = new Player( $obj );
         }
      }
   }
}
?>