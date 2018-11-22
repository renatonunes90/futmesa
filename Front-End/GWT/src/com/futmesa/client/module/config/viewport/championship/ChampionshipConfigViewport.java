package com.futmesa.client.module.config.viewport.championship;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.module.config.widgets.championshiptable.ChampionshipsTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
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

   private ChampionshipsTable championshipTable = new ChampionshipsTable();
   
   /**
    * Construtor padrão.
    */
   public ChampionshipConfigViewport()
   {
      uiBinder.createAndBindUi( this );
      
      panel.add( championshipTable.asWidget() );
   }

   @Override
   public Widget asWidget()
   {
      return panel;
   }
   
   public void setChampionships( JsArray< Championship > championships ) 
   {
      championshipTable.updateTableInfo( championships );
   }
}
