package com.futmesa.client.module.main.viewport.championship.classificatoryDeathMatch;

import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.module.main.dialogs.results.ResultsDialog;
import com.futmesa.client.module.main.viewport.championship.ChampionshipViewport;
import com.futmesa.client.module.main.viewport.championship.ChampionshipViewportConsts;
import com.futmesa.client.module.main.widgets.classification.ClassificationTable;
import com.futmesa.client.module.main.widgets.games.GamesTable;
import com.futmesa.client.module.main.widgets.games.GamesTableConsts;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
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
	protected VerticalPanel leftPanel;

	@UiField(provided = false)
	protected VerticalPanel rightPanel;

	@UiField(provided = false)
	protected SimplePanel gameTablePanel;
	
	@UiField(provided = false)
	protected Button prevPhaseBtn;

	@UiField(provided = false)
	protected Button nextPhaseBtn;
	
	@UiField(provided = false)
	protected Label phaseLabel;
	
	// @UiField(provided = false)
	// protected Button insertResultBtn;

	private ClassificationTable classification;

	private GamesTable games;

	private ResultsDialog resultsDialog;

	public ClassificatoryDeathMatchViewport() {

		constants = GWT.create(ChampionshipViewportConsts.class);
		 
		classification = new ClassificationTable();
		games = new GamesTable();
		resultsDialog = new ResultsDialog();

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		leftPanel.add(classification.asWidget());
		gameTablePanel.add(games.asWidget());

		prevPhaseBtn.setEnabled(false);
		nextPhaseBtn.setEnabled(true);
		
		prevPhaseBtn.setText("<<");
		prevPhaseBtn.addClickHandler(handler -> {
			phaseLabel.setText(constants.qualifyPhase());
			prevPhaseBtn.setEnabled(false);
			nextPhaseBtn.setEnabled(true);
		});
		
		nextPhaseBtn.setText(">>");
		nextPhaseBtn.addClickHandler(handler -> {
			phaseLabel.setText(constants.deathMatchPhase());
			prevPhaseBtn.setEnabled(true);
			nextPhaseBtn.setEnabled(false);
		});
		// rightPanel.setCellHorizontalAlignment( insertResultBtn,
		// HasHorizontalAlignment.ALIGN_RIGHT);
		// panel.setCellHorizontalAlignment( championshipLabel,
		// HasHorizontalAlignment.ALIGN_CENTER );

		/*
		 * insertResultBtn.addClickHandler( handler ->{ resultsDialog.setChampionship(
		 * championship ); resultsDialog.setGames( games.getDisplayedGames() );
		 * resultsDialog.getDialog().center(); resultsDialog.getDialog(); })
		 */;
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void updateClassification(JsArray<Classification> classification) {
		this.classification.updateClassification(classification);
	}

	@Override
	public void updateRounds(JsArray<Round> rounds) {
		games.setRounds(rounds, 21);
	}

}