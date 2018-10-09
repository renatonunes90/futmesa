package com.futmesa.client.request.service;

import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;

public class ServicePlayer extends ServiceAbstract {

	public static final String MODULE = "base";
	public static final String SERVICE = "player";

	public ServicePlayer(ServiceInterface parent) {
		super(parent, MODULE, SERVICE);
	}
	
	public void requestPlayers()
	{
		request( "function=getAllPlayers", "getAllPlayers" );
	}

}
