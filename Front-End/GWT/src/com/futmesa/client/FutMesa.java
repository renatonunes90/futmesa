package com.futmesa.client;

import com.futmesa.client.base.URLFilter;
import com.futmesa.client.module.main.MainModule;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;


/**
 */
public class FutMesa implements EntryPoint
{

   private MainModule mainModule;

   /**
    * Construtor padrão.
    */
   public FutMesa()
   {
      // Não faz nada por enquanto...
   }

   /**
    */
   public void onModuleLoad()
   {
      //AuthWindow.getInstance().checkAuth();
      initializeViewport();
   }

   /**
    * Inicializa a viewport principal da ferramenta.
    */
   private void initializeViewport()
   {
      RootLayoutPanel.get().add( BaseViewport.getInstance() );

      // listener para chamar o update da viewport quando a url é alterada
      History.addValueChangeHandler( handler -> updateViewport() );

      mainModule = new MainModule();
      BaseViewport.getInstance().addModule( mainModule );
      this.updateViewport();
   }
   
   public void updateViewport()
   {
      URLFilter filter = new URLFilter( Window.Location.getQueryString() );
      String moduleFilter = filter.getFilter( URLFilter.MODULE );
      // if ( moduleFilter.equalsIgnoreCase( "sample" ) )
         mainModule.updatePanel( filter );
      // else
      // // módulo default
      // baseModule.updatePanel( filter );
   }
}
