package com.futmesa.client.module.main;

import com.futmesa.client.FutMesaConsts;
import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.module.main.viewport.classification.ClassificationViewport;
import com.futmesa.client.request.service.ServicePlayer;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;


public class MainModule extends ModuleInterface implements ServiceInterface
{
   /**
    * Constantes de mensagens.
    */
   private FutMesaConsts consts = GWT.create( FutMesaConsts.class );

   private ServicePlayer servicePlayer;
   
   /**
    * Construtor padrão.
    */
   public MainModule()
   {
	   servicePlayer = new ServicePlayer( this );
      super.addMenu( consts.examplePage(), "module=main" );
      super.addMenu( consts.exampleTables(), "module=main&panel=table" );
   }

   @Override
   public void updatePanel( FilterConfig filter )
   {
      servicePlayer.requestPlayers();
   }

   @Override
   public String getModuleName()
   {
      return consts.moduleName();
   }

	@Override
	public void mask(String requestId) {
		
	}
	
	@Override
	public void onServiceResult(JavaScriptObject records, String requestId) {
		if ( "getAllPlayers".equals( requestId ) )
		{
		      ViewportInterface v = null;
	         v = new ClassificationViewport();
	         

		      BaseViewport.getInstance().setViewportContent( v );
		      
		}
	}
	
	@Override
	public void unmask(String requestId) {
		
	}
}
