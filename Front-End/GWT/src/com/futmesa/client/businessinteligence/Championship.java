package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Classe para representar um campeonato retornado pelo back-end.
 */
public final class Championship extends JavaScriptObject
{

   /**
    * Construtor que não será chamado.
    */
   protected Championship()
   {
      // Não faz nada mesmo.
   }

   /**
    * The key provider that provides the unique ID.
    */
   public static final ProvidesKey< Championship > KEY_PROVIDER = new ProvidesKey< Championship >()
   {
      @Override
      public Object getKey( Championship item )
      {
         return item == null ? null : item.getId();
      }
   };

   /**
    * @return Retorna o identificador do campeonato.
    */
   public final native int getId() /*-{
		return this.id;
   }-*/;
   
   /**
    * @return Retorna o identificador da temporada.
    */
   public final native int getIdSeason() /*-{
      return this.idseason;
   }-*/;

   /**
    * @return Retorna o nome do campeonato.
    */
   public final native String getName() /*-{
		return this.name;
   }-*/;
   
   /**
    * @return Retorna o tipo de campeonato.
    */
   public final native int getType() /*-{
      return this.type;
   }-*/;
   
   /**
    * @return Retorna flag indicando se o campenato já foi finalizado.
    */
   public final native int getIsFinished() /*-{
      return this.isfinished;
   }-*/;

   /**
    * @return Data base dos jogos do campeonato.
    */
   public final native String getBaseDate() /*-{
		return this.basedate;
   }-*/;

   /**
    * @return Dias de incremento de cada data de jogos.
    */
   public final native int getDateIncr() /*-{
		return this.dateincr;
   }-*/;

   /**
    * @return Rodadas por dia de jogo.
    */
   public final native int getRoundsByDay() /*-{
		return this.roundsbyday;
   }-*/;

   /**
    * @return Jogos por rodada.
    */
   public final native int getGamesByRound() /*-{
		return this.gamesbyround;
   }-*/;

   public final native void setId( int id ) /*-{
		this.id = id;
   }-*/;
   
   public final native void setIdSeason( int idseason ) /*-{
      this.idseason = idseason;
   }-*/;

   public final native void setName( String name ) /*-{
		this.name = name;
   }-*/;
   
   public final native void setType( int type ) /*-{
      this.type = type;
   }-*/;
   
   public final native void setIsFinished( int isfinished ) /*-{
      this.isfinished = isfinished;
   }-*/;

   public final native void setBaseDate( String basedate ) /*-{
		this.basedate = basedate;
   }-*/;

   public final native void setDateIncr( int dateincr ) /*-{
		this.dateincr = dateincr;
   }-*/;

   public final native void setRoundsByDay( int roundsbyday ) /*-{
		this.roundsbyday = roundsbyday;
   }-*/;

   public final native void setGamesByRound( int gamesbyround ) /*-{
		this.gamesbyround = gamesbyround;
   }-*/;
}
