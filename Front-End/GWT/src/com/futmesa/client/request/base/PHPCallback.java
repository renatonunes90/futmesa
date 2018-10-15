package com.futmesa.client.request.base;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 * Classe para verificar o retorno do PHP por um erro e repassar as informações válidas.
 * 
 */
final class PHPCallback
   implements RequestCallback
{
   /**
    * Classe da requisição usada.
    */
   private PHPRequest parent;

   /**
    * Guarda o identificador da requisição.
    */
   private String requestId;

   /**
    * Booleano indicando se retorno deve ser tratado ou não. Nãod eve ser tratado em caso de download.
    */
   private boolean isDownload;

   private String fileName;

   /**
    * Construtor padrão que recebe a lsita de parâmetros usada.
    * 
    * @param parent
    *           Classe da requisição usada.
    * @param requestId
    *           Identificador da requisição.
    */
   public PHPCallback( PHPRequest parent, String requestId )
   {
      this( parent, requestId, false, null );
   }

   /**
    * Construtor padrão que recebe a lsita de parâmetros usada.
    * 
    * @param parent
    *           Classe da requisição usada.
    * @param requestId
    *           Identificador da requisição.
    */
   public PHPCallback( PHPRequest parent, String requestId, boolean isDownload, String fileName )
   {
      this.parent = parent;
      this.requestId = requestId;
      this.isDownload = isDownload;
      this.fileName = fileName;
   }

   @Override
   public void onError( Request request, Throwable exception )
   {
      String message = exception.getMessage();
      // Verifica se foi problema de timeout.
      if ( message.contains( "timeout" ) )
         parent.getParent().onFailure( PHPConsts.INSTANCE.timeoutError(), requestId );
      // Se não repassa o erro adiante.
      else
         parent.getParent().onFailure( PHPConsts.INSTANCE.requestError() + " " + message, requestId );
   }

   @Override
   public void onResponseReceived( Request request, Response response )
   {
      // Converte o resultado.
      try
      {
         if ( this.isDownload )
         {
            download( response.getText(), fileName );
            parent.getParent().onSuccess( JavaScriptObject.createObject(), requestId );
         }
         else
         {
            RequestRecord rr = JsonUtils.< RequestRecord > safeEval( response.getText() );
            // Verifica se a resposta possui identificador do request.
            if ( rr.isError() )
               parent.getParent().onFailure( rr.getErrMsg(), requestId );
            else
               parent.getParent().onSuccess( rr.getRecords(), requestId );
         }
      }
      catch ( Exception ex )
      {
         String message = PHPConsts.INSTANCE.parseError();
         Logger.getGlobal().log( Level.WARNING, message + " " + response.getText(), ex );
         // Info.display( new DefaultInfoConfig( PHPConsts.INSTANCE.error(), message ) );
         parent.getParent().onFailure( message, null );
      }
   }

   /**
    * Método que faz o download to arquivo caso seja solicitado
    * 
    * @param content
    *           Conteúdo do arquivo
    * @param fileName
    *           Nome do arquivo
    */
   private static native void download( String content, String fileName )
   /*-{
      var link = $wnd.document.createElement( "a" );
      link.setAttribute( "href", "data:text/plain;charset=utf-8," + content );
      link.setAttribute( "download", fileName );
      $wnd.document.getElementsByTagName( "body" )[ 0 ].appendChild( link ).click();
      link.remove();
   }-*/;
}
