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
import com.google.gwt.user.cellview.client.DataGrid;
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

		String customGamesHeader();
		
		String customGamesSubHeader();

		String customColumn();

		String gameTable();
	}

	/**
	 * Builder customizado para o header da tabela de jogos.
	 *
	 */
	private class CustomHeaderBuilder extends AbstractHeaderOrFooterBuilder<Round> {

		private final String headerStyle;
		

		public CustomHeaderBuilder(CellTable<Round> dataGrid) {
			super(dataGrid, false);
			setSortIconStartOfLine(false);
			headerStyle = resources.styles().customGamesHeader();
		}

		@Override
		protected boolean buildHeaderOrFooterImpl() {

		      TableRowBuilder tr = startRow();

			buildHeader(tr, "", true, false);
			buildHeader(tr, "", false, false);
			buildHeader(tr, "", false, false);
			buildHeader(tr, "", false, false);
			buildHeader(tr, "", false, true);

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
	private class CustomTableBuilder extends AbstractCellTableBuilder<Round> {

		private final String cellStyles;
		
		private final String subHeaderStyle;

		public CustomTableBuilder(CellTable<Round> dataGrid) {
			super(dataGrid);

			cellStyles = resources.styles().customColumn();
			subHeaderStyle = resources.styles().customGamesSubHeader();
		}

		@Override
		public void buildRowImpl(Round rowValue, int absRowIndex) {

			TableRowBuilder row = startRow();
	        TableCellBuilder td = row.startTD().colSpan(5).className(subHeaderStyle);
	        td.text("Rodada " + String.valueOf( rowValue.getNumber() ) + " - " + rowValue.getBaseDate() + " " + rowValue.getBaseHour() ).endTD();
	        row.endTR();
		      
			JsArray<Game> games = rowValue.getGames();
			for (int i = 0; i < games.length(); i++) {
				row = startRow();
	
				// Player 1
				buildRow( row, games.get(i).getPlayer1Name(), true, false );
	
				// Score 1
				String score1 = String.valueOf(games.get(i).getScore1()) != "null" ? String.valueOf(games.get(i).getScore1()) : "";
				buildRow( row, score1, false, false );
	
				// X
				buildRow( row, "X", false, false );
	
				// Score 2
				String score2 = String.valueOf(games.get(i).getScore2()) != "null" ? String.valueOf(games.get(i).getScore2()) : "";
				buildRow( row, score2, false, false );
	
				// Player 2
				buildRow( row, games.get(i).getPlayer2Name(), false, true );
			}

			row.endTR();
		}
		
		private void buildRow( TableRowBuilder row, String value, boolean isPlayer1, boolean isPlayer2  ) {
			
			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			if ( isPlayer1 ) {
				td.style().textAlign(TextAlign.RIGHT).fontSize(17, Unit.PX).endStyle();
			}
			else if ( isPlayer2 ) {
				td.style().textAlign(TextAlign.LEFT).fontSize(17, Unit.PX).endStyle();
			}
			td.text( value );
			td.endTD();
		}
		
	}

	private CellTable<Round> cellTable;

	private Resources resources;
	
	private Round currentRound;

	public GamesTable() {

		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		currentRound = null;
		
		// Create a CellTable.
		cellTable = new CellTable<Round>(Round.KEY_PROVIDER);
		cellTable.setStyleName( resources.styles().gameTable() );
		cellTable.setSkipRowHoverStyleUpdate( true );

		// Specify a custom table.
		cellTable.setHeaderBuilder(new CustomHeaderBuilder(cellTable));
		cellTable.setTableBuilder(new CustomTableBuilder(cellTable));
	}

   public CellTable<Round> asWidget()
   {
	   return cellTable;
   }

	public void updateRounds(JsArray<Round> rounds, int roundNumber ) {

		List<Round> data = new ArrayList<Round>();
		for (int i = 0; i < rounds.length(); i++) {
			if ( rounds.get(i).getNumber() == roundNumber ) {
				currentRound = rounds.get(i);
			}
		}
		
		for (int i = 0; i < rounds.length(); i++) {
			if ( currentRound.getBaseDate().equals( rounds.get(i).getBaseDate() ) )			{
				data.add( rounds.get( i ) );
			}
		}
		cellTable.setRowData(data);
	}
}