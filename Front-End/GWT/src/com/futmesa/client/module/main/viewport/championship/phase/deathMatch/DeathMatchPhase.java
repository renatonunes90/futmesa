package com.futmesa.client.module.main.viewport.championship.phase.deathMatch;

import java.util.List;

import com.futmesa.client.businessinteligence.Round;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widgets composing a championship death match phase.
 */
public class DeathMatchPhase {

	private static final DeathMatchPhaseUiBinder uiBinder = GWT.create(DeathMatchPhaseUiBinder.class);

	interface DeathMatchPhaseUiBinder extends UiBinder<HorizontalPanel, DeathMatchPhase> {
	}
	
	@UiField(provided = false)
	protected HorizontalPanel deathMatchPhasePanel;

	@UiField(provided = false)
	protected HorizontalPanel leftPanel;

	@UiField(provided = false)
	protected HorizontalPanel rightPanel;

	private List<Round> allRounds;
	
	public DeathMatchPhase() {
	 
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);


	}

	public Widget asWidget() {
		return deathMatchPhasePanel;
	}

	public void updateRounds(JsArray<Round> rounds) {
		allRounds.clear();
		for (int i = 0; i < rounds.length(); i++) {
			allRounds.add( rounds.get( i ) );
		}
	}

}