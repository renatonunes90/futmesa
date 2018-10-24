package com.futmesa.client.module.main.dialogs.results;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.request.service.ServiceChampionship;
import com.futmesa.client.request.service.base.ServiceInterface;
import com.google.gwt.cell.client.AbstractInputCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class ResultsDialog implements ServiceInterface {

	private static final ResultsDialogUiBinder uiBinder = GWT.create(ResultsDialogUiBinder.class);

	interface ResultsDialogUiBinder extends UiBinder<VerticalPanel, ResultsDialog> {
	}

	interface Resources extends ClientBundle {

		@Source("resultsdialog.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String resultGameTable();

		String customGamesHeader();

		String customColumn();

		String cancelBtn();

		String insertResultBtn();
	}

	/**
	 * Constantes da classe.
	 */
	private ResultsDialogConsts constants;

	/**
	 * Estilos da tabela.
	 */
	private Resources resources;

	private ServiceChampionship serviceChampionship;

	private Column<Game, String> score1Column;

	private Column<Game, String> score2Column;

	private DialogBox dialogBox;

	private Championship championship;
	
	private List<Game> currentGames;

	@UiField(provided = false)
	protected VerticalPanel panel;

	@UiField(provided = false)
	protected CellTable<Game> gameTable;

	@UiField(provided = false)
	protected HorizontalPanel btnPanel;

	@UiField(provided = false)
	protected Button cancelBtn;

	@UiField(provided = false)
	protected Button insertResultBtn;

	/**
	 * Construtor padrão.
	 */
	public ResultsDialog() {

		constants = GWT.create(ResultsDialogConsts.class);

		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);

		championship = null;
		currentGames = new ArrayList<Game>();
		serviceChampionship = new ServiceChampionship(this);

		panel.setCellHorizontalAlignment(panel, HasHorizontalAlignment.ALIGN_CENTER);
		panel.setCellHorizontalAlignment(btnPanel, HasHorizontalAlignment.ALIGN_CENTER);

		createGrid();

		// Create the dialog box
		dialogBox = new DialogBox();
		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setText(constants.insertResultsLabel());
		dialogBox.setWidget(panel);

		cancelBtn.addClickHandler(handler -> {
			dialogBox.hide();
		});

		insertResultBtn.addClickHandler(handler -> {
			serviceChampionship.insertResults( championship.getId(), currentGames );
		});
	}

	public Widget asWidget() {
		return panel;
	}

	public DialogBox getDialog() {
		return dialogBox;
	}

	public void setChampionship( Championship championship )
	{
		this.championship = championship;
	}
	
	public void setGames(List<Game> games) {
		currentGames.clear();
		currentGames.addAll(games);
		gameTable.setRowData(currentGames);
	}

	private void createGrid() {
		gameTable.setTableBuilder(new CustomTableBuilder(gameTable));
		gameTable.setSkipRowHoverStyleUpdate(true);

		TextHeader header = new TextHeader("");
		header.setHeaderStyleNames(resources.styles().customGamesHeader());

		// player 1
		Column<Game, String> player1Column = new Column<Game, String>(new TextCell()) {
			@Override
			public String getValue(Game object) {
				return object.getPlayer1Name();
			}
		};
		gameTable.addColumn(player1Column, header);
		gameTable.setColumnWidth(player1Column, 120, Unit.PX);

		// score 1
		final ScoreInputCell score1Cell = new ScoreInputCell();
		score1Column = new Column<Game, String>(score1Cell) {
			@Override
			public String getValue(Game object) {
				return "null".equals(String.valueOf(object.getScore1())) ? "" : String.valueOf(object.getScore1());
			}
		};
		score1Column.setFieldUpdater(new FieldUpdater<Game, String>() {
			@Override
			public void update(int index, Game object, String value) {
				int score = 0;
				if (isScoreValid(value)) {
					score = Integer.parseInt(value);
				}
				for (Game g : currentGames) {
					if (g.getId() == object.getId()) {
						g.setScore1(score);
						break;
					}
				}
			}
		});
		gameTable.addColumn(score1Column, header);
		gameTable.setColumnWidth(score1Column, 40, Unit.PX);

		// X
		Column<Game, String> versusColumn = new Column<Game, String>(new TextCell()) {
			@Override
			public String getValue(Game object) {
				return constants.versusSymbol();
			}
		};
		gameTable.addColumn(versusColumn, header);
		gameTable.setColumnWidth(versusColumn, 40, Unit.PX);

		final ScoreInputCell score2Cell = new ScoreInputCell();

		// score 2
		score2Column = new Column<Game, String>(score2Cell) {
			@Override
			public String getValue(Game object) {
				return "null".equals(String.valueOf(object.getScore2())) ? "" : String.valueOf(object.getScore2());
			}
		};
		score2Column.setFieldUpdater(new FieldUpdater<Game, String>() {
			@Override
			public void update(int index, Game object, String value) {
				int score = 0;
				if (isScoreValid(value)) {
					score = Integer.parseInt(value);
				}
				for (Game g : currentGames) {
					if (g.getId() == object.getId()) {
						g.setScore2(score);
						break;
					}
				}
			}
		});
		gameTable.addColumn(score2Column, header);
		gameTable.setColumnWidth(score2Column, 40, Unit.PX);

		// player 2
		Column<Game, String> player2Column = new Column<Game, String>(new TextCell()) {
			@Override
			public String getValue(Game object) {
				return object.getPlayer2Name();
			}
		};
		gameTable.addColumn(player2Column, header);
		gameTable.setColumnWidth(player2Column, 120, Unit.PX);
	}

	private boolean isScoreValid(String score) {

		try {
			int value = Integer.parseInt(score);
			if (value < 0) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	@Override
	public void onServiceResult(JavaScriptObject records, String requestId) {
		if ( ServiceChampionship.INSERT_RESULTS.equals( requestId ) ) {
			dialogBox.hide();
			Window.alert( constants.successMsg() );
			Window.Location.assign( "?championship=" + championship.getId() );
		}
	}

	/**
	 * Célula customizada para colocar o score do jogo.
	 */
	public static class ScoreInputCell extends AbstractInputCell<String, String> {

		interface ScoreCellTemplate extends SafeHtmlTemplates {
			@Template("<input  type=\"number\" min=\"0\" max=\"20\" value=\"{0}\" tabindex=\"-1\" style=\"width:34px;\"></input>")
			SafeHtml input(String value);
		}
		
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
		public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
				ValueUpdater<String> valueUpdater) {
			super.onBrowserEvent(context, parent, value, event, valueUpdater);

			// ignora eventos que não são do input
			Element target = event.getEventTarget().cast();
			if (!parent.getFirstChildElement().isOrHasChild(target)) {
				return;
			}

			Object key = context.getKey();
			String viewData = getViewData(key);
			if ("change".equals(event.getType())) {
				InputElement input = parent.getFirstChild().cast();

				// muda a cor para indicar que houve mudança
				input.getStyle().setColor("blue");

				// salva o novo valor
				if (viewData == null) {
					setViewData(key, "");
				}
				String newValue = input.getValue();
				viewData = newValue;
				finishEditing(parent, newValue, key, valueUpdater);

				// atualiza no grid
				if (valueUpdater != null) {
					valueUpdater.update(newValue);
				}
			}
		}
	}

	/**
	 * Builder customizado para a renderização de cada linha da tabela de jogos.
	 */
	private class CustomTableBuilder extends AbstractCellTableBuilder<Game> {

		private final String cellStyles;

		public CustomTableBuilder(CellTable<Game> dataGrid) {
			super(dataGrid);

			cellStyles = resources.styles().customColumn();
		}

		@Override
		public void buildRowImpl(Game rowValue, int absRowIndex) {

			TableRowBuilder row = startRow();

			// adiciona linha do jogo
			row = startRow();

			// player 1
			buildRow(row, rowValue.getPlayer1Name(), true, false);

			// score 1
			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			renderCell(td, createContext(1), score1Column, rowValue);
			td.endTD();

			// versus
			buildRow(row, constants.versusSymbol(), false, false);

			// score 2
			td = row.startTD();
			td.className(cellStyles);
			renderCell(td, createContext(3), score2Column, rowValue);
			td.endTD();

			// player 2
			buildRow(row, rowValue.getPlayer2Name(), false, true);

			row.endTR();
		}

		private void buildRow(TableRowBuilder row, String value, boolean isPlayer1, boolean isPlayer2) {

			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			if (isPlayer1) {
				td.style().textAlign(TextAlign.RIGHT).fontSize(17, Unit.PX).endStyle();
			} else if (isPlayer2) {
				td.style().textAlign(TextAlign.LEFT).fontSize(17, Unit.PX).endStyle();
			}
			td.text(value);
			td.endTD();
		}

	}

}