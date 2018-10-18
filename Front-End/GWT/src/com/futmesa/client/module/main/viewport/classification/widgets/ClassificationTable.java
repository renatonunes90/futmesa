package com.futmesa.client.module.main.viewport.classification.widgets;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.module.main.viewport.classification.ClassificationViewportConsts;
import com.google.gwt.cell.client.Cell.Context;
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
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.AbstractHeaderOrFooterBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextHeader;

/**
 * Classe com a tabela de classificação de um campeonato.
 */
public class ClassificationTable {


	interface Resources extends ClientBundle {

		@Source("classificationtable.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String customClassificationHeader();

		String customColumn();

		String customEvenColumn();

		String cellTable();
	}

	/**
	 * Builder customizado para o header da classificação.
	 *
	 */
	private class CustomHeaderBuilder extends AbstractHeaderOrFooterBuilder<Classification> {

		private final String headerStyle;

		public CustomHeaderBuilder(CellTable<Classification> dataGrid) {
			super(dataGrid, false);
			setSortIconStartOfLine(false);
			headerStyle = resources.styles().customClassificationHeader();
		}

		@Override
		protected boolean buildHeaderOrFooterImpl() {

			TableRowBuilder tr = startRow();

			tr = startRow();

			buildHeader(tr, constants.classificationColumn(), true);
			buildHeader(tr, "P", false);
			buildHeader(tr, "J", false);
			buildHeader(tr, "V", false);
			buildHeader(tr, "E", false);
			buildHeader(tr, "D", false);
			buildHeader(tr, "GP", false);
			buildHeader(tr, "GC", false);
			buildHeader(tr, "SD", false);
			buildHeader(tr, "%", false);

			tr.endTR();

			return true;
		}

		private void buildHeader(TableRowBuilder out, String headerStr, boolean isFirst) {

			// Create the table cell.
			TableCellBuilder th = out.startTH().className(headerStyle);
			if (isFirst) {
				th.style().width(240, Unit.PX).textAlign(TextAlign.LEFT).endStyle();
			}

			Header<String> header = new TextHeader(headerStr);

			// Render the header.
			Context context = new Context(0, 2, header.getKey());
			renderHeader(th, context, header);

			// End the table cell.
			th.endTH();
		}
	}

	/**
	 * Builder customizado para a renderização de cada linha da clssificação.
	 *
	 */
	private class CustomTableBuilder extends AbstractCellTableBuilder<Classification> {

		private final StringBuilder evenCellStyles;
		private final String cellStyles;
		private NumberFormat numberFormatter;

		public CustomTableBuilder(CellTable<Classification> dataGrid) {
			super(dataGrid);

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

	private CellTable<Classification> cellTable;

	private Resources resources;

	private ClassificationViewportConsts constants;

	public ClassificationTable() {

		// Create the internationalized error messages
	    constants = GWT.create(ClassificationViewportConsts.class);
		
		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		// Create a CellTable.
		cellTable = new CellTable<Classification>(Classification.KEY_PROVIDER);
		cellTable.setStyleName( resources.styles().cellTable() );
		cellTable.setSkipRowHoverStyleUpdate( true );

		// Specify a custom table.
		cellTable.setHeaderBuilder(new CustomHeaderBuilder(cellTable));
		cellTable.setTableBuilder(new CustomTableBuilder(cellTable));
	}

   public CellTable<Classification> asWidget()
   {
	   return cellTable;
   }

	public void updateClassification(JsArray<Classification> classifications) {
		List<Classification> data = new ArrayList<Classification>();
		for (int i = 0; i < classifications.length(); i++) {
			data.add(classifications.get(i));
		}
		cellTable.setRowData(data);
	}
}