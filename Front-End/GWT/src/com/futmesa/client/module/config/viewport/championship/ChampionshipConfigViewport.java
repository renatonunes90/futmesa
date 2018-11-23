package com.futmesa.client.module.config.viewport.championship;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.module.config.widgets.championshiptable.ChampionshipTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport da tela de gerenciamento dos campeonatos.
 */
public class ChampionshipConfigViewport
   implements ViewportInterface
{

   private static final ChampionshipViewportUiBinder uiBinder = GWT.create( ChampionshipViewportUiBinder.class );

   interface ChampionshipViewportUiBinder
      extends UiBinder< VerticalPanel, ChampionshipConfigViewport >
   {}

   @UiField ( provided = false )
   protected VerticalPanel panel;
   
   @UiField ( provided = false )
   protected SimplePanel championshipPanel;

   @UiField ( provided = false )
   protected Button addBtn;
   
   private ChampionshipTable championshipTable = new ChampionshipTable();
   
   /**
    * Construtor padrão.
    */
   public ChampionshipConfigViewport()
   {
      uiBinder.createAndBindUi( this );
      
      championshipPanel.add( championshipTable.asWidget() );

      panel.setCellHorizontalAlignment( championshipPanel, HasHorizontalAlignment.ALIGN_CENTER);
      panel.setCellHorizontalAlignment( addBtn, HasHorizontalAlignment.ALIGN_CENTER);
      
      addBtn.addClickHandler( handler -> {
         Window.alert( "Vai adicionar um novo campeonato!" );
      });
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
