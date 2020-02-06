package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Represents back-end object.
 */
public final class Phase extends JavaScriptObject {

	protected Phase() {
	}

	/**
	 * The key provider that provides the unique ID.
	 */
	public static final ProvidesKey<Phase> KEY_PROVIDER = new ProvidesKey<Phase>() {
		@Override
		public Object getKey(Phase item) {
			return item == null ? null : item.getId();
		}
	};

	public final native int getId() /*-{
		return this.id;
	}-*/;

	public final native int getIdChampionship() /*-{
		return this.idchampionship;
	}-*/;

	public final native int getType() /*-{
		return this.type;
	}-*/;

	public final native int getNumber() /*-{
		return this.number;
	}-*/;
	
	public final native JsArray<Group> getGroups() /*-{
		return this.groups;
	}-*/;
	
	public final native JsArray<Round> getRounds() /*-{
		return this.rounds;
	}-*/;

	public final native void setId(int id) /*-{
		this.id = id;
	}-*/;

	public final native void setIdChampionship(int idchampionship) /*-{
		this.idchampionship = idchampionship;
	}-*/;


	public final native void setType(int type) /*-{
		this.type = type;
	}-*/;

	public final native void setNumber(int number) /*-{
		this.number = number;
	}-*/;
	
	public final native void setGroups(JsArray<Group> groups) /*-{
	 	this.groups = groups;
	}-*/;
	
	public final native void setRounds(JsArray<Round> rounds) /*-{
	 	this.rounds = rounds;
	}-*/;
}
