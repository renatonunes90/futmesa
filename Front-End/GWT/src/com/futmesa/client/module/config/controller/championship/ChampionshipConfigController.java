package com.futmesa.client.module.config.controller.championship;

import com.futmesa.client.module.config.viewport.championship.ChampionshipConfigViewport;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Executa a comunicação assíncrona do back-end com a tela de gerenciamento de campeonatos.
 */
public class ChampionshipConfigController
   implements ServiceInterface
{

   private ServiceChampionship serviceChampionship;

   private ChampionshipConfigViewport championshipConfigViewport;

   /**
    * Construtor padrão.
    */
   public ChampionshipConfigController()
   {
      serviceChampionship = new ServiceChampionship( this );
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
         BaseViewport.getInstance().setViewportContent( championshipConfigViewport );
      }
   }

}
