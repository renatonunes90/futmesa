package com.futmesa.client.base;

import com.futmesa.client.windows.main.BaseViewport;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Ponto de entrada default para qualquer módulo da aplicação. Qualquer módulo, mesmo standalone, irá conter todos os elementos e seguir o
 * mesmo fluxo de carregamento de dados.
 */
public abstract class AppEntryPoint
   implements EntryPoint
{

   /**
    * Ctor padrão. No momento não é utilizado.
    */
   public AppEntryPoint()
   {}

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

   /**
    * Função para carregamento das classes necessárias para o funcionamento do módulo. É chamado sempre no início da aplicação, após
    * executar o login e carregar os tipos.
    */
   public abstract void onLoad();

   /**
    * Atualiza o conteúdo do conteiner principal da janela. É chamado cada vez que a url é alterada com novos parâmetros.
    */
   public abstract void updateViewport();
}
