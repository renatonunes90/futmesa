package com.futmesa.client.builder;

import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Player;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * Classe para criar instâncias de campeonatos.
 */
public class ChampionshipBuilder
{

   /**
    * Construtor que não será chamado.
    */
   private ChampionshipBuilder()
   {}
   
   @SuppressWarnings ( "unchecked" )
   public static Championship buildEmpty()
   {
      Championship c = ( Championship ) JavaScriptObject.createObject();
      c.setId( 0 );
      c.setIdSeason( 0 );
      c.setName( "" );
      c.setType( 0 );
      c.setGamesByRound( 0 );
      c.setRoundsByDay( 0 );
      c.setDateIncr( 0 );
      c.setPlayers( ( JsArray< Player > ) JavaScriptObject.createArray() );
      return c;
   }

}
