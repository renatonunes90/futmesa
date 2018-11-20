package com.futmesa.client;

import com.futmesa.client.base.Modules;
import com.futmesa.client.base.URLFilter;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.module.config.ConfigModule;
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
   
   private ConfigModule configModule;

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
      servicePlayer = new ServicePlayer( this );
      
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

      this.updateViewport();
   }
   
   public void updateViewport()
   {
      URLFilter filter = new URLFilter( Window.Location.getQueryString() );
      String moduleFilter = filter.getFilter( URLFilter.MODULE );
      if ( Modules.CONFIG_MODULE.equalsIgnoreCase( moduleFilter ) )
      {
         configModule = new ConfigModule();
         //BaseViewport.getInstance().addModule( mainModule );
         configModule.updatePanel( filter );
      }
      else if ( Modules.MAIN_MODULE.equalsIgnoreCase( moduleFilter ) )
      {
         // módulo default
         mainModule = new MainModule();
         //BaseViewport.getInstance().addModule( mainModule );
         mainModule.updatePanel( filter );
      }
   }
   
   @Override
   public void onServiceResult( JavaScriptObject records, String requestId )
   {
      if ( ServiceChampionship.GET_ALL_CHAMPIONSHIPS.equals( requestId )  ) 
      {
         JsArray<Championship> championships = records.cast();
         BaseViewport.getInstance().setChampionships( championships );
         
         servicePlayer.requestPlayers();
      }
      else if ( ServicePlayer.GET_ALL_PLAYERS.equals( requestId ) )
      {
         JsArray<Player> players = records.cast();
         BaseViewport.getInstance().setPlayers( players );
         
         initializeViewport();
      }
   }
}
