package com.futmesa.client.module.main.viewport.championship.freeForAll;

import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.module.main.viewport.championship.ChampionshipViewport;
import com.futmesa.client.module.main.viewport.championship.phase.qualify.QualifyPhase;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport to championship free for all.
 */
public class FreeForAllViewport extends ChampionshipViewport {

	private static final ClassificationViewportUiBinder uiBinder = GWT.create(ClassificationViewportUiBinder.class);

	interface ClassificationViewportUiBinder extends UiBinder<HorizontalPanel, FreeForAllViewport> {
	}

	@UiField(provided = false)
	protected HorizontalPanel phasePanel;
	
	private QualifyPhase qualifyPhase;
	
	public FreeForAllViewport() {

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		qualifyPhase = new QualifyPhase();
		phasePanel.add(qualifyPhase.asWidget());
	}

	@Override
	public Widget asWidget() {
		return phasePanel;
	}

	@Override
	public void updateClassification(JsArray<Classification> classification) {
		qualifyPhase.updateClassification(classification);
	}

	@Override
	public void updateRounds(JsArray<Round> rounds) {
		qualifyPhase.updateRounds(rounds);
	}


}