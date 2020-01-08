package com.futmesa.client.builder;

import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.businessinteligence.Round;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * 
 */
public class RoundBuilder
{

   /**
    * 
    */
   private RoundBuilder()
   {}
   
   @SuppressWarnings ( "unchecked" )
   public static Round build(JsArray<Game> games)
   {
      Round r = ( Round ) JavaScriptObject.createObject();
      r.setGames(games);
      return r;
   }

}
