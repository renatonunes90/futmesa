package com.futmesa.client;

import com.futmesa.client.base.AppEntryPoint;
import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.module.main.MainModule;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.user.client.History;


/**
 */
public class FutMesa extends AppEntryPoint
{

   private MainModule mainModule;

   /**
    * Construtor padrão.
    */
   public FutMesa()
   {
      // Não faz nada por enquanto...
   }

   @Override
   public void onLoad()
   {
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
