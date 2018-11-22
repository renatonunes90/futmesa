package com.futmesa.client.module.main;

import com.futmesa.client.base.URLFilter;
import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.Modules;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.businessinteligence.tablestructures.SimpleMapInfo;
import com.futmesa.client.module.main.viewport.classification.ClassificationViewport;
import com.futmesa.client.module.main.viewport.player.PlayerViewport;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.ServicePlayer;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class MainModule extends ModuleInterface
   implements ServiceInterface
{

   private ServiceChampionship serviceChampionship;

   private ServicePlayer servicePlayer;

   private ClassificationViewport classificationViewport;

   private PlayerViewport playerViewport;

   /**
    * Construtor padrão.
    */
   public MainModule()
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
      if ( MainModulePanel.PLAYER_PANEL.equals( filter.getView() ) )
      {
         String id = filter.getFilter( "id" );
         servicePlayer.requestPlayer( parseIntSafe( id ) );
      }
      else
      {
         String championshipId = filter.getFilter( "id" );
         serviceChampionship.requestChampionship( parseIntSafe( championshipId ) );
      }
   }

   @Override
   public String getModuleName()
   {
      return Modules.MAIN_MODULE;
   }

   @Override
   public void onServiceResult( JavaScriptObject records, String requestId )
   {
      if ( ServiceChampionship.GET_CHAMPIONSHIP.equals( requestId ) )
      {
         Championship championship = records.cast();
         if ( championship != null )
         {
            classificationViewport.setChampionship( championship );
            serviceChampionship.requestClassification( championship.getId() );
         }
      }
      else if ( ServiceChampionship.GET_LAST_CLASSIFICATIONS.equals( requestId ) )
      {
         JsArray< Classification > classification = records.cast();
         classificationViewport.updateClassification( classification );

         serviceChampionship.requestAllRounds( classificationViewport.getChampionship().getId() );
      }
      else if ( ServiceChampionship.GET_ALL_ROUNDS.equals( requestId ) )
      {
         JsArray< Round > rounds = records.cast();
         classificationViewport.updateRounds( rounds );

         BaseViewport.getInstance().setTitleHeaderLabel( classificationViewport.getChampionship().getName() );
         BaseViewport.getInstance().setViewportContent( classificationViewport );
      }
      else if ( ServicePlayer.GET_PLAYER.equals( requestId ) )
      {
         Player player = records.cast();
         playerViewport.setPlayer( player );
         BaseViewport.getInstance().setViewportContent( playerViewport );

         servicePlayer.requestReviewInfo( player.getId() );
      }
      else if ( ServicePlayer.GET_REVIEW_INFO.equals( requestId ) )
      {
         JsArray< SimpleMapInfo > infos = records.cast();
         playerViewport.updateReviewInfo( infos );

         servicePlayer.requestStatisticsInfo( playerViewport.getPlayer().getId() );
      }
      else if ( ServicePlayer.GET_STATISTICS_INFO.equals( requestId ) )
      {
         JsArray< SimpleMapInfo > infos = records.cast();
         playerViewport.updateStatisticsInfo( infos );
      }
   }

   private int parseIntSafe( String value )
   {
      int parsedValue = -1;
      try
      {
         parsedValue = Integer.parseInt( value );
      }
      catch ( NumberFormatException e )
      {}
      return parsedValue;
   }
}
