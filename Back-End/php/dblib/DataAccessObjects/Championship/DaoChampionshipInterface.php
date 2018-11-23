<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

/**
 * Interface com as funções que uma implementação de DAO de um campeonato deve possuir.
 */
interface DaoChampionshipInterface
{
   const CHAMPIONSHIP = "CHAMPIONSHIP";

   // -- ValueObjects
   // Tabela
   const ID = "ID";
   const NAME = "NAME";
   const BASEDATE = "BASEDATE";
   const DATEINCR = "DATEINCR";
   const ROUNDSBYDAY = "ROUNDSBYDAY";
   const GAMESBYROUND = "GAMESBYROUND";

   /**
    * Busca todos os campeonatos do banco de dados.
    *
    * @return array Todos os campeonatos do banco de dados.
    */
   public function getAllChampionships(): array;

   /**
    * Busca todos os participantes de um campeonato.
    * @param int $championshipId Identificador do campeonato.
    * @return array Lista com os identificadores dos participantes.
    */
   public function getParticipants( int $championshipId ) : array;
   
  /**
   * Inclui novos objetos na tabela no banco de dados.
   *
   * @param array $objects
   *           Array de objetos \ValueObject\TableObject com os dados a serem inseridos. 
   * @return bool Flag indicando se foi possível inserir os objetos.
   */
   public function createChampionships( array $championships ) : bool;
   
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
