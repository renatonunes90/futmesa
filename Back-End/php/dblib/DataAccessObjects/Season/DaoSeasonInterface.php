<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

/**
 * Interface com as funções que uma implementação de DAO de uma temporada deve possuir.
 */
interface DaoSeasonInterface
{
   const SEASON = "SEASON";

   // -- ValueObjects
   // Tabela
   const ID = "ID";
   const NAME = "NAME";

   /**
    * Busca todos as temporadas do banco de dados.
    *
    * @return array Todas as temporadas do banco de dados.
    */
   public function getAllSeasons(): array;

/**
 * Inclui novos objetos na tabela no banco de dados.
 *
 * @param array $objects
 *           Array de objetos \ValueObject\TableObject com os dados a serem inseridos.
 * @return bool Flag indicando se foi possível inserir os objetos.
 */
   // public function insertTableObjects( array $objects ): bool;

/**
 * Atualiza os dados de objetos na tabela.
 *
 * @param array $objects
 *           Array de objetos \ValueObject\TableObject com os dados atualizados.
 * @return bool Flag indicando se foi possível atualizar os objetos.
 */
   // public function updateTableObjects( array $objects ): bool;

/**
 * Deleta objetos através de seus identificadores.
 *
 * @param array $ids
 *           Array de identificadores de objetos.
 * @return bool Flag indicando se foi possível remover os objetos.
 */
   // public function deleteTableObjects( array $ids ): bool;
}
