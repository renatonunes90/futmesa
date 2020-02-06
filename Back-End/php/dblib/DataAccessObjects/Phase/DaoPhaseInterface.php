<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
namespace DAO;

/**
 */
interface DaoPhaseInterface
{
   const PHASE = "PHASE";

   const ID = "ID";
   const IDCHAMPIONSHIP = "IDCHAMPIONSHIP";
   const TYPE = "TYPE";
   const NUMBER = "NUMBER";

   /**
    * Get all rounds by championship.
    *
    * @param int $championshipId
    * @return array Map of phases indexed by id.
    */
   public function getAllPhases( int $championshipId ): array;
}
