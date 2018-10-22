package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Classe para representar uma rodada de um campeonato retornado pelo back-end.
 */
public final class Round extends JavaScriptObject
{
   
   /**
    * Construtor que não será chamado.
    */
   protected Round()
   {
      // Não faz nada mesmo.
   }

   /**
    * The key provider that provides the unique ID of a contact.
    */
   public static final ProvidesKey<Round> KEY_PROVIDER = new ProvidesKey<Round>() {
     @Override
     public Object getKey(Round item) {
       return item == null ? null : item.getId();
     }
   };
   
   /**
    * @return Retorna o identificador da rodada.
    */
   public final native int getId() /*-{
      return this.id;
   }-*/;

   /**
    * @return Retorna o identificador do campeonato.
    */
   public final native int getidChampionship() /*-{
      return this.idchampionsip;
   }-*/;

  /**
   * @return Horário da rodada.
   */
  public final native String getBaseHour() /*-{
  	 return this.basehour;
  }-*/;

  /**
   * @return Data da rodada.
   */
  public final native String getBaseDate() /*-{
  	 return this.basedate;
  }-*/;

  /**
   * @return Número da rodada no campeonato.
   */
  public final native int getNumber() /*-{
  	 return this.number;
  }-*/;

  /**
   * @return Jogos da rodada.
   */
  public final native JsArray<Game> getGames() /*-{
  	 return this.games;
  }-*/;
}

