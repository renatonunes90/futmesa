package com.futmesa.client.module.main.viewport.championship.classificatoryDeathMatch;

import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.module.main.viewport.championship.ChampionshipViewport;
import com.futmesa.client.module.main.viewport.championship.ChampionshipViewportConsts;
import com.futmesa.client.module.main.viewport.championship.phase.qualify.QualifyPhase;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport to championship free for all.
 */
public class ClassificatoryDeathMatchViewport extends ChampionshipViewport {

	private static final ClassificatoryDeathMatchUiBinder uiBinder = GWT.create(ClassificatoryDeathMatchUiBinder.class);

	interface ClassificatoryDeathMatchUiBinder extends UiBinder<VerticalPanel, ClassificatoryDeathMatchViewport> {
	}

	private ChampionshipViewportConsts constants;
	
	@UiField(provided = false)
	protected VerticalPanel panel;
	
	@UiField(provided = false)
	protected Button prevPhaseBtn;

	@UiField(provided = false)
	protected Button nextPhaseBtn;
	
	@UiField(provided = false)
	protected Label phaseLabel;
	
	@UiField(provided = false)
	protected HorizontalPanel phasePanel;
	
	private QualifyPhase qualifyPhase;
	
	public ClassificatoryDeathMatchViewport() {

		constants = GWT.create(ChampionshipViewportConsts.class);
		 
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		qualifyPhase = new QualifyPhase();
		phasePanel.add(qualifyPhase.asWidget());
		
		prevPhaseBtn.setEnabled(false);
		nextPhaseBtn.setEnabled(true);
		
		prevPhaseBtn.setText("<<");
		prevPhaseBtn.addClickHandler(handler -> {
			phaseLabel.setText(constants.qualifyPhase());
			prevPhaseBtn.setEnabled(false);
			nextPhaseBtn.setEnabled(true);
			
			phasePanel.clear();
			phasePanel.add(qualifyPhase.asWidget());
		});
		
		nextPhaseBtn.setText(">>");
		nextPhaseBtn.addClickHandler(handler -> {
			phaseLabel.setText(constants.deathMatchPhase());
			prevPhaseBtn.setEnabled(true);
			nextPhaseBtn.setEnabled(false);
			phasePanel.clear();
			
		});
	}

	@Override
	public Widget asWidget() {
		return panel;
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