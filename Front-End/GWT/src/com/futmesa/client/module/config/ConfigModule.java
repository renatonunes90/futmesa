package com.futmesa.client.module.config;

import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.Modules;
import com.futmesa.client.base.URLFilter;
import com.futmesa.client.module.main.viewport.classification.ClassificationViewport;
import com.futmesa.client.module.main.viewport.player.PlayerViewport;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.ServicePlayer;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.google.gwt.core.client.JavaScriptObject;

public class ConfigModule extends ModuleInterface
   implements ServiceInterface
{

   private ServiceChampionship serviceChampionship;

   private ServicePlayer servicePlayer;

   private ClassificationViewport classificationViewport;

   private PlayerViewport playerViewport;

   /**
    * Construtor padrão.
    */
   public ConfigModule()
   {
      classificationViewport = new ClassificationViewport();
      playerViewport = new PlayerViewport();
      serviceChampionship = new ServiceChampionship( this );
      servicePlayer = new ServicePlayer( this );

      // super.addMenu(consts.examplePage(), "module=main");
      // super.addMenu(consts.exampleTables(), "module=main&panel=table");
   }

   @Override
   public void updatePanel( URLFilter filter )
   {
      if ( "panel".equals( filter.getView() ) )
      {
         String id = filter.getFilter( "id" );

      }
      else
      {
         String championshipId = filter.getFilter( "championship" );
      }
   }

   @Override
   public String getModuleName()
   {
      return Modules.CONFIG_MODULE;
   }

   @Override
   public void onServiceResult( JavaScriptObject records, String requestId )
   {

   }

}
