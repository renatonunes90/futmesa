package com.futmesa.client.base;

import java.util.TreeMap;

/**
 * Classe contendo as propriedades que são utilizadas na navegação da ferramenta através da URL.
 */
public class URLFilter
{
   private static final String DELIMITER = "=";

   private static final String SEPARATOR = "&";

   public static final String VIEW = "view";

   public static final String MODULE = "module";

   /**
    * Mapa de filtros.
    */
   private TreeMap< String, String > filters;

   /**
    * Construtor padrão que recebe um filtro.
    * 
    * @param filterConfig
    *           Filtro de ativo/grupo virtual.
    */
   public URLFilter( String filterConfig )
   {
      filters = new TreeMap<>();
      parseFilder( filterConfig );
   }

   /**
    * Construtor que recebe um módulo e uma view.
    * 
    */
   public URLFilter( String module, String view )
   {
      filters = new TreeMap<>();
      setFilter( MODULE, module );
      setFilter( VIEW, view );
   }
   
   /**
    * Trata uma string de filtro e divide para um mapa de filtros.
    * 
    * @param filterConfig
    *           String de um FilterConfig.
    */
   private void parseFilder( String filterConfig )
   {
      filterConfig = filterConfig.replace("?","");
      String[] filterSplit = filterConfig.split( SEPARATOR );
      for ( String filter : filterSplit )
      {
         String[] fv = filter.split( DELIMITER );
         if ( fv.length == 2 )
         {
            filters.put( fv[ 0 ], fv[ 1 ] );
         }
      }
   }

   /**
    * Verifica se existe um filtro.
    * 
    * @param filter
    *           Filtro a procurar.
    */
   public boolean hasFilter( String filter )
   {
      return filters.containsKey( filter );
   }

   /**
    * 
    * @return Retorna quantos filtros são usados.
    */
   public int getFilterCount()
   {
      return filters.size();
   }

   /**
    * Retorna o valor de um filtro ou uma string vazia.
    * 
    * @param filter
    *           Nome do filtro.
    */
   public String getFilter( String filter )
   {
      return getFilter( filter, "" );
   }

   /**
    * Retorna o valor de um filtro. Caso não haja a propriedade, retorna um valor padrão.
    * 
    * @param filter
    *           Nome do filtro.
    * @param defaultValue
    *           Valor padrão para retornar, se não houver.
    */
   public String getFilter( String filter, String defaultValue )
   {
      if ( filters.containsKey( filter ) )
         return filters.get( filter );
      else
         return defaultValue;
   }

   /**
    * 
    * @return String do Módulo na URL.
    */
   public String getModule()
   {
      return getFilter( MODULE, "" );
   }

   /**
    * 
    * @return String do painel do Módulo contido na URL.
    */
   public String getView()
   {
      return getFilter( VIEW, "" );
   }
   
   /**
    * Adiciona um filtro na URL.
    * 
    * @param key
    * @param value
    */
   public void addFilter( String key, String value )
   {
      setFilter( key, value );
   }

   /**
    * Filtros no formato de uma URL.
    * 
    * @return
    */
   public String toURLString()
   {
      StringBuilder URLString = new StringBuilder( "?" );
      for ( String key : filters.keySet() )
      {
         if ( URLString.length() > 1 ) 
         {
            URLString.append( SEPARATOR );
         }
         URLString.append( key );
         URLString.append( DELIMITER );
         URLString.append( filters.get( key ) );
      }
      return URLString.toString();
   }
   
   private void setFilter( String key, String value )
   {
      filters.put( key, value );
   }
   
}
