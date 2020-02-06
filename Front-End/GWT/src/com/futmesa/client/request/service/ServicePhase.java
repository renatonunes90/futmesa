package com.futmesa.client.request.service;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;

public class ServicePhase extends ServiceAbstract {

	public static final String MODULE = "base";
	public static final String SERVICE = "phase";

	public static final String GET_PHASE_INFO = "getPhaseInfoByNumber";
	public static final String GET_LAST_CLASSIFICATIONS = "getLastClassifications";

	public ServicePhase(ServiceInterface parent) {
		super(parent, MODULE, SERVICE);
	}

	public void requestPhaseCompleteInfo(int idChampionship, int phaseNumber) {
		List<String> params = new ArrayList<String>();
		params.add("idChampionship=" + String.valueOf(idChampionship));
		params.add("phaseNumber=" + String.valueOf(phaseNumber));
		params.add("function=" + GET_PHASE_INFO);
		request(params, GET_PHASE_INFO);
	}

	public void requestClassification(int idChampionship, int phaseNumber) {
		List<String> params = new ArrayList<String>();
		params.add("idChampionship=" + String.valueOf(idChampionship));
		params.add("phaseNumber=" + String.valueOf(phaseNumber));
		params.add("function=" + GET_LAST_CLASSIFICATIONS);
		request(params, GET_LAST_CLASSIFICATIONS);
	}

}
