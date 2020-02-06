package com.futmesa.client.module.main.viewport.championship.phase.deathMatch;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.businessinteligence.Round;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widgets composing a championship death match phase.
 */
public class DeathMatchPhase {

	private static final DeathMatchPhaseUiBinder uiBinder = GWT.create(DeathMatchPhaseUiBinder.class);

	interface DeathMatchPhaseUiBinder extends UiBinder<HorizontalPanel, DeathMatchPhase> {
	}
	
	/**
	 * Load css resources.
	 */
	interface Resources extends ClientBundle {

		@Source("deathmatchphase.css")
		Styles styles();
	}
	
	interface Styles extends CssResource {

		String customLeftPanel();
		String semiDown();
		String playerDisqualified();
		String finalPanel();
		String versusCentral();
		String playerSpot();
		String versusLabel();
		String customRightPanel();
		String semiPanel();
		String playerLabel();
		
	}
	
	private Resources resources;
	
	@UiField(provided = false)
	protected HorizontalPanel deathMatchPhasePanel;

	@UiField(provided = false)
	protected HorizontalPanel leftPanel;

	@UiField(provided = false)
	protected HorizontalPanel rightPanel;
	
	@UiField(provided = false)
	protected VerticalPanel quarterLeftPanel;
	
	@UiField(provided = false)
	protected VerticalPanel quarterRightPanel;

	@UiField(provided = false)
	protected Label playerName1;
	
	@UiField(provided = false)
	protected Label playerScore1;
	
	@UiField(provided = false)
	protected Label playerName2;
	
	@UiField(provided = false)
	protected Label playerScore2;
	
	@UiField(provided = false)
	protected Label playerName3;
	
	@UiField(provided = false)
	protected Label playerScore3;

	@UiField(provided = false)
	protected Label playerName4;
	
	@UiField(provided = false)
	protected Label playerScore4;
	
	@UiField(provided = false)
	protected Label playerName5;
	
	@UiField(provided = false)
	protected Label playerScore5;
	
	@UiField(provided = false)
	protected Label playerName6;
	
	@UiField(provided = false)
	protected Label playerScore6;
	
	@UiField(provided = false)
	protected Label playerName7;
	
	@UiField(provided = false)
	protected Label playerScore7;
	
	@UiField(provided = false)
	protected Label playerName8;
	
	@UiField(provided = false)
	protected Label playerScore8;
	
	@UiField(provided = false)
	protected Label playerSemi1;
	
	@UiField(provided = false)
	protected Label playerScoreSemi1;
	
	@UiField(provided = false)
	protected Label playerSemi2;
	
	@UiField(provided = false)
	protected Label playerScoreSemi2;
	
	@UiField(provided = false)
	protected Label playerSemi3;
	
	@UiField(provided = false)
	protected Label playerScoreSemi3;

	@UiField(provided = false)
	protected Label playerSemi4;
	
	@UiField(provided = false)
	protected Label playerScoreSemi4;
	
	@UiField(provided = false)
	protected Label playerFinal1;
	
	@UiField(provided = false)
	protected Label playerScoreFinal1;

	@UiField(provided = false)
	protected Label playerFinal2;
	
	@UiField(provided = false)
	protected Label playerScoreFinal2;
	
	private List<Label> playerRef;
	private List<Label> playerScoreRef;
	
	private List<Label> playerSemiRef;
	private List<Label> playerSemiScoreRef;
	
	private List<Label> playerFinalRef;
	private List<Label> playerFinalScoreRef;
	
	public DeathMatchPhase() {
	 
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
		
		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();
		
		playerRef = new ArrayList<Label>();
		playerRef.add(playerName1);
		playerRef.add(playerName2);
		playerRef.add(playerName3);
		playerRef.add(playerName4);
		playerRef.add(playerName5);
		playerRef.add(playerName6);
		playerRef.add(playerName7);
		playerRef.add(playerName8);
		
		playerScoreRef = new ArrayList<Label>();
		playerScoreRef.add(playerScore1);
		playerScoreRef.add(playerScore2);
		playerScoreRef.add(playerScore3);
		playerScoreRef.add(playerScore4);
		playerScoreRef.add(playerScore5);
		playerScoreRef.add(playerScore6);
		playerScoreRef.add(playerScore7);
		playerScoreRef.add(playerScore8);
		
		playerSemiRef = new ArrayList<Label>();
		playerSemiRef.add(playerSemi1);
		playerSemiRef.add(playerSemi2);
		playerSemiRef.add(playerSemi3);
		playerSemiRef.add(playerSemi4);
		
		playerSemiScoreRef = new ArrayList<Label>();
		playerSemiScoreRef.add(playerScoreSemi1);
		playerSemiScoreRef.add(playerScoreSemi2);
		playerSemiScoreRef.add(playerScoreSemi3);
		playerSemiScoreRef.add(playerScoreSemi4);
		
		playerFinalRef = new ArrayList<Label>();
		playerFinalRef.add(playerFinal1);
		playerFinalRef.add(playerFinal2);
		
		playerFinalScoreRef = new ArrayList<Label>();
		playerFinalScoreRef.add(playerScoreFinal1);
		playerFinalScoreRef.add(playerScoreFinal2);
	}

	public Widget asWidget() {
		return deathMatchPhasePanel;
	}

	@SuppressWarnings("unchecked")
	public void updateRounds(JsArray<Round> rounds) {		
		
		if ( rounds.get(0).getGames().length() > 0) {
			if ( rounds.length() > 2 ) {	
				
				quarterLeftPanel.setVisible(true);
				quarterRightPanel.setVisible(true);
				
				JsArray<Game> games = rounds.get(0).getGames();
				int playerCount = 0;
				for ( int i=0; i< games.length(); i++ ) {
					fillGame(games.get(i), playerRef.get(playerCount), playerScoreRef.get(playerCount++), playerRef.get(playerCount), playerScoreRef.get(playerCount++));
				}
				
				JsArray<Round> semiRounds = ( JsArray< Round > ) JavaScriptObject.createArray();
				for(int i = 1; i<rounds.length(); i++ ) {
					semiRounds.push(rounds.get(i));
				}
				
				fillSemiFinals(semiRounds);
			} else if ( rounds.length() > 1 ) {
				
				quarterLeftPanel.setVisible(false);
				quarterRightPanel.setVisible(false);
				fillSemiFinals(rounds);
			}
		}
	}
	
	
	private void fillSemiFinals(JsArray<Round> rounds) {
		if ( rounds.length() > 0 && rounds.get(0).getGames().length() > 0 ) {
			JsArray<Game> games = rounds.get(0).getGames();
			int playerSemiCount = 0;
			for ( int i=0; i< games.length(); i++ ) {
				fillGame(games.get(i), playerSemiRef.get(playerSemiCount), playerSemiScoreRef.get(playerSemiCount++), playerSemiRef.get(playerSemiCount), playerSemiScoreRef.get(playerSemiCount++));
			}
			
			if ( rounds.length() > 1 && rounds.get(1).getGames().length() > 0 ) {
				games = rounds.get(1).getGames();
				fillGame(games.get(0), playerFinalRef.get(0), playerFinalScoreRef.get(0), playerFinalRef.get(1), playerFinalScoreRef.get(1));
				
			} else {
				games = rounds.get(0).getGames();
				for ( int i=0; i< games.length(); i++ ) {
					resolveGame(games.get(i), playerFinalRef.get(i));
				}
			}
			
		} else {
			JsArray<Game> games = rounds.get(0).getGames();
			for ( int i=0; i< games.length(); i++ ) {
				resolveGame(games.get(i), playerSemiRef.get(i));
			}
		}
	}
	
	private void fillGame(Game game, Label player1, Label score1, Label player2, Label score2) {
		player1.setText(game.getPlayer1Name());
		player2.setText(game.getPlayer2Name());

		if ( game.hasScores() ) {
			score1.setText(String.valueOf(game.getScore1()));
			score2.setText(String.valueOf(game.getScore2()));
			
			if ( game.player1IsWinner() ) {
				player2.addStyleName(resources.styles().playerDisqualified());
				score2.addStyleName(resources.styles().playerDisqualified());
			} else {
				player1.addStyleName(resources.styles().playerDisqualified());
				score1.addStyleName(resources.styles().playerDisqualified());
			}
		} else {
			score1.setText("0");
			score2.setText("0");
		}
	}
	
	private void resolveGame(Game game, Label player) {
		if ( game.hasScores() ) {
			if ( game.getScore1() > game.getScore2() ) {
				player.setText(game.getPlayer1Name());
			} else {
				player.setText(game.getPlayer2Name());
			}
		}
	}

}