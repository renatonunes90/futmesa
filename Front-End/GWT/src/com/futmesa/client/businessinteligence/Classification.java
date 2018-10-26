package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Classe para representar a classificação de um jogador retornado pelo back-end.
 */
public final class Classification extends JavaScriptObject
{
   
   /**
    * Construtor que não será chamado.
    */
   protected Classification()
   {
      // Não faz nada mesmo.
   }

   /**
    * The key provider that provides the unique ID of a contact.
    */
   public static final ProvidesKey<Classification> KEY_PROVIDER = new ProvidesKey<Classification>() {
     @Override
     public Object getKey(Classification item) {
       return item == null ? null : item.getPlayerId();
     }
   };
   
   /**
    * @return Retorna o identificador do jogador.
    */
   public final native int getPlayerId() /*-{
      return this.player.id;
   }-*/;

   /**
    * @return Retorna o nome do jogador.
    */
   public final native String getPlayerName() /*-{
      return this.player.name;
   }-*/;

   /**
    * @return Retorna a posição no campeonato.
    */
   public final native int getPosition() /*-{
   	 return this.position;
   }-*/;
   
  /**
   * @return Retorna o nome.
   */
  public final native int getRoundNumber() /*-{
  	 return this.roundNumber;
  }-*/;
  
  /**
   * @return Número de vitórias do jogador.
   */
  public final native int getWins() /*-{
  	 return this.wins;
  }-*/;

  /**
   * @return Número de empates do jogador.
   */
  public final native int getTies() /*-{
  	 return this.ties;
  }-*/;

  /**
   * @return Número de derrotas do jogador.
   */
  public final native int getLosses() /*-{
  	 return this.losses;
  }-*/;

  /**
   * @return Número de gols marcados do jogador.
   */
  public final native int getGoalsPro() /*-{
  	 return this.goalsPro;
  }-*/;
  
  /**
   * @return Número de gols sofridos do jogador.
   */
  public final native int getGoalsCon() /*-{
  	 return this.goalsCon;
  }-*/;
  
  /**
   * @return Saldo de gols do jogador.
   */
  public final native int getGoalsDiff() /*-{
  	 return this.goalsPro - this.goalsCon;
  }-*/;
  
  /**
   * @return Número de partidas jogadas pelo jogador.
   */
  public final native int getNumberOfGames() /*-{
  	 return this.wins + this.ties + this.losses;
  }-*/;
  
  /**
   * @return Pontos do jogador.
   */
  public final native int getPoints() /*-{
      return this.wins * 3 + this.ties;
  }-*/;
  
  /**
   * @return Percentual de aproveitamento do jogador.
   */
  public final native double getWinRate() /*-{
      return this.@com.futmesa.client.businessinteligence.Classification::getPoints()() * 100 / ( this.@com.futmesa.client.businessinteligence.Classification::getNumberOfGames()() * 3 );
  }-*/;
  
  /**
   * @return Lista com o estado dos últimos 5 jogos.
   */
  public final native JsArrayString getLast5Games() /*-{
  	 return this.last5Games;
  }-*/;
  
}

