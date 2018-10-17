package com.futmesa.client.module.main.viewport.classification;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.businessinteligence.Round;
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
public class GamesTable {


	interface Resources extends ClientBundle {

		@Source("gamestable.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String customClassificationHeader();

		String customColumn();

		String customEvenColumn();

		String cellTable();
	}

	/**
	 * Builder customizado para o header da tabela de jogos.
	 *
	 */
	private class CustomHeaderBuilder extends AbstractHeaderOrFooterBuilder<Game> {

		private final String headerStyle;

		public CustomHeaderBuilder(CellTable<Game> dataGrid) {
			super(dataGrid, false);
			setSortIconStartOfLine(false);
			headerStyle = resources.styles().customClassificationHeader();
		}

		@Override
		protected boolean buildHeaderOrFooterImpl() {

			TableRowBuilder tr = startRow();

			tr = startRow();

			buildHeader(tr, "Jogador 1", true, false);
			buildHeader(tr, "", false, false);
			buildHeader(tr, "", false, false);
			buildHeader(tr, "", false, false);
			buildHeader(tr, "Jogador 2", false, true);

			tr.endTR();

			return true;
		}

		private void buildHeader(TableRowBuilder out, String headerStr, boolean isFirst, boolean isLast) {

			// Create the table cell.
			TableCellBuilder th = out.startTH().className(headerStyle);
			if (isFirst) {
				th.style().width(120, Unit.PX).textAlign(TextAlign.RIGHT).endStyle();
			}
			if (isLast) {
				th.style().width(120, Unit.PX).textAlign(TextAlign.LEFT).endStyle();
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
	 * Builder customizado para a renderização de cada linha da tabela de jogos.
	 *
	 */
	private class CustomTableBuilder extends AbstractCellTableBuilder<Game> {

		private final String cellStyles;

		public CustomTableBuilder(CellTable<Game> dataGrid) {
			super(dataGrid);

			cellStyles = resources.styles().customColumn();

//			evenCellStyles = new StringBuilder(resources.styles().customEvenColumn());
//			evenCellStyles.append(" " + resources.styles().customColumn());
		}

		@Override
		public void buildRowImpl(Game rowValue, int absRowIndex) {

			TableRowBuilder row = startRow();

			// Player 1
			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			td.style().textAlign(TextAlign.RIGHT).fontSize(17, Unit.PX).endStyle();
			td.text(rowValue.getPlayer1Name() );
			td.endTD();

			// Score 1
			td = row.startTD();
			td.className(cellStyles);
			String score1 = String.valueOf(rowValue.getScore1()) != "null" ? String.valueOf(rowValue.getScore1()) : "";
			td.text(score1);
			td.endTD();

			// X
			td = row.startTD();
			td.className(cellStyles);
			td.text( "X" );
			td.endTD();

			// Score 2
			td = row.startTD();
			td.className(cellStyles);
			String score2 = String.valueOf(rowValue.getScore2()) != "null" ? String.valueOf(rowValue.getScore2()) : "";
			td.text(score2);
			td.endTD();

			// Player 2
			td = row.startTD();
			td.className(cellStyles);
			td.style().textAlign(TextAlign.LEFT).fontSize(17, Unit.PX).endStyle();
			td.text(rowValue.getPlayer2Name() );
			td.endTD();

			row.endTR();
		}
	}

	private CellTable<Game> cellTable;

	private Resources resources;

	public GamesTable() {

		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		// Create a CellTable.
		cellTable = new CellTable<Game>(Game.KEY_PROVIDER);
		cellTable.setStyleName( resources.styles().cellTable() );
		cellTable.setSkipRowHoverStyleUpdate( true );

		// Specify a custom table.
		cellTable.setHeaderBuilder(new CustomHeaderBuilder(cellTable));
		cellTable.setTableBuilder(new CustomTableBuilder(cellTable));
	}

   public CellTable<Game> asWidget()
   {
	   return cellTable;
   }

	public void updateRounds(JsArray<Round> rounds) {
		
		List<Game> data = new ArrayList<Game>();
		for (int i = 0; i < rounds.length(); i++) {
			JsArray<Game> games = rounds.get(i).getGames();
			for (int j = 0; j < games.length(); j++) {
				data.add(games.get(j));
			}
		}
		cellTable.setRowData(data);
	}
}