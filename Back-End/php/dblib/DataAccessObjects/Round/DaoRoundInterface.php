<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

/**
 * Interface com as funções que uma implementação de DAO de um rodada deve possuir.
 */
interface DaoRoundInterface
{
   const ROUND = "ROUND";

   // -- ValueObjects
   // Tabela
   const ID = "ID";
   const IDCHAMPIONSHIP = "IDCHAMPIONSHIP";
   const PHASE = "PHASE";
   const BASEDATE = "BASEDATE";
   const BASEHOUR = "BASEHOUR";
   const NUMBER = "NUMBER";

   /**
    * Busca todos as rodadas de um campeonato do banco de dados.
    *
    * @param int $championshipId
    * @return array Mapa de objetos do tipo Round indexados pelo seu identificador.
    */
   public function getAllRounds( int $championshipId ): array;

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
