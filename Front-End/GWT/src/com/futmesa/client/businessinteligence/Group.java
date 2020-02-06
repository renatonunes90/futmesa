package com.futmesa.client.businessinteligence;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.view.client.ProvidesKey;

/**
 * Represents back-end object.
 */
public final class Group extends JavaScriptObject {

	protected Group() {
	}

	/**
	 * The key provider that provides the unique ID.
	 */
	public static final ProvidesKey<Group> KEY_PROVIDER = new ProvidesKey<Group>() {
		@Override
		public Object getKey(Group item) {
			return item == null ? null : item.getId();
		}
	};

	public final native int getId() /*-{
		return this.id;
	}-*/;

	public final native int getIdChampionship() /*-{
		return this.idchampionship;
	}-*/;

	public final native int getIdPhase() /*-{
		return this.idphase;
	}-*/;

	public final native String getName() /*-{
		return this.name;
	}-*/;

	public final native JsArray<Player> getMembers() /*-{
		return this.members;
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
	
	public final native void setIdPhase(int idphase) /*-{
		this.idphase = idphase;
	}-*/;


	public final native void setName(String name) /*-{
		this.name = name;
	}-*/;

	public final native void setMembers(JsArray<Player> members) /*-{
	 	this.members = members;
	}-*/;
	
	public final native void setRounds(JsArray<Round> rounds) /*-{
	 	this.rounds = rounds;
	}-*/;
}
