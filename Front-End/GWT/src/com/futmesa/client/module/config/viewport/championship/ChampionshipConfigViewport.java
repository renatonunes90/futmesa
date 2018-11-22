package com.futmesa.client.module.config.viewport.championship;

import com.futmesa.client.base.ViewportInterface;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport da tela de gerenciamento dos campeonatos.
 */
public class ChampionshipConfigViewport
   implements ViewportInterface
{

   private static final ChampionshipViewportUiBinder uiBinder = GWT.create( ChampionshipViewportUiBinder.class );

   interface ChampionshipViewportUiBinder
      extends UiBinder< HorizontalPanel, ChampionshipConfigViewport >
   {}

   @UiField ( provided = false )
   protected HorizontalPanel panel;

   /**
    * Construtor padrão.
    */
   public ChampionshipConfigViewport()
   {

      uiBinder.createAndBindUi( this );

   }

   @Override
   public Widget asWidget()
   {
      return panel;
   }
}
