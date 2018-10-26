package com.futmesa.client.request.service;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;

public class ServicePlayer extends ServiceAbstract {

	public static final String MODULE = "base";
	public static final String SERVICE = "Player";
	
	public static final String GET_PLAYER = "getPlayer";
	public static final String GET_REVIEW_INFO = "getReviewInfo";
	
	public ServicePlayer(ServiceInterface parent) {
		super(parent, MODULE, SERVICE);
	}
	
	public void requestPlayers()
	{
		request( "function=getAllPlayers", "getAllPlayers" );
	}
	
	public void requestPlayer( int id )
	{
		List<String> params = new ArrayList<String>();
		params.add( "id=" + String.valueOf( id ) );
		params.add( "function=" + GET_PLAYER );
		request( params, GET_PLAYER );
	}
	
	public void requestReviewInfo( int id )
	{
		List<String> params = new ArrayList<String>();
		params.add( "id=" + String.valueOf( id ) );
		params.add( "function=" + GET_REVIEW_INFO );
		request( params, GET_REVIEW_INFO );
	}

}
