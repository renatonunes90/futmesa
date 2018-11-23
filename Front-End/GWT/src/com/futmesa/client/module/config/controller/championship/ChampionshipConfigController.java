package com.futmesa.client.module.config.controller.championship;

import com.futmesa.client.base.event.CustomEvent;
import com.futmesa.client.base.event.CustomEventHandler;
import com.futmesa.client.base.event.EventBus;
import com.futmesa.client.base.event.EventProperty;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.module.config.viewport.championship.ChampionshipConfigViewport;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.request.service.config.ServiceCRUDChampionship;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;

/**
 * Executa a comunicação assíncrona do back-end com a tela de gerenciamento de campeonatos.
 */
public class ChampionshipConfigController
   implements ServiceInterface
{

   public static CustomEvent EDIT_CHAMPIONSHIP = new CustomEvent();
   public static CustomEvent REMOVE_CHAMPIONSHIP = new CustomEvent();
   
   private ServiceChampionship serviceChampionship;

   private ServiceCRUDChampionship serviceCRUDChampionship;
   
   private ChampionshipConfigViewport championshipConfigViewport;

   /**
    * Construtor padrão.
    */
   public ChampionshipConfigController()
   {
      serviceChampionship = new ServiceChampionship( this );
      serviceCRUDChampionship = new ServiceCRUDChampionship( this );
      
      EventBus.getInstance().addHandler( EDIT_CHAMPIONSHIP.getAssociatedType(), new CustomEventHandler()
      {
         @Override
         public void onEvent( CustomEvent event )
         {
            Championship c = ( Championship ) event.getProperty( EventProperty.CHAMPIONSHIP );
            Window.alert( "Vai editar um campeonato '" + c.getName() + "'." );
         }
      } );
      
      EventBus.getInstance().addHandler( REMOVE_CHAMPIONSHIP.getAssociatedType(), new CustomEventHandler()
      {
         @Override
         public void onEvent( CustomEvent event )
         {
            Championship c = ( Championship ) event.getProperty( EventProperty.CHAMPIONSHIP );
            serviceCRUDChampionship.deleteChampionship( c.getId() );
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
         BaseViewport.getInstance().setTitleHeaderLabel( "Gerenciamento de Campeonatos" );
         BaseViewport.getInstance().setViewportContent( championshipConfigViewport );
      }
      else if ( ServiceCRUDChampionship.DELETE_CHAMPIONSHIP.equals( requestId ) )
      {
         boolean response = Boolean.valueOf( records.toString() );
         if ( response )
         {
            Window.alert( "Campeonato removido com sucesso." );
            serviceChampionship.requestChampionships();
         }
         else
         {
            Window.alert( "Erro removendo o campeonato, tente novamente mais tarde." );
         }
      }
   }

}
