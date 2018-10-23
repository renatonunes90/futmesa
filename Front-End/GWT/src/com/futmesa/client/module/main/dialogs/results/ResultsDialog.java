package com.futmesa.client.module.main.dialogs.results;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.google.gwt.cell.client.AbstractInputCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ibm.icu.util.BytesTrie.Result;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class ResultsDialog implements ServiceInterface {

	private static final ResultsDialogUiBinder uiBinder = GWT.create(ResultsDialogUiBinder.class);

	interface ResultsDialogUiBinder extends UiBinder<VerticalPanel, ResultsDialog> {
	}

	/**
	 * Constantes da classe.
	 */
	private ResultsDialogConsts constants;
	
	@UiField(provided = false)
	protected VerticalPanel panel;
	
	@UiField(provided = false)
	protected CellTable<Game> gameTable;

	@UiField(provided = false)
	protected Button insertResultBtn;
	
	private ServiceChampionship serviceChampionship;
	
    private DialogBox dialogBox;
    
    private List<Game> currentGames;
    
	/**
	 * Construtor padrão.
	 */
	public ResultsDialog() {

	    constants = GWT.create(ResultsDialogConsts.class);
	    
		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
		
		currentGames = new ArrayList<Game>();
		serviceChampionship = new ServiceChampionship( this );
		
		panel.setCellHorizontalAlignment( panel,  HasHorizontalAlignment.ALIGN_CENTER );
		panel.setCellHorizontalAlignment( insertResultBtn,  HasHorizontalAlignment.ALIGN_CENTER );
		
		createGrid();
		
	    // Create the dialog box
		dialogBox = new DialogBox();
	    dialogBox.setGlassEnabled(true);
	    dialogBox.setAnimationEnabled(true);
	    dialogBox.setText( constants.insertingResultsLabel() );
	    dialogBox.setWidget( panel );
	    
		insertResultBtn.addClickHandler( handler ->{
			serviceChampionship.insertResults( 1, currentGames );
		});
	}

	public Widget asWidget() {
		return panel;
	}

	public DialogBox getDialog()
	{
		return dialogBox;
	}
	
	public void setGames( List<Game> games) {
		currentGames.clear();
		currentGames.addAll( games );
		gameTable.setRowData( currentGames );
	}
	
	private void createGrid()
	{
	    // player 1
	    Column<Game, String> player1Column = new Column<Game, String>(new TextCell()) {
	      @Override
	      public String getValue(Game object) {
	        return object.getPlayer1Name();
	      }
	    };
	    gameTable.addColumn(player1Column, "Jogador 1");
	    gameTable.setColumnWidth(player1Column, 120, Unit.PX);
	    
		// score 1
	    final ScoreInputCell score1Cell = new ScoreInputCell();
		Column<Game, String> score1Column = new Column<Game, String>(score1Cell) {
			@Override
			public String getValue(Game object) {
				return "null".equals( String.valueOf(object.getScore1()) ) ? "" :  String.valueOf(object.getScore1()) ;
			}
		};
		score1Column.setFieldUpdater(new FieldUpdater<Game, String>() {
			@Override
			public void update(int index, Game object, String value) {
				int score = 0;
				if ( isScoreValid( value ) ) {
					score = Integer.parseInt( value );
				}
				for ( Game g : currentGames ) {
					if ( g.getId() == object.getId() ) {
						g.setScore1( score );
						break;
					}
				}
			}
		});
		gameTable.addColumn(score1Column, "");
		gameTable.setColumnWidth(score1Column, 40, Unit.PX);
		    
	    // X
	    Column<Game, String> versusColumn = new Column<Game, String>(new TextCell()) {
	      @Override
	      public String getValue(Game object) {
	        return "X";
	      }
	    };
	    gameTable.addColumn(versusColumn, "");
	    gameTable.setColumnWidth(versusColumn, 40, Unit.PX);
		
	    final ScoreInputCell score2Cell = new ScoreInputCell();
		
		// score 2
		Column<Game, String> score2Column = new Column<Game, String>( score2Cell ) {
			@Override
			public String getValue(Game object) {
				return "null".equals( String.valueOf(object.getScore2()) ) ? "" :  String.valueOf(object.getScore2()) ;
			}
		};
		score2Column.setFieldUpdater(new FieldUpdater<Game, String>() {
			@Override
			public void update(int index, Game object, String value) {
				int score = 0;
				if ( isScoreValid( value ) ) {
					score = Integer.parseInt( value );
				}
				for ( Game g : currentGames ) {
					if ( g.getId() == object.getId() ) {
						g.setScore2( score) ;
						break;
					}
				}
			}
		});
		gameTable.addColumn(score2Column, "");
		gameTable.setColumnWidth(score2Column, 40, Unit.PX);
		
	    // player 2
	    Column<Game, String> player2Column = new Column<Game, String>(new TextCell()) {
	      @Override
	      public String getValue(Game object) {
	        return object.getPlayer2Name();
	      }
	    };
	    gameTable.addColumn(player2Column, "Jogador 2");
	    gameTable.setColumnWidth(player2Column, 120, Unit.PX);
	}
	
	interface ScoreCellTemplate extends SafeHtmlTemplates {
		@Template("<input  type=\"number\" min=\"0\" max=\"20\" value=\"{0}\" tabindex=\"-1\" style=\"width:34px;\"></input>")
		SafeHtml input(String value);
	}

	private static class ScoreInputCell extends AbstractInputCell<String, String> {

		private static ScoreCellTemplate template;

		public ScoreInputCell() {
			super("change");
			if (template == null) {
				template = GWT.create(ScoreCellTemplate.class);
			}
		}

	    @Override
	    public void render(Context context, String value, SafeHtmlBuilder sb) {
	      sb.append(template.input(value));
	    }
		
		@Override
	    public void onBrowserEvent(Context context, Element parent, String value,
	        NativeEvent event, ValueUpdater<String> valueUpdater) {
	      super.onBrowserEvent(context, parent, value, event, valueUpdater);

	      // Ignore events that don't target the input.
	      Element target = event.getEventTarget().cast();
	      if (!parent.getFirstChildElement().isOrHasChild(target)) {
	        return;
	      }

	      Object key = context.getKey();
	      String viewData = getViewData(key);
	      if ("change".equals( event.getType() )) {
	        InputElement input = parent.getFirstChild().cast();

	        // Mark cell as containing a pending change
	        input.getStyle().setColor("blue");

	        // Save the new value in the view data.
	        if (viewData == null) {
	          setViewData(key, "");
	        }
	        String newValue = input.getValue();
	        viewData = newValue;
	        finishEditing(parent, newValue, key, valueUpdater);

	        // Update the value updater, which updates the field updater.
	        if (valueUpdater != null) {
	          valueUpdater.update(newValue);
	        }
	      }
	    }

//	    @Override
//	    protected void onEnterKeyDown(Context context, Element parent, String value,
//	        NativeEvent event, ValueUpdater<String> valueUpdater) {
//	      Element target = event.getEventTarget().cast();
//	      if (getInputElement(parent).isOrHasChild(target)) {
//	        finishEditing(parent, value, context.getKey(), valueUpdater);
//	      } else {
//	        super.onEnterKeyDown(context, parent, value, event, valueUpdater);
//	      }
//	    }
	    
	}

	private boolean isScoreValid(String score) {

		try {
			int value = Integer.parseInt(score);
			if ( value < 0 ) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}


	@Override
	public void onServiceResult(JavaScriptObject records, String requestId) {
		dialogBox.hide();
		
	}
}