package com.futmesa.client.module;

import com.futmesa.client.FutMesaConsts;
import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.base.ModuleInterface;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.viewport.table.MainViewport;
import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.shared.GWT;


public class MainModule extends ModuleInterface
{
   /**
    * Constantes de mensagens.
    */
   private FutMesaConsts consts = GWT.create( FutMesaConsts.class );

   /**
    * Construtor padrão.
    */
   public MainModule()
   {
      super.addMenu( consts.examplePage(), "module=main" );
      super.addMenu( consts.exampleTables(), "module=main&panel=table" );
   }

   @Override
   public void updatePanel( FilterConfig filter )
   {
      ViewportInterface v = null;
      String panelFilter = filter.getFilter( FilterConfig.PANEL );
      // if ( panelFilter.equalsIgnoreCase( "table" ) )
         v = new MainViewport();

      BaseViewport.getInstance().setViewportContent( v );
      v.updateView( filter );
   }

   @Override
   public String getModuleName()
   {
      return consts.moduleName();
   }
}
