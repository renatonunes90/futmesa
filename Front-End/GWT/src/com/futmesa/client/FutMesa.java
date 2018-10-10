package com.futmesa.client;

import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.module.main.MainModule;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
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
      RootPanel.get().add( BaseViewport.getInstance() );

      // listener para chamar o update da viewport quando a url é alterada
      History.addValueChangeHandler( handler -> updateViewport() );

      mainModule = new MainModule();
      BaseViewport.getInstance().addModule( mainModule );
      this.updateViewport();
   }
   
   public void updateViewport()
   {
      FilterConfig filter = new FilterConfig( History.getToken() );
      String moduleFilter = filter.getFilter( FilterConfig.MODULE );
      // if ( moduleFilter.equalsIgnoreCase( "sample" ) )
         mainModule.updatePanel( filter );
      // else
      // // módulo default
      // baseModule.updatePanel( filter );
   }
}
