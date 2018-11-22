package com.futmesa.client.module.config.controller.player;

import com.futmesa.client.module.config.viewport.player.PlayerConfigViewport;
import com.futmesa.client.request.service.ServicePlayer;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Executa a comunicação assíncrona do back-end com a tela de gerenciamento de jogadores.
 */
public class PlayerConfigController
   implements ServiceInterface
{

   private ServicePlayer servicePlayer;

   private PlayerConfigViewport playerConfigViewport;

   /**
    * Construtor padrão.
    */
   public PlayerConfigController()
   {
      servicePlayer = new ServicePlayer( this );
   }

   public void openViewport()
   {
      playerConfigViewport = new PlayerConfigViewport();
      servicePlayer.requestPlayers();
   }
   
   
   @Override
   public void onServiceResult( JavaScriptObject records, String requestId )
   {
      if ( ServicePlayer.GET_ALL_PLAYERS.equals( requestId ) ) 
      {
         BaseViewport.getInstance().setViewportContent( playerConfigViewport );
      }
   }

}
