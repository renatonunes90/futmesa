package com.futmesa.client.module.config.widgets.championshipform;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Championship;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget com o formulário de configuração de um campeonato.
 */
public class ChampionshipForm implements ViewportInterface {

	private static final ChampionshipFormUiBinder uiBinder = GWT.create(ChampionshipFormUiBinder.class);

	interface ChampionshipFormUiBinder extends UiBinder<HorizontalPanel, ChampionshipForm> {
	}

	@UiField(provided = false)
	protected HorizontalPanel panel;
	
	@UiField(provided = false)
   protected TextBox nameInput;

   @UiField(provided = false)
   protected TextBox seasonInput;
  
   @UiField(provided = false)
   protected TextBox typeInput;
   
   @UiField(provided = false)
   protected TextBox baseDateInput;
   
   @UiField(provided = false)
   protected TextBox gamesByRoundInput;
   
   @UiField(provided = false)
   protected TextBox roundsByDayInput;
   
   @UiField(provided = false)
   protected TextBox dateIncrInput;

	private Championship championship;
	
	/**
	 * Construtor padrão.
	 */
	public ChampionshipForm() {

		championship = null;

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public Championship getChampionship()
	{
	   championship.setName( nameInput.getValue() );
	   championship.setBaseDate( baseDateInput.getValue() );
	   
	   int value = !seasonInput.getValue().isEmpty() ? Integer.parseInt( seasonInput.getValue() ) : 0;
	   championship.setIdSeason( value );
	   
	   value = !typeInput.getValue().isEmpty() ? Integer.parseInt( typeInput.getValue() ) : 0;
	   championship.setType( value );
	   
	   value = !gamesByRoundInput.getValue().isEmpty() ? Integer.parseInt( gamesByRoundInput.getValue() ) : 0;
	   championship.setGamesByRound( value );
	   
	   value = !roundsByDayInput.getValue().isEmpty() ? Integer.parseInt( roundsByDayInput.getValue() ) : 0;
	   championship.setRoundsByDay( value );
	   
	   value = !dateIncrInput.getValue().isEmpty() ? Integer.parseInt( dateIncrInput.getValue() ) : 0;
	   championship.setDateIncr( value );
	   
	   return championship;
	}
	
	public void setChampionship( Championship championship )
	{
		this.championship = championship;
		
		nameInput.setValue( championship.getName() );
	   seasonInput.setValue( String.valueOf( championship.getIdSeason() ) );
	   typeInput.setValue( String.valueOf( championship.getIdSeason() ) );
	   baseDateInput.setValue( championship.getBaseDate() );
	   gamesByRoundInput.setValue( String.valueOf( championship.getGamesByRound() ) );
	   roundsByDayInput.setValue( String.valueOf( championship.getRoundsByDay() ) );
	   dateIncrInput.setValue( String.valueOf( championship.getDateIncr() ) );
	}
	
}