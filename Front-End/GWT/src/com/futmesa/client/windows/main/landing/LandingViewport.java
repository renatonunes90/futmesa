package com.futmesa.client.windows.main.landing;

import com.futmesa.client.base.Modules;
import com.futmesa.client.base.URLFilter;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.module.main.MainModulePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport da tela inicial do sistema.
 */
public class LandingViewport
   implements ViewportInterface
{

   private static final ClassificationViewportUiBinder uiBinder = GWT.create( ClassificationViewportUiBinder.class );

   interface ClassificationViewportUiBinder
      extends UiBinder< VerticalPanel, LandingViewport >
   {}

   @UiField ( provided = false )
   protected VerticalPanel panel;

   @UiField ( provided = false )
   protected FocusPanel shortcutBtn;

   @UiField ( provided = false )
   protected Label helpText;

   /**
    * Construtor padrão.
    */
   public LandingViewport()
   {

      // Create the UiBinder.
      uiBinder.createAndBindUi( this );

      panel.setCellHorizontalAlignment( shortcutBtn, HasHorizontalAlignment.ALIGN_CENTER );
      panel.setCellHorizontalAlignment( helpText, HasHorizontalAlignment.ALIGN_CENTER );
   }

   @Override
   public Widget asWidget()
   {
      return panel;
   }

   public void setLastChampionshipLink( int idChampionship ) 
   {
      shortcutBtn.addClickHandler( handler ->
      {
         URLFilter filter = new URLFilter( Modules.MAIN_MODULE, MainModulePanel.CHAMPIONSHIP_PANEL );
         filter.addFilter( "id", String.valueOf( idChampionship ) );
         Window.Location.assign( filter.toURLString()  );
      } );
   }

}
