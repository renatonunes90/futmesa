package com.futmesa.client.request.service;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;

public class ServiceChampionship extends ServiceAbstract {

	public static final String MODULE = "base";
	public static final String SERVICE = "Championship";

	public static final String GET_LAST_CLASSIFICATIONS = "getLastClassifications";
	
	public ServiceChampionship(ServiceInterface parent) {
		super(parent, MODULE, SERVICE);
	}
	
	public void requestClassification( int id )
	{
		List<String> params = new ArrayList<String>();
		params.add( "id=" + String.valueOf( id ) );
		params.add( "function=" + GET_LAST_CLASSIFICATIONS );
		request( params, GET_LAST_CLASSIFICATIONS );
	}

}
