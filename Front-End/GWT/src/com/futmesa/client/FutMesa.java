package com.futmesa.client;

import com.futmesa.client.base.URLFilter;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.module.main.MainModule;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.ServicePlayer;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;


/**
 */
public class FutMesa implements EntryPoint, ServiceInterface
{

   private ServiceChampionship serviceChampionship;

   private ServicePlayer servicePlayer;
   
   private MainModule mainModule;

   /**
    * Construtor padrão.
    */
   public FutMesa()
   {
      // Não faz nada por enquanto...
   }

   /**
    */
   public void onModuleLoad()
   {
      //AuthWindow.getInstance().checkAuth();
      serviceChampionship = new ServiceChampionship( this );
      serviceChampionship.requestChampionships();
   }

   /**
    * Inicializa a viewport principal da ferramenta.
    */
   private void initializeViewport()
   {
      RootLayoutPanel.get().add( BaseViewport.getInstance() );

      // listener para chamar o update da viewport quando a url é alterada
      History.addValueChangeHandler( handler -> updateViewport() );

      mainModule = new MainModule();
      BaseViewport.getInstance().addModule( mainModule );
      this.updateViewport();
   }
   
   public void updateViewport()
   {
      URLFilter filter = new URLFilter( Window.Location.getQueryString() );
      String moduleFilter = filter.getFilter( URLFilter.MODULE );
      // if ( moduleFilter.equalsIgnoreCase( "sample" ) )
         mainModule.updatePanel( filter );
      // else
      // // módulo default
      // baseModule.updatePanel( filter );
   }
   
   @Override
   public void onServiceResult( JavaScriptObject records, String requestId )
   {
      if ( ServiceChampionship.GET_ALL_CHAMPIONSHIPS.equals( requestId )  ) 
      {
         JsArray<Championship> championships = records.cast();
         BaseViewport.getInstance().setChampionships( championships );
         
         initializeViewport();
      }
   }
}
