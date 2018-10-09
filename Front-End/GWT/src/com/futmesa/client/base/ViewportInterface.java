package com.futmesa.client.base;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Interface para uma viewport que poderá ser exibida no painel principal da interface.
 */
public interface ViewportInterface
   extends IsWidget
{
   
   /**
    * @return Retorna o filtro usado na view.
    */
   public FilterConfig getFilterConfig();

   /**
    * @return Retorna o texto de ajuda do módulo.
    */
   public String getHelp();

   /**
    * Atualiza a tela com as informações necessárias.
    * 
    * @param filterConfig
    *           Filtro a ser usado na view.
    */
   public void updateView( FilterConfig filterConfig );
}
