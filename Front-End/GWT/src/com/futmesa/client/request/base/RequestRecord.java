package com.futmesa.client.request.base;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Classe para pegar os dados retornados do PHP.
 */
public final class RequestRecord extends JavaScriptObject
{
   /**
    * Construtor protegido para não ser usado.
    */
   protected RequestRecord()
   {
      // Não faz nada mesmo.
   }

   /**
    * @return Retorna se o resultado é um erro.
    */
   public final native boolean isError() /*-{
      return this.errMsg != null;
   }-*/;

   /**
    * @return Retorna o resultado em si.
    */
   public final native JavaScriptObject getRecords() /*-{
      return this.records;
   }-*/;

   /**
    * @return Retorna a mensagem de erro, se houver.
    */
   public final native String getErrMsg() /*-{
      return this.errMsg != null ? this.errMsg : "";
   }-*/;
}
