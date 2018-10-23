package com.futmesa.client.request.service.base;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Classe para fazer requisições para serviços do PHP.
 */
public interface ServiceInterface
{
   /**
    * Repassa o resultado do PHP para o componente pai.
    * 
    * @param records
    *           Resultado da requisição do PHP.
    * @param requestId
    *           Identificador da requisição.
    */
   public void onServiceResult( JavaScriptObject records, String requestId );
}
