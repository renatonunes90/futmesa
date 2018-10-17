package com.futmesa.client.module.main.viewport.classification;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Classification;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.NoSelectionModel;

/**
 * Viewport com exemplos de utilização do SimpleGrid.
 */
public class ClassificationViewport implements ViewportInterface {

	private static final ClassificationViewportUiBinder uiBinder = GWT.create(ClassificationViewportUiBinder.class);

	interface ClassificationViewportUiBinder extends UiBinder<HorizontalPanel, ClassificationViewport> {
	}

	/**
	 * The resources used by this example.
	 */
	interface Resources extends ClientBundle {

		/**
		 * Get the styles used but this example.
		 */
		@Source("classificationviewport.css")
		Styles styles();
	}

	/**
	 * The CSS Resources used by this example.
	 */
	interface Styles extends CssResource {

		String classificationTitle();
		
		String customColumn();

		String customEvenColumn();

		String cellTable();
	}

	/**
	 * Renders the data rows that display each contact in the table.
	 */
	private class CustomTableBuilder extends AbstractCellTableBuilder<Classification> {

		private final StringBuilder evenCellStyles;
		private final String cellStyles;
		private NumberFormat numberFormatter;

		public CustomTableBuilder(CellTable<Classification> dataGrid) {
			super(dataGrid);

			// Cache styles for faster access.
			// Calculate the cell styles.
			cellStyles = resources.styles().customColumn();

			evenCellStyles = new StringBuilder(resources.styles().customEvenColumn());
			evenCellStyles.append(" " + resources.styles().customColumn());

			numberFormatter = NumberFormat.getFormat("###.##");
		}

		@Override
		public void buildRowImpl(Classification rowValue, int absRowIndex) {

			TableRowBuilder row = startRow();

			// Player
			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			td.style().textAlign(TextAlign.LEFT).fontSize(17, Unit.PX).endStyle();
			td.text(String.valueOf(absRowIndex + 1) + " " + rowValue.getPlayerName());
			td.endTD();

			// Points
			td = row.startTD();
			td.className(evenCellStyles.toString());
			td.style().fontWeight(FontWeight.BOLD).endStyle();
			td.text(String.valueOf(rowValue.getPoints()));
			td.endTD();

			// Games
			td = row.startTD();
			td.className(cellStyles);
			td.text(String.valueOf(rowValue.getNumberOfGames()));
			td.endTD();

			// Wins
			td = row.startTD();
			td.className(evenCellStyles.toString());
			td.text(String.valueOf(rowValue.getWins()));
			td.endTD();

			// Ties
			td = row.startTD();
			td.className(cellStyles);
			td.text(String.valueOf(rowValue.getTies()));
			td.endTD();

			// Losses
			td = row.startTD();
			td.className(evenCellStyles.toString());
			td.text(String.valueOf(rowValue.getLosses()));
			td.endTD();

			// Goals pro
			td = row.startTD();
			td.className(cellStyles);
			td.text(String.valueOf(rowValue.getGoalsPro()));
			td.endTD();

			// Goals con
			td = row.startTD();
			td.className(evenCellStyles.toString());
			td.text(String.valueOf(rowValue.getGoalsCon()));
			td.endTD();

			// Goals difference
			td = row.startTD();
			td.className(cellStyles);
			td.text(String.valueOf(rowValue.getGoalsDiff()));
			td.endTD();

			// Win rate
			td = row.startTD();
			td.className(evenCellStyles.toString());
			td.text(numberFormatter.format(rowValue.getWinRate()));
			td.endTD();

			row.endTR();
		}
	}

	@UiField(provided = false)
	protected HorizontalPanel panel;

	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	protected CellTable<Classification> cellTable;

	/**
	 * The resources used by this example.
	 */
	private Resources resources;

	/**
	 * Construtor padrão.
	 */
	public ClassificationViewport() {

		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		// Create a CellTable.

		// Set a key provider that provides a unique key for each contact. If key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellTable = new CellTable<Classification>(Classification.KEY_PROVIDER);
//		cellTable.setWidth("100%", true);
//		cellTable.setTitle("Tabela" );

		// Specify a custom table.
		cellTable.setTableBuilder(new CustomTableBuilder(cellTable));

		// Initialize the columns.
		initTableColumns();

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns() {

		// Player name
		Column<Classification, String> firstNameColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return object.getPlayerName();
			}
		};

		cellTable.addColumn(firstNameColumn, "Classificação");
		cellTable.setColumnWidth(firstNameColumn, 240, Unit.PX);

		// Points
		Column<Classification, String> pointsColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getPoints());
			}
		};
		cellTable.addColumn(pointsColumn, "P");
		cellTable.setColumnWidth(pointsColumn, 60, Unit.PX);

		// Games
		Column<Classification, String> gamesColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getNumberOfGames());
			}
		};

		cellTable.addColumn(gamesColumn, "J");
		cellTable.setColumnWidth(gamesColumn, 60, Unit.PX);

		// Wins
		Column<Classification, String> winsColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getWins());
			}
		};

		cellTable.addColumn(winsColumn, "V");
		cellTable.setColumnWidth(winsColumn, 60, Unit.PX);

		// Ties
		Column<Classification, String> tiesColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getTies());
			}
		};

		cellTable.addColumn(tiesColumn, "E");
		cellTable.setColumnWidth(tiesColumn, 60, Unit.PX);

		// Losses
		Column<Classification, String> lossesColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getLosses());
			}
		};

		cellTable.addColumn(lossesColumn, "D");
		cellTable.setColumnWidth(lossesColumn, 60, Unit.PX);

		// Goals pro
		Column<Classification, String> goalsProColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getGoalsPro());
			}
		};

		cellTable.addColumn(goalsProColumn, "GP");
		cellTable.setColumnWidth(goalsProColumn, 60, Unit.PX);

		// Goals con
		Column<Classification, String> goalsConColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getGoalsCon());
			}
		};

		cellTable.addColumn(goalsConColumn, "GC");
		cellTable.setColumnWidth(goalsConColumn, 60, Unit.PX);

		// Goals difference
		Column<Classification, String> goalsDifferenceColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getGoalsDiff());
			}
		};

		cellTable.addColumn(goalsDifferenceColumn, "SD");
		cellTable.setColumnWidth(goalsDifferenceColumn, 60, Unit.PX);

		// Win rate
		Column<Classification, String> winRateColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf(object.getWinRate());
			}
		};

		cellTable.addColumn(winRateColumn, "%");
		cellTable.setColumnWidth(winRateColumn, 60, Unit.PX);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public void updateClassification(JsArray<Classification> classifications) {
		List<Classification> data = new ArrayList<Classification>();
		for (int i = 0; i < classifications.length(); i++) {
			data.add(classifications.get(i));
		}
		cellTable.setRowData(data);
	}
}