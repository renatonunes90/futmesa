package com.futmesa.client.request.service;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;

public class ServiceGroup extends ServiceAbstract {

	public static final String MODULE = "base";
	public static final String SERVICE = "groups";

	public static final String GET_GROUP_CLASSIFICATIONS = "getGroupLastClassification";

	public ServiceGroup(ServiceInterface parent) {
		super(parent, MODULE, SERVICE);
	}

	public void requestClassification(int idChampionship, int phaseNumber, int idGroup, String groupName) {
		List<String> params = new ArrayList<String>();
		params.add("idChampionship=" + String.valueOf(idChampionship));
		params.add("phaseNumber=" + String.valueOf(phaseNumber));
		params.add("idGroup=" + String.valueOf(idGroup));
		params.add("function=getLastClassifications");
		request(params, GET_GROUP_CLASSIFICATIONS + "." + groupName);
	}

}
