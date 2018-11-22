package com.futmesa.client.module.config;

import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.Modules;
import com.futmesa.client.base.URLFilter;
import com.futmesa.client.module.config.controller.championship.ChampionshipConfigController;
import com.futmesa.client.module.config.controller.player.PlayerConfigController;

/**
 * Gerencia os controllers e a navegação entre as páginas do módulo.
 */
public class ConfigModule extends ModuleInterface
{

   private ChampionshipConfigController championshipConfigController;

   private PlayerConfigController playerConfigController;

   /**
    * Construtor padrão.
    */
   public ConfigModule()
   {
      championshipConfigController = new ChampionshipConfigController();
      playerConfigController = new PlayerConfigController();
   }

   @Override
   public void updatePanel( URLFilter filter )
   {
      if ( ConfigModulePanel.PLAYER_PANEL.equals( filter.getView() ) )
      {
         playerConfigController.openViewport();
      }
      else
      {
         championshipConfigController.openViewport();
      }
   }

   @Override
   public String getModuleName()
   {
      return Modules.CONFIG_MODULE;
   }

}
