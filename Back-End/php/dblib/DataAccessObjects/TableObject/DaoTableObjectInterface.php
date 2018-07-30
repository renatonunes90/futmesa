<?php
/**
 * Projeto PHPSample
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

/**
 * Interface com as funções que uma implementação de DAO de um objeto de table deve possuir.
 */
interface DaoTableObjectInterface
{

   const TABLEOBJECT = "TABLEOBJECT";

   // -- ValueObjects
   // Tabela
   const ID = "ID";

   const NAME = "NAME";

   const DESCRIPTION = "DESCRIPTION";

   /**
    * Busca um objeto na tabela pelo seu identificador no banco de dados.
    *
    * @param int $id
    *           Identificador do objeto na tabela.
    * @return \ValueObject\TableObject|NULL Um objeto com as propriedades do objeto da tabela ou nulo se ele não existir.
    */
   public function getTableObject( int $id ): ?\ValueObject\TableObject;

   /**
    * Busca todos os objetos da tabela do banco de dados.
    *
    * @return array Todos os objetos da tabela do banco de dados.
    */
   public function getAllTableObjects(): array;

   /**
    * Inclui novos objetos na tabela no banco de dados.
    *
    * @param array $objects
    *           Array de objetos \ValueObject\TableObject com os dados a serem inseridos.
    * @return bool Flag indicando se foi possível inserir os objetos.
    */
   public function insertTableObjects( array $objects ): bool;

   /**
    * Atualiza os dados de objetos na tabela.
    *
    * @param array $objects
    *           Array de objetos \ValueObject\TableObject com os dados atualizados.
    * @return bool Flag indicando se foi possível atualizar os objetos.
    */
   public function updateTableObjects( array $objects ): bool;

   /**
    * Deleta objetos através de seus identificadores.
    *
    * @param array $ids
    *           Array de identificadores de objetos.
    * @return bool Flag indicando se foi possível remover os objetos.
    */
   public function deleteTableObjects( array $ids ): bool;
}
