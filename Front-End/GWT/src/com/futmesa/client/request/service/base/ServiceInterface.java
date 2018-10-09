package com.futmesa.client.request.service.base;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Classe para fazer requisições para serviços do PHP.
 */
public interface ServiceInterface
{
   /**
    * Desabilita o componente pai.
    * 
    * @param requestId
    *           Identificador da requisição.
    */
   public void mask( String requestId );

   /**
    * Repassa o resultado do PHP para o componente pai.
    * 
    * @param records
    *           Resultado da requisição do PHP.
    * @param requestId
    *           Identificador da requisição.
    */
   public void onServiceResult( JavaScriptObject records, String requestId );

   /**
    * Habilita o componente pai.
    * 
    * @param requestId
    *           Identificador da requisição.
    */
   public void unmask( String requestId );
}
