package com.futmesa.client.module.main;

import com.futmesa.client.FutMesaConsts;
import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.module.main.viewport.classification.ClassificationViewport;
import com.futmesa.client.request.base.RequestRecord;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.ServicePlayer;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.shared.GWT;


public class MainModule extends ModuleInterface implements ServiceInterface
{
   /**
    * Constantes de mensagens.
    */
   private FutMesaConsts consts = GWT.create( FutMesaConsts.class );

   private ServiceChampionship serviceChampionship;
   
   /**
    * Construtor padrão.
    */
   public MainModule()
   {
	   serviceChampionship = new ServiceChampionship( this );
      super.addMenu( consts.examplePage(), "module=main" );
      super.addMenu( consts.exampleTables(), "module=main&panel=table" );
   }

   @Override
   public void updatePanel( FilterConfig filter )
   {
      serviceChampionship.requestClassification(1);
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
		if ( ServiceChampionship.GET_LAST_CLASSIFICATIONS.equals( requestId ) )
		{
			 ClassificationViewport v = null;
	         v = new ClassificationViewport();
	         JsArray<Classification> classification = records.cast();
	         v.updateClassification( classification );

		      BaseViewport.getInstance().setViewportContent( v );
		      
		}
	}
	
	@Override
	public void unmask(String requestId) {
		
	}
}
