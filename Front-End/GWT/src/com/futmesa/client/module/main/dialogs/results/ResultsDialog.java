package com.futmesa.client.module.main.dialogs.results;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Round;
import com.futmesa.client.module.main.widgets.games.GamesTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class ResultsDialog implements ViewportInterface {

	private static final ResultsDialogUiBinder uiBinder = GWT.create(ResultsDialogUiBinder.class);

	interface ResultsDialogUiBinder extends UiBinder<VerticalPanel, ResultsDialog> {
	}

	@UiField(provided = false)
	protected VerticalPanel panel;
	
	@UiField(provided = false)
	protected SimplePanel gameTablePanel;

	@UiField(provided = false)
	protected Button insertResultBtn;
	
    private DialogBox dialogBox;
    
	private GamesTable games;

	/**
	 * Construtor padrão.
	 */
	public ResultsDialog() {

		games = new GamesTable();

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		gameTablePanel.add(games.asWidget());
		
	    // Create the dialog box
		dialogBox = new DialogBox();
	    dialogBox.setGlassEnabled(true);
	    dialogBox.setAnimationEnabled(true);
	    dialogBox.setText( "Exemplo" );
//	    dialogContents.setSize("400px", "400px");
	    dialogBox.setWidget( panel );
	    
		insertResultBtn.addClickHandler( handler ->{
			dialogBox.hide();
		});
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public DialogBox getDialog()
	{
		return dialogBox;
	}
	
	public void setRounds(JsArray<Round> rounds) {
		games.setRounds( rounds, 7 );
	}
}