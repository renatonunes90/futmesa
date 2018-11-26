package com.futmesa.client.request.service.config;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.request.service.base.ServiceAbstract;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.google.gwt.core.client.JsonUtils;

public class ServiceCRUDChampionship extends ServiceAbstract {

	public static final String MODULE = "config";
	public static final String SERVICE = "CRUDChampionship";

	public static final String CREATE_CHAMPIONSHIP = "createChampionship";
	public static final String DELETE_CHAMPIONSHIP = "deleteChampionship";
   public static final String UPDATE_CHAMPIONSHIP = "updateChampionship";
	
	public ServiceCRUDChampionship(ServiceInterface parent) {
		super(parent, MODULE, SERVICE);
	}
	
	public void createChampionship( Championship championship )
	{
		List<String> params = new ArrayList<String>();
		params.add( "championship=" + JsonUtils.stringify(championship) );
		params.add( "function=" + CREATE_CHAMPIONSHIP );
		request( params, CREATE_CHAMPIONSHIP );
	}

   public void deleteChampionship( int id )
   {
      List< String > params = new ArrayList< String >();
      params.add( "id=" + String.valueOf( id ) );
      params.add( "function=" + DELETE_CHAMPIONSHIP );
      request( params, DELETE_CHAMPIONSHIP );
   }
   
   public void updateChampionship( Championship championship )
   {
      List<String> params = new ArrayList<String>();
      params.add( "championship=" + JsonUtils.stringify(championship) );
      params.add( "function=" + CREATE_CHAMPIONSHIP );
      request( params, CREATE_CHAMPIONSHIP );
   }
	  
}
