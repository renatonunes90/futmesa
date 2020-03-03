package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Represents back-end object.
 */
public final class Classification extends JavaScriptObject {

	protected Classification() {
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

	public final native int getPlayerId() /*-{
		return this.player.id;
	}-*/;

	public final native String getPlayerName() /*-{
		return this.player.name;
	}-*/;

	public final native int getPosition() /*-{
		return this.position;
	}-*/;

	public final native int getRoundNumber() /*-{
		return this.roundNumber;
	}-*/;

	public final native int getWins() /*-{
		return this.wins;
	}-*/;

	public final native int getTies() /*-{
		return this.ties;
	}-*/;

	public final native int getLosses() /*-{
		return this.losses;
	}-*/;

	public final native int getGoalsPro() /*-{
		return this.goalsPro;
	}-*/;

	public final native int getGoalsCon() /*-{
		return this.goalsCon;
	}-*/;

	public final native int getGoalsDiff() /*-{
		return this.goalsPro - this.goalsCon;
	}-*/;

	public final native int getNumberOfGames() /*-{
		return this.wins + this.ties + this.losses;
	}-*/;

	public final native int getPoints() /*-{
		return this.wins * 3 + this.ties;
	}-*/;

	public final native double getWinRate() /*-{
		if ( this.@com.futmesa.client.businessinteligence.Classification::getNumberOfGames()() > 0 ) {
			return this.@com.futmesa.client.businessinteligence.Classification::getPoints()()
					* 100
					/ (this.@com.futmesa.client.businessinteligence.Classification::getNumberOfGames()() * 3);
		} else {
			return 0;
		}
	}-*/;

	public final native JsArrayString getLast5Games() /*-{
		return this.last5Games;
	}-*/;

}
