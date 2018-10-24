package com.futmesa.client.request.service.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.futmesa.client.request.base.PHPRequest;
import com.futmesa.client.request.base.PHPRequestInterface;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;


/**
 * Classe base para serviços do PHP.
 */
public abstract class ServiceAbstract
   implements PHPRequestInterface
{
   protected static final String FUNCTION_STR = "function=";
   private static final String MODULE_STR = "module=";
   private static final String SERVICE_STR = "service=";

   /**
    * Classe para as requisições do serviço.
    */
   private PHPRequest phpRequest;

   /**
    * Classe de callback do serviço.
    */
   protected ServiceInterface parent;

   /**
    * Módulo usado no PHP.
    */
   private String module;

   /**
    * Serviço usado no PHP.
    */
   private String service;

   /**
    * Construtor parão que recebe a classe de callback do serviço.
    * 
    * @param parent
    *           Classe de callback do serviço.
    * @param service
    *           Serviço usado no PHP para as chamadas.
    */
   public ServiceAbstract( ServiceInterface parent, String module, String service )
   {
      phpRequest = new PHPRequest( this );
      this.module = module;
      this.service = service;
      this.parent = parent;
   }

   /**
    * Função para emitir a requisição ao PHP.
    *
    * @param params
    *           Lista dos parâmetros e valores do serviço do PHP.
    * @param requestId
    *           Identificador da requisição PHP.
    * @see get( String data, String requestid )
    */
   protected void request( String[] params, String requestId )
   {
      List< String > paramList = new ArrayList<>( Arrays.asList( params ) );
      this.request( paramList, requestId );
   }

   /**
    * Função para emitir a requisição ao PHP.
    *
    * @param params
    *           Lista dos parâmetros e valores do serviço do PHP.
    * @param requestId
    *           Identificador da requisição PHP.
    * @see get( String data, String requestid )
    */
   protected void request( List< String > params, String requestId )
   {
      params.add( MODULE_STR + module );
      params.add( SERVICE_STR + service );
      phpRequest.request( params, requestId );
   }
   
   /**
    * Função para emitir a requisição ao PHP.
    *
    * @param param
    *           Parâmetro e valore do serviço do PHP.
    * @param requestId
    *           Identificador da requisição PHP.
    * @see get( String data, String requestid )
    */
   protected void request( String param, String requestId )
   {
      this.request( new String[] { param }, requestId );
   }

   @Override
   public void onSuccess( JavaScriptObject result, String requestId )
   {
      parent.onServiceResult( result, requestId );
   }

   @Override
   public void onFailure( String message, String requestId )
   {
      // Verifica a autenticação do usuário.
      if ( "Usuário não autenticado.".equals( message ) )
      {
         com.google.gwt.user.client.Window.Location.reload();
      }
      else
      {
    	  Window.alert( message );
      }
   }
}
