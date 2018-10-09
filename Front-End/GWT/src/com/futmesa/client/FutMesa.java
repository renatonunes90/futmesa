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
    * Ponto de entrada da interface. Mostra a tela de login e seta o listener para após o carregamento dos tipos.
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
      // LayoutPanel baseViewport = new LayoutPanel();
      RootPanel.get().add( BaseViewport.getInstance() );
      // baseViewport.add( MainViewport.getInstance() );
      // baseViewport.add( new Label( "TEXTO" ) );
      // MainViewport.getInstance().setUserLogged( AuthWindow.getInstance().getWebUser().getName() );

      // listener para chamar o update da viewport quando a url é alterada
      History.addValueChangeHandler( handler -> updateViewport() );

      // chama função que será implementada por cada ponto de entrada
      onLoad();
   }
   
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
