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
interface DaoGroupsInterface
{
   const GROUPS = "GROUPS";

   const ID = "ID";
   const IDCHAMPIONSHIP = "IDCHAMPIONSHIP";
   const IDPHASE = "IDPHASE";
   const NAME = "NAME";

   /**
    * Get all rounds by group.
    *
    * @param int $championshipId
    * @param int $phaseId
    * 
    * @return array Map of groups indexed by id.
    */
   public function getAllGroups( int $championshipId, int $phaseId ): array;
}
