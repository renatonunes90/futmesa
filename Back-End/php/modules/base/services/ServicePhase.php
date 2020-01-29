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
require_once "dto/DtoPhase.php";

class ServicePhase
{

    private $provider_;

    public function __construct()
    {
        $this->provider_ = ChampionshipProvider::getInstance();
    }

    public function getAllPhases(int $idChampionship): array
    {
        $result = array();

        $championship = $this->provider_->getChampionship($idChampionship);
        if ($championship != null) {
            $phases = $championship->getPhases();
            foreach ($phases as $p) {
                $result[] = $p->getPhaseVO();
            }
        }

        return $result;
    }

    public function getAllPhasesInfo(int $idChampionship): array
    {
        $result = array();

        $championship = $this->provider_->getChampionship($idChampionship);
        if ($championship != null) {
            $phases = $championship->getPhases();
            foreach ($phases as $p) {
                $result[] = new DtoPhase($p);
            }
        }

        return $result;
    }

    public function getLastClassifications(int $idChampionship, int $phaseNumber): array
    {
        $result = array();
        
        $championship = $this->provider_->getChampionship($idChampionship);
        if ($championship != null) {
            $phase = $championship->getPhase($phaseNumber);
            if ($phase != null) {                
                $evalClassification = new EvalClassification();
                $classification = $evalClassification->getClassification($phase->getRounds(), $championship->getPlayers());
                foreach ($classification as $c) {
                    $result[] = new DtoClassification($c);
                }
            }
        }
        
        return $result;
    }
    
    public function getPhaseByNumber(int $idChampionship, int $phaseNumber): ?ValueObject\Phase
    {
        $result = array();

        $championship = $this->provider_->getChampionship($idChampionship);
        if ($championship != null) {
            $phase = $championship->getPhase($phaseNumber);
            if ($phase != null) {
                $result = $phase->getPhaseVO();
            }
        }

        return $result;
    }

    public function getPhaseInfoByNumber(int $idChampionship, int $phaseNumber): ?DtoPhase
    {
        $result = null;

        $championship = $this->provider_->getChampionship($idChampionship);
        if ($championship != null) {
            $phase = $championship->getPhase($phaseNumber);
            if ($phase != null) {
                $result = new DtoPhase($phase);
            }
        }

        return $result;
    }
}

?>