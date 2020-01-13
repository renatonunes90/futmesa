package com.futmesa.client.module.main.viewport.championship.classificatoryDeathMatch;

import com.futmesa.client.builder.GameBuilder;
import com.futmesa.client.builder.RoundBuilder;
import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.module.main.viewport.championship.ChampionshipViewport;
import com.futmesa.client.module.main.viewport.championship.ChampionshipViewportConsts;
import com.futmesa.client.module.main.viewport.championship.phase.deathMatch.DeathMatchPhase;
import com.futmesa.client.module.main.viewport.championship.phase.qualify.QualifyPhase;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
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
	
	private DeathMatchPhase deathMatchPhase;
	
	public ClassificatoryDeathMatchViewport() {

		constants = GWT.create(ChampionshipViewportConsts.class);
		 
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		qualifyPhase = new QualifyPhase();
		deathMatchPhase = new DeathMatchPhase();
		
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
			phasePanel.add(deathMatchPhase.asWidget());
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

	@SuppressWarnings("unchecked")
	@Override
	public void updateRounds(JsArray<Round> rounds) {
		
		JsArray<Round> classificationRounds = ( JsArray< Round > ) JavaScriptObject.createArray(); 
		JsArray<Round> deathMatchRounds = ( JsArray< Round > ) JavaScriptObject.createArray(); 
		for ( int i =0; i < rounds.length(); i++) {
			if ( rounds.get(i).getPhase() == 1 ) {
				classificationRounds.push(rounds.get(i));
			} else {
				deathMatchRounds.push(rounds.get(i));
			}
				
		}
		qualifyPhase.updateRounds(classificationRounds);
		deathMatchPhase.updateRounds(deathMatchRounds);
		
//		JsArray<Game> games = ( JsArray< Game > ) JavaScriptObject.createArray(); 
//		
//		games.push(GameBuilder.buildGame("Player 1", "Player 2", 1, 2));
//		games.push(GameBuilder.buildGame("Player 3", "Player 4", 1, 0));
//		games.push(GameBuilder.buildGame("Player 5", "Player 6", 1, 0));
//		games.push(GameBuilder.buildGame("Player 7", "Player 8", 1, 2));
//		
//		JsArray<Game> gamesSemi = ( JsArray< Game > ) JavaScriptObject.createArray(); 
//		
//		gamesSemi.push(GameBuilder.buildGame("Player 2", "Player 3", 1, 0));
//		gamesSemi.push(GameBuilder.buildGame("Player 5", "Player 8", 1, 0));
//		
//		
//		JsArray<Game> gameFinal = ( JsArray< Game > ) JavaScriptObject.createArray(); 
//		
//		gameFinal.push(GameBuilder.buildGame("Player 2", "Player 5", 1, 0));
//		
//		Round secondRound = RoundBuilder.build(games);
//		Round thirdRound = RoundBuilder.build(gamesSemi);
//		Round finalRound = RoundBuilder.build(gameFinal);
//		
//		JsArray<Round> secondRounds = ( JsArray< Round > ) JavaScriptObject.createArray(); 
//		secondRounds.push(secondRound);
//		secondRounds.push(thirdRound);
//		secondRounds.push(finalRound);
//		
//		deathMatchPhase.updateRounds(deathMatchRounds);
	}

}