<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

/**
 * Interface com as funções que uma implementação de DAO de um resultado jogo deve possuir.
 */
interface DaoGameResultInterface
{
   const GAMERESULT = "GAMERESULT";

   // -- ValueObjects
   // Tabela
   const IDGAME = "IDGAME";
   const SCORE1 = "SCORE1";
   const SCORE2 = "SCORE2";
   const INPUTDATE = "INPUTDATE";
   const IDWINNER = "IDWINNER";

   /**
    * Busca todos os resultados dos jogos de um campeonato do banco de dados.
    *
    * @param int $championshipId
    * @return array Lista de objetos do tipo GameResult indexados pelo identificador do seu jogo.
    */
   public function getAllResults( int $championshipId ): array;

   /**
    * Busca o resultado de um jogo específico do banco de dados.
    *
    * @param int $gameId
    * @return ?\ValueObject\GameResult Resultado ou null se ele não existir.
    */
   public function getResult( int $gameId ): ?\ValueObject\GameResult;

   /**
    * Inclui um novo resultado no banco de dados.
    *
    * @param \ValueObject\GameResult $result
    * @return bool Flag indicando se foi possível inserir o resultado.
    */
   public function insertResult( \ValueObject\GameResult $result ): bool;

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
