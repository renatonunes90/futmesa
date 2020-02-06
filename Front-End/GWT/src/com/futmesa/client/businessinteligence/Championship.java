package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Represents back-end object.
 */
public final class Championship extends JavaScriptObject {

	protected Championship() {
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

	public final native int getId() /*-{
		return this.id;
	}-*/;

	public final native int getIdSeason() /*-{
		return this.idseason;
	}-*/;

	public final native String getName() /*-{
		return this.name;
	}-*/;

	public final native int getType() /*-{
		return this.type;
	}-*/;

	public final native int getIsFinished() /*-{
		return this.isfinished;
	}-*/;

	public final native String getBaseDate() /*-{
		return this.basedate;
	}-*/;

	public final native int getDateIncr() /*-{
		return this.dateincr;
	}-*/;

	public final native int getGamesByRound() /*-{
		return this.gamesbyround;
	}-*/;

	public final native int getRoundsByDay() /*-{
		return this.roundsbyday;
	}-*/;

	public final native JsArray<Player> getPlayers() /*-{
		return this.players;
	}-*/;

	public final native JsArray<Phase> getPhases() /*-{
		return this.phases;
	}-*/;

	public final native void setId(int id) /*-{
		this.id = id;
	}-*/;

	public final native void setIdSeason(int idseason) /*-{
		this.idseason = idseason;
	}-*/;

	public final native void setName(String name) /*-{
		this.name = name;
	}-*/;

	public final native void setType(int type) /*-{
		this.type = type;
	}-*/;

	public final native void setIsFinished(int isfinished) /*-{
		this.isfinished = isfinished;
	}-*/;

	public final native void setBaseDate(String basedate) /*-{
		this.basedate = basedate;
	}-*/;

	public final native void setDateIncr(int dateincr) /*-{
		this.dateincr = dateincr;
	}-*/;

	public final native void setRoundsByDay(int roundsbyday) /*-{
		this.roundsbyday = roundsbyday;
	}-*/;

	public final native void setGamesByRound(int gamesbyround) /*-{
		this.gamesbyround = gamesbyround;
	}-*/;

	public final native void setPlayers(JsArray<Player> players) /*-{
		this.players = players;
	}-*/;

	public final native void setPhases(JsArray<Round> phases) /*-{
		this.phases = phases;
	}-*/;
	
	public final native boolean isFirstPhase(int phaseNumber) /*-{
		var isFirst = true;
		for ( var i=0; i < this.phases.length; i++ ) {
			if ( this.phases[i].number < phaseNumber ) {
				isFirst = false;
			}
		}
		return isFirst;
	}-*/;
	
	public final native boolean isLastPhase(int phaseNumber) /*-{
		var isLast = true;
		for ( var i=0; i < this.phases.length; i++ ) {
			if ( this.phases[i].number > phaseNumber ) {
				isLast = false;
			}
		}
		return isLast;
	}-*/;
}
