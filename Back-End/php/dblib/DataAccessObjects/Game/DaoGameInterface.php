<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

/**
 * Interface com as funções que uma implementação de DAO de um jogo deve possuir.
 */
interface DaoGameInterface
{

    const GAME = "GAME";

    // -- ValueObjects
    // Tabela
    const ID = "ID";

    const IDROUND = "IDROUND";

    const IDPLAYER1 = "IDPLAYER1";

    const IDPLAYER2 = "IDPLAYER2";

    const GAMETABLE = "GAMETABLE";

    const SCORE1 = "SCORE1";

    const SCORE2 = "SCORE2";

    const INPUTDATE = "INPUTDATE";

    const IDWINNER = "IDWINNER";

    /**
     * Busca todos os jogos de um campeonato do banco de dados.
     *
     * @param int $championshipId
     * @return array Mapa de objetos do tipo Game indexados pelo seu identificador.
     */
    public function getAllGames(int $championshipId): array;

    /**
     * Busca todos os jogos de uma rodada de um campeonato do banco de dados.
     *
     * @param int $roundId
     * @return array Mapa de objetos do tipo Game indexados pelo seu identificador.
     */
    public function getAllGamesByRound(int $roundId): array;

    /**
     * Atualiza o resultado de jogo no banco de dados.
     *
     * @param \ValueObject\Game $result
     * @return bool Flag indicando se foi possível atualizar o resultado.
     */
    public function updateResult(\ValueObject\Game $result): bool;

    /**
     * Inclui novos jogos na tabela no banco de dados.
     *
     * @param array $games lista de jogos
     * @return bool Flag indicando se foi possível atualizar o resultado.
     */
    public function insertGames(array $games): bool;

/**
 * Atualiza os dados de objetos na tabela.
 *
 * @param array $objects
 *            Array de objetos \ValueObject\TableObject com os dados atualizados.
 * @return bool Flag indicando se foi possível atualizar os objetos.
 */
    // public function updateTableObjects( array $objects ): bool;

/**
 * Deleta objetos através de seus identificadores.
 *
 * @param array $ids
 *            Array de identificadores de objetos.
 * @return bool Flag indicando se foi possível remover os objetos.
 */
    // public function deleteTableObjects( array $ids ): bool;
}
