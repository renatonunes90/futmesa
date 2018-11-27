package com.futmesa.client.request.service;

import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;

public class ServiceSeason extends ServiceAbstract {

	public static final String MODULE = "base";
	public static final String SERVICE = "Season";

	public static final String GET_ALL_SEASONS = "getAllSeasons";
	
	public ServiceSeason(ServiceInterface parent) 
	{
		super(parent, MODULE, SERVICE);
	}
	
	public void requestSeasons()
	{
		request( "function=" + GET_ALL_SEASONS, GET_ALL_SEASONS );
	}
}
