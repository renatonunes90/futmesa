package com.futmesa.client.base;

import java.util.TreeMap;

/**
 * Classe contendo as propriedades que são utilizadas na navegação da ferramenta através da URL.
 */
public final class FilterConfig
{
   private static final String DELIMITER = "=";

   private static final String SEPARATOR = "&";

   public static final String PANEL = "panel";

   public static final String MODULE = "module";

   /**
    * Mapa de filtros.
    */
   private final TreeMap< String, String > filters;

   /**
    * Construtor padrão que recebe um filtro.
    * 
    * @param filterConfig
    *           Filtro de ativo/grupo virtual.
    */
   public FilterConfig( String filterConfig )
   {
      filters = new TreeMap<>();
      this.parseFilder( filterConfig );
   }

   /**
    * Trata uma string de filtro e divide para um mapa de filtros.
    * 
    * @param filterConfig
    *           String de um FilterConfig.
    */
   private void parseFilder( String filterConfig )
   {
      String[] filterSplit = filterConfig.split( SEPARATOR );
      for ( String filter : filterSplit )
      {
         String[] fv = filter.split( DELIMITER );
         if ( fv.length == 2 )
         {
            this.filters.put( fv[ 0 ], fv[ 1 ] );
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
      return this.getFilter( filter, "" );
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
   public String getPanel()
   {
      return getFilter( PANEL, "" );
   }

}
