package com.futmesa.client.request.service;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;

public class ServiceChampionship extends ServiceAbstract {

	public static final String MODULE = "base";
	public static final String SERVICE = "Championship";

	public static final String GET_ALL_CHAMPIONSHIPS = "getAllChampionships";
	public static final String GET_CHAMPIONSHIP = "getChampionship";
	public static final String GET_LAST_CLASSIFICATIONS = "getLastClassifications";
	public static final String GET_ALL_ROUNDS = "getAllRounds";
	public static final String INSERT_RESULTS = "insertResults";
	
	
	public ServiceChampionship(ServiceInterface parent) {
		super(parent, MODULE, SERVICE);
	}
	
	public void requestChampionships()
	{
		request( "function=" + GET_ALL_CHAMPIONSHIPS, GET_ALL_CHAMPIONSHIPS );
	}
	
	public void requestChampionship( int id )
   {
      List<String> params = new ArrayList<String>();
      params.add( "id=" + String.valueOf( id ) );
      params.add( "function=" + GET_CHAMPIONSHIP );
      request( params, GET_CHAMPIONSHIP );
   }
	  
	public void requestClassification( int id )
	{
		List<String> params = new ArrayList<String>();
		params.add( "id=" + String.valueOf( id ) );
		params.add( "function=" + GET_LAST_CLASSIFICATIONS );
		request( params, GET_LAST_CLASSIFICATIONS );
	}
	
	public void requestAllRounds( int id )
	{
		List<String> params = new ArrayList<String>();
		params.add( "id=" + String.valueOf( id ) );
		params.add( "function=" + GET_ALL_ROUNDS );
		request( params, GET_ALL_ROUNDS );
	}
	
	@SuppressWarnings("unchecked")
	public void insertResults( int id, List<Game> games )
	{
		JsArray<Game> js = (JsArray<Game>) JavaScriptObject.createArray();
		for ( Game g : games ) {
			js.push( g );
		}
		
		List<String> params = new ArrayList<String>();
		params.add( "id=" + String.valueOf( id ) );
		params.add( "results=" + JsonUtils.stringify(js) );
		params.add( "function=" + INSERT_RESULTS );
		request( params, INSERT_RESULTS );
	}

}
