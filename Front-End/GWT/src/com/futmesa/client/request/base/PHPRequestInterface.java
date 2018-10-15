package com.futmesa.client.request.base;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Interface para callback de requisições para o PHP.
 */
public interface PHPRequestInterface
{
   /**
    * Chamada quando a comunicação dá certo.
    * 
    * @param result
    *           Resposta da requisição.
    * @param requestId
    *           Identificador da requisição.
    */
   public void onSuccess( JavaScriptObject result, String requestId );

   /**
    * Chamada quando a comunicação dá erro.
    * 
    * @param message
    *           Resposta da requisição.
    * @param requestId
    *           Identificador da requisição.
    */
   public void onFailure( String message, String requestId );
}
