package com.futmesa.client.module.config.controller.championship;

import com.futmesa.client.base.event.CustomEvent;
import com.futmesa.client.base.event.CustomEventHandler;
import com.futmesa.client.base.event.EventBus;
import com.futmesa.client.base.event.EventProperty;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.module.config.ConfigModuleConsts;
import com.futmesa.client.module.config.viewport.championship.ChampionshipConfigViewport;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.ServicePlayer;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.request.service.config.ServiceCRUDChampionship;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;

/**
 * Executa a comunicação assíncrona do back-end com a tela de gerenciamento de campeonatos.
 */
public class ChampionshipConfigController
   implements ServiceInterface
{

   /**
    * Constantes da classe.
    */
   private ConfigModuleConsts constants;
   
   public static CustomEvent CREATE_CHAMPIONSHIP = new CustomEvent();
   public static CustomEvent EDIT_CHAMPIONSHIP = new CustomEvent();
   public static CustomEvent REMOVE_CHAMPIONSHIP = new CustomEvent();
   public static CustomEvent UPDATE_CHAMPIONSHIP = new CustomEvent();
   
   private ServiceChampionship serviceChampionship;
   private ServiceCRUDChampionship serviceCRUDChampionship;
   private ServicePlayer servicePlayer;
   
   private ChampionshipConfigViewport championshipConfigViewport;

   /**
    * Construtor padrão.
    */
   public ChampionshipConfigController()
   {
      constants = GWT.create( ConfigModuleConsts.class );
      
      serviceChampionship = new ServiceChampionship( this );
      serviceCRUDChampionship = new ServiceCRUDChampionship( this );
      servicePlayer = new ServicePlayer( this );
      
      EventBus.getInstance().addHandler( CREATE_CHAMPIONSHIP.getAssociatedType(), new CustomEventHandler()
      {
         @Override
         public void onEvent( CustomEvent event )
         {
            Championship c = ( Championship ) event.getProperty( EventProperty.CHAMPIONSHIP );
            serviceCRUDChampionship.createChampionship( c );
         }
      } );
      
      EventBus.getInstance().addHandler( EDIT_CHAMPIONSHIP.getAssociatedType(), new CustomEventHandler()
      {
         @Override
         public void onEvent( CustomEvent event )
         {
            championshipConfigViewport.showChampionshipForm( ( Championship ) event.getProperty( EventProperty.CHAMPIONSHIP ) );
         }
      } );
      
      EventBus.getInstance().addHandler( REMOVE_CHAMPIONSHIP.getAssociatedType(), new CustomEventHandler()
      {
         @Override
         public void onEvent( CustomEvent event )
         {
            Championship c = ( Championship ) event.getProperty( EventProperty.CHAMPIONSHIP );
            if ( Window.confirm( constants.confirmRemoveMsg( constants.championshipLabel() ) ) ) 
            {
               serviceCRUDChampionship.deleteChampionship( c.getId() );
            }
         }
      } );
      
      EventBus.getInstance().addHandler( UPDATE_CHAMPIONSHIP.getAssociatedType(), new CustomEventHandler()
      {
         @Override
         public void onEvent( CustomEvent event )
         {
            Championship c = ( Championship ) event.getProperty( EventProperty.CHAMPIONSHIP );
            serviceCRUDChampionship.updateChampionship( c );
         }
      } );
   }

   public void openViewport()
   {
      championshipConfigViewport = new ChampionshipConfigViewport();
      serviceChampionship.requestChampionships();
   }
   
   
   @Override
   public void onServiceResult( JavaScriptObject records, String requestId )
   {
      if ( ServiceChampionship.GET_ALL_CHAMPIONSHIPS.equals( requestId ) ) 
      {
         JsArray<Championship> championships = records.cast();
         championshipConfigViewport.setChampionships( championships );

         servicePlayer.requestPlayers();
      }
      else if ( ServicePlayer.GET_ALL_PLAYERS.equals( requestId ) ) 
      {
         JsArray< Player > players = records.cast();
         championshipConfigViewport.setPlayers( players );

         BaseViewport.getInstance().setTitleHeaderLabel( "Gerenciamento de Campeonatos" );
         BaseViewport.getInstance().setViewportContent( championshipConfigViewport );
      }
      else if ( ServiceCRUDChampionship.DELETE_CHAMPIONSHIP.equals( requestId ) )
      {
         boolean response = Boolean.valueOf( records.toString() );
         if ( response )
         {
            Window.alert( constants.removeSuccessMsg( constants.championshipLabel() ) );
            serviceChampionship.requestChampionships();
         }
         else
         {
            Window.alert( constants.removeErrorMsg( constants.championshipLabel() ) );
         }
      }
      else if ( ServiceCRUDChampionship.CREATE_CHAMPIONSHIP.equals( requestId ) )
      {
         boolean response = Boolean.valueOf( records.toString() );
         if ( response )
         {
            Window.alert( constants.addSuccessMsg( constants.championshipLabel() ) );
            reloadList();
         }
         else
         {
            Window.alert( constants.addErrorMsg( constants.championshipLabel() ) );
         }
      }
      else if ( ServiceCRUDChampionship.UPDATE_CHAMPIONSHIP.equals( requestId ) )
      {
         boolean response = Boolean.valueOf( records.toString() );
         if ( response )
         {
            Window.alert( constants.addSuccessMsg( constants.championshipLabel() ) );
            reloadList();
         }
         else
         {
            Window.alert( constants.addErrorMsg( constants.championshipLabel() ) );
         }
      }
   }

   private void reloadList()
   {
      championshipConfigViewport.showChampionshipList();
      serviceChampionship.requestChampionships();
   }

}
