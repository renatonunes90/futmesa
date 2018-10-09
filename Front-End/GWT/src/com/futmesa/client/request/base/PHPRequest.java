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
   public static final int REQUEST_TIMEOUT = 30;

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
    * Faz uma requisição com timeout de 30 segundos.
    * 
    * @param params
    *           Parâmetros da requisição.
    * @param idRequest
    *           Identificador da requisição.
    */
   public void request( List< String > params, String idRequest )
   {
      this.request( REQUEST_TIMEOUT, params, idRequest );
   }

   /**
    * /** Faz uma requisição com timeout em segundos.
    * 
    * @param timeout
    *           Timeout da requisição.
    * @param params
    *           Parâmetros da requisição.
    * @param requestId
    *           Identificador da requisição.
    */
   public void request( int timeout, List< String > params, String requestId )
   {
      RequestBuilder builder = new RequestBuilder( RequestBuilder.POST, "" );// Const.SERVER_PATH );
      builder.setHeader( "Content-Type", "application/x-www-form-urlencoded" );
      builder.setTimeoutMillis( timeout * 1000 );

      PHPCallback callback;
      if ( params.contains( "download=1" ) )
      {
         callback = new PHPCallback( this, requestId, true, getFileName( params ) );
      }
      else
      {
         callback = new PHPCallback( this, requestId );
      }
      try
      {
         params.add( "timeout=" + Integer.toString( timeout ) );
         builder.sendRequest( this.encodeParameters( params ), callback );
      }
      catch ( RequestException ex )
      {
         callback.onError( null, ex );
      }
   }

   private String getFileName( List< String > params )
   {
      String rst = "file.txt";
      for ( String s : params )
      {
         if ( s.startsWith( "filename=" ) || s.startsWith( "fileName=" ) )
         {
            rst = s.split( "=" )[ 1 ];
         }
      }
      return rst;
   }

   /**
    * @return Retorna a calsse de vai receber o resultado da requisição.
    */
   public PHPRequestInterface getParent()
   {
      return parent;
   }
}
