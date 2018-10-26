package com.futmesa.client.request.base;

import java.util.List;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

/**
 * Classe para fazer requisições ao PHP.
 * 
 */
public final class PHPRequest
{
   /**
    * Classe pai que vai receber o resultado das requisições.
    */
   private PHPRequestInterface parent;

   /**
    * Construtor padrão que recebe a classe de callback como parâmetro.
    * 
    * @param parent
    *           Classe que receberá o resultado das requisições.
    */
   public PHPRequest( PHPRequestInterface parent )
   {
      this.parent = parent;
   }

   /**
    * Codifica os parâmetros do post de uma requisição para escapar os caracteres especiais do html.
    *
    * @param d
    *           Requisição original.
    * @return Requisição codificada.
    */
   private String encodeParameters( List< String > params )
   {
      StringBuilder result = new StringBuilder();
      for ( String param : params )
      {
         if ( result.length() > 0 )
            result.append( '&' );

         // NÃO usar a função split pois o valor do parâmetro pode conter o
         // caracter '='.
         int equalsIndex = param.indexOf( '=' );
         if ( equalsIndex != -1 )
         {
            result.append( URL.encodeQueryString( param.substring( 0, equalsIndex ) ) ).append( '=' );
            if ( equalsIndex + 1 < param.length() )
               result.append( URL.encodeQueryString( param.substring( equalsIndex + 1 ) ) );
         }
         else
            result.append( URL.encodeQueryString( param ) ).append( '=' );
      }

      // Retorna a string encodada.
      return result.toString();
   }

   /**
    * /** Faz uma requisição com timeout em segundos.
    * 
    * @param params
    *           Parâmetros da requisição.
    * @param requestId
    *           Identificador da requisição.
    */
   public void request( List< String > params, String requestId )
   {
      RequestBuilder builder = new RequestBuilder( RequestBuilder.POST, "server/main.php" );// Const.SERVER_PATH );
      builder.setHeader( "Content-Type", "application/x-www-form-urlencoded" );

      PHPCallback callback = new PHPCallback( this, requestId );

      try
      {
         builder.sendRequest( this.encodeParameters( params ), callback );
      }
      catch ( RequestException ex )
      {
         callback.onError( null, ex );
      }
   }

   /**
    * @return Retorna a calsse de vai receber o resultado da requisição.
    */
   public PHPRequestInterface getParent()
   {
      return parent;
   }
}
