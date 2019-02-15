package com.futmesa.client.module.config.viewport.player;

import com.futmesa.client.base.ViewportInterface;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport da tela de gerenciamento dos jogadores.
 */
public class PlayerConfigViewport
   implements ViewportInterface
{

   private static final PlayerViewportUiBinder uiBinder = GWT.create( PlayerViewportUiBinder.class );

   interface PlayerViewportUiBinder
      extends UiBinder< HorizontalPanel, PlayerConfigViewport >
   {}

   @UiField ( provided = false )
   protected HorizontalPanel panel;

   /**
    * Construtor padrão.
    */
   public PlayerConfigViewport()
   {

      uiBinder.createAndBindUi( this );

   }

   @Override
   public Widget asWidget()
   {
      return panel;
   }
}
