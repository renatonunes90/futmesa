package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Classe para representar um jogo de um campeonato retornado pelo back-end.
 */
public final class Game extends JavaScriptObject
{
   
   /**
    * Construtor que não será chamado.
    */
   protected Game()
   {
      // Não faz nada mesmo.
   }

   /**
    * The key provider that provides the unique ID of a contact.
    */
   public static final ProvidesKey<Game> KEY_PROVIDER = new ProvidesKey<Game>() {
     @Override
     public Object getKey(Game item) {
       return item == null ? null : item.getId();
     }
   };
   
   /**
    * @return Retorna o identificador do jogo.
    */
   public final native int getId() /*-{
      return this.id;
   }-*/;

   /**
    * @return Retorna o identificador da rodada do campeonato.
    */
   public final native int getIdRound() /*-{
      return this.idround;
   }-*/;

   /**
    * @return Retorna o identificador do jogador 1.
    */
   public final native int getPlayer1Id() /*-{
      return this.player1.id;
   }-*/;

   /**
    * @return Retorna o nome do jogador 1.
    */
   public final native String getPlayer1Name() /*-{
      return this.player1.name;
   }-*/;
   
   /**
    * @return Retorna o identificador do jogador 2.
    */
   public final native int getPlayer2Id() /*-{
      return this.player2.id;
   }-*/;

   /**
    * @return Retorna o nome do jogador 2.
    */
   public final native String getPlayer2Name() /*-{
      return this.player2.name;
   }-*/;
   
  /**
   * @return Mesa do jogo.
   */
  public final native int getGameTable() /*-{
  	 return this.gametable;
  }-*/;

  /**
   * @return Gols do jogador 1 no jogo.
   */
  public final native int getScore1() /*-{
  	 return this.score1;
  }-*/;

  /**
   * @return Gols do jogador 2 no jogo.
   */
  public final native int getScore2() /*-{
  	 return this.score2;
  }-*/;

  /**
   * @return Horário que o resultado foi inserido.
   */
  public final native String getInputDate() /*-{
  	 return this.inputdate;
  }-*/;
  
  /**
   * @return Identificador do jogador vencedor.
   */
  public final native String getIdWinner() /*-{
  	 return this.idwinner;
  }-*/;

	public final native void setScore1(int score) /*-{
		this.score1 = score;
	}-*/;

	public final native void setScore2(int score) /*-{
		this.score2 = score;
	}-*/;

	public final native void setPlayer1Name(String name) /*-{
		  if (!this.player1) {
		  	this.player1 = {};
		  }
	      this.player1.name = name;
	   }-*/;

	public final native void setPlayer2Name(String name) /*-{
		if (!this.player2) {
		  	this.player2 = {};
		  }
	      this.player2.name = name;
	   }-*/;

}

