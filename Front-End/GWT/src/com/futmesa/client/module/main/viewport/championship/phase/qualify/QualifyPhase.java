package com.futmesa.client.module.main.viewport.championship.phase.qualify;

import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.module.main.dialogs.results.ResultsDialog;
import com.futmesa.client.module.main.widgets.classification.ClassificationTable;
import com.futmesa.client.module.main.widgets.games.GamesTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widgets composing a championship qualify phase.
 */
public class QualifyPhase {

	private static final QualifyPhaseUiBinder uiBinder = GWT.create(QualifyPhaseUiBinder.class);

	interface QualifyPhaseUiBinder extends UiBinder<VerticalPanel, QualifyPhase> {
	}
	
	@UiField(provided = false)
	protected VerticalPanel qualifyPhasePanel;
	
	@UiField(provided = false)
	protected Label groupName;

	@UiField(provided = false)
	protected VerticalPanel leftPanel;

	@UiField(provided = false)
	protected VerticalPanel rightPanel;

	@UiField(provided = false)
	protected SimplePanel gameTablePanel;
	
	// @UiField(provided = false)
	// protected Button insertResultBtn;

	private ClassificationTable classification;

	private GamesTable games;

	private ResultsDialog resultsDialog;

	public QualifyPhase() {
	 
		classification = new ClassificationTable();
		games = new GamesTable();
		resultsDialog = new ResultsDialog();

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		groupName.setVisible(false);
		leftPanel.add(classification.asWidget());
		gameTablePanel.add(games.asWidget());

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

	public Widget asWidget() {
		return qualifyPhasePanel;
	}

	public void updateClassification(JsArray<Classification> classification) {
		this.classification.updateClassification(classification);
	}

	public void updateRounds(JsArray<Round> rounds) {
		games.setRounds(rounds, 1);
	}
	
	public void setGroupName(String groupName) {
		this.groupName.setText(groupName);
		this.groupName.setVisible(true);
	}

}