<?php
/**
 * Projeto FutMesaBackEnd
 *
 * @copyright : Renato Martins Barbieri Nunes
 * @version 0.1
 */
use DBLib\ChampionshipProvider;
use DBLib\EvalClassification;

require_once "dto/DtoClassification.php";

class ServiceGroups
{

    private $provider_;

    public function __construct()
    {
        $this->provider_ = ChampionshipProvider::getInstance();
    }

    public function getLastClassifications(int $idChampionship, int $phaseNumber, int $idGroup): array
    {
        $result = array();

        $championship = $this->provider_->getChampionship($idChampionship);
        if ($championship != null) {
            $phase = $championship->getPhase($phaseNumber);
            if ($phase != null) {
                $group = $phase->getGroup($idGroup);

                $evalClassification = new EvalClassification();
                $classification = $evalClassification->getClassification($group->getRounds(), $group->getMembers());
                foreach ($classification as $c) {
                    $result[] = new DtoClassification($c);
                }
            }
        }

        return $result;
    }
}

?>