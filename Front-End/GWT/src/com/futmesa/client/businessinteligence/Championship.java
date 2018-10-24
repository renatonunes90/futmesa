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
   public static final ProvidesKey<Championship> KEY_PROVIDER = new ProvidesKey<Championship>() {
     @Override
     public Object getKey(Championship item) {
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
    * @return Retorna o nome do campeonato.
    */
   public final native String getName() /*-{
      return this.name;
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

}

