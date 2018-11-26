package com.futmesa.client.module.config.viewport.championship;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.base.event.EventBus;
import com.futmesa.client.base.event.EventProperty;
import com.futmesa.client.builder.ChampionshipBuilder;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.module.config.controller.championship.ChampionshipConfigController;
import com.futmesa.client.module.config.widgets.championshipform.ChampionshipForm;
import com.futmesa.client.module.config.widgets.championshiptable.ChampionshipTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
   protected HorizontalPanel btnPanel;

   @UiField ( provided = false )
   protected Button addBtn;
   
   @UiField ( provided = false )
   protected Button cancelBtn;
   
   @UiField ( provided = false )
   protected Button saveBtn;
   
   private ChampionshipTable championshipTable;
   
   private ChampionshipForm championshipForm;
   
   /**
    * Construtor padrão.
    */
   public ChampionshipConfigViewport()
   {
      uiBinder.createAndBindUi( this );
      
      championshipForm =  new ChampionshipForm();
      championshipTable = new ChampionshipTable();
      
      panel.setCellHorizontalAlignment( championshipPanel, HasHorizontalAlignment.ALIGN_CENTER);
      panel.setCellHorizontalAlignment( btnPanel, HasHorizontalAlignment.ALIGN_CENTER);
      
      addBtn.addClickHandler( handler -> {
         showChampionshipForm( ChampionshipBuilder.buildEmpty() );
      });
      
      cancelBtn.addClickHandler( handler -> {
         showChampionshipList();
      });
      
      saveBtn.addClickHandler( handler -> {
         Championship c = championshipForm.getChampionship();
         if ( c.getId() > 0 )
         {
            ChampionshipConfigController.UPDATE_CHAMPIONSHIP.setProperty( EventProperty.CHAMPIONSHIP, c );
            EventBus.getInstance().fireEvent( ChampionshipConfigController.UPDATE_CHAMPIONSHIP );
         }
         else
         {
            c.setIsFinished( 0 );
            ChampionshipConfigController.CREATE_CHAMPIONSHIP.setProperty( EventProperty.CHAMPIONSHIP, c );
            EventBus.getInstance().fireEvent( ChampionshipConfigController.CREATE_CHAMPIONSHIP );
         }
      });
      
      showChampionshipList();
   }

   @Override
   public Widget asWidget()
   {
      return panel;
   }

   public void showChampionshipForm( Championship championship )
   {
      championshipPanel.clear();
      championshipForm.setChampionship( championship );
      championshipPanel.add( championshipForm.asWidget() );

      addBtn.setVisible( false );
      cancelBtn.setVisible( true );
      saveBtn.setVisible( true );
   }
   
   public void showChampionshipList()
   {
      championshipPanel.clear();
      championshipPanel.add( championshipTable.asWidget() );

      addBtn.setVisible( true );
      cancelBtn.setVisible( false );
      saveBtn.setVisible( false );
   }
   
   public void setChampionships( JsArray< Championship > championships ) 
   {
      championshipTable.updateTableInfo( championships );
   }
   
   public void setPlayers( JsArray< Player > players )
   {
      championshipForm.setPlayers( players );
   }
}
