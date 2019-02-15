package com.futmesa.client.module.main.widgets.games;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.futmesa.client.businessinteligence.Game;
import com.futmesa.client.businessinteligence.Round;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.AbstractHeaderOrFooterBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Classe com a tabela de classificação de um campeonato.
 */
public class GamesTable {


	interface Resources extends ClientBundle {

		@Source("gamestable.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String gameTable();
		
		String customGamesHeader();
		
		String customRoundSubHeader();

		String customGameTableRow();
		
		String customColumn();
		
		String customNextBtn();
		
		String customPrevBtn();
	}

	/**
	 * Constantes da classe.
	 */
	private GamesTableConsts constants;
	
	/**
	 * Estilos da tabela.
	 */
	private Resources resources;

	/**
	 * Painel com os botões e a tabela.
	 */
	private HorizontalPanel panel;
	
	/**
	 * Tabela de jogos.
	 */
	private CellTable<Round> cellTable;

	/**
	 * Botões de navegação entre as rodadas do campeonato.
	 */
	private Button prevRounds;
	private Button nextRounds;
	
	/**
	 * Lista com todas as rodadas do campeonato.
	 */
	private List<Round> allRounds;
	
	/** 
	 * Rodada corrente ques está sendo exibida.
	 */
	private Round currentRound;

	private ArrayList<Round> data;

	public GamesTable() {

	    constants = GWT.create(GamesTableConsts.class);
	    
		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		currentRound = null;
		allRounds = new ArrayList<Round>();
		
		cellTable = new CellTable<Round>(Round.KEY_PROVIDER);
		cellTable.setStyleName( resources.styles().gameTable() );
		cellTable.setSkipRowHoverStyleUpdate( true );

		cellTable.setHeaderBuilder(new CustomHeaderBuilder(cellTable));
		cellTable.setTableBuilder(new CustomTableBuilder(cellTable));
		
		prevRounds = new Button( "<<", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int nextRound = findRound( false );
				if ( nextRound > 0 )  { 
					updateRounds( nextRound );
				}
			}
		});
		prevRounds.setStyleName(resources.styles().customPrevBtn());
		
		nextRounds = new Button( ">>", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int nextRound = findRound( true );
				if ( nextRound > 0 )  { 
					updateRounds( nextRound );
				}
			}
		});
		nextRounds.setStyleName(resources.styles().customNextBtn());
		
		panel = new HorizontalPanel();
		panel.add(prevRounds);
		panel.add(cellTable);
		panel.add(nextRounds);
	}

	/**
	 * 
	 * @return Widget com a tabela e os botões.
	 */
   public HorizontalPanel asWidget()
   {
	   return panel;
   }

   /**
    * 
    * @return Rodada corrente que está sendo exibida.
    */
   public Round getCurrentRound()
   {
	   return currentRound;
   }
   
   public List<Game> getDisplayedGames()
   {
	   List<Game> games = new ArrayList<Game>();
	   for ( Round r : data )
	   {
		   for ( int i = 0; i < r.getGames().length(); i++ )
		   {
			   games.add( r.getGames().get( i ) );
		   }
	   }
	   return games;
   }
   
   /**
    * Atualiza as rodadas da tabela.
    * 
    * @param rounds Lista de rodadas.
    * @param currentRound Rodada que deve ser exibida por default.
    */
	public void setRounds(JsArray<Round> rounds, int currentRound ) {
		allRounds.clear();
		for (int i = 0; i < rounds.length(); i++) {
			allRounds.add( rounds.get( i ) );
		}
		
		updateRounds( currentRound );
	}
	
	private void updateRounds( int nextRound ) {
		data = new ArrayList<Round>();
		for ( Round r : allRounds) {
			if ( r.getNumber() == nextRound ) {
				currentRound = r;
				break;
			}
		}
		
		for ( Round r : allRounds) {
			if ( currentRound.getBaseDate().equals( r.getBaseDate() ) && r.getGames().length() > 0 )	{
				data.add( r );
			}
		}
		
		cellTable.setRowData(data);
		
		updateButtons();
	}
	
	private int findRound( boolean isNext ) {
		
		int expected = isNext ? 1 : -1;
		
		int nextRound = -1;
		for ( Round r : allRounds) {
			if ( r.getBaseDate().compareTo( currentRound.getBaseDate() ) == expected ) {
				if ( isNext ) {
					if ( nextRound == -1 || r.getNumber() < nextRound ) {
						nextRound = r.getNumber();
					}
				}
				else {
					if ( nextRound == -1 || r.getNumber() > nextRound ) {
						nextRound = r.getNumber();
					}	
				}
			}
		}
		
		return nextRound;
	}
	
	private void updateButtons()
	{
		boolean hasNext = false;
		boolean hasPrev = false;
		
		for ( Round r : allRounds) {
			if ( r.getBaseDate().compareTo( currentRound.getBaseDate() ) == 1  ) {
				hasNext = true;
			}
			if ( r.getBaseDate().compareTo( currentRound.getBaseDate() ) == -1  ) {
				hasPrev = true;
			}
		}
		
		nextRounds.setEnabled( hasNext );
		prevRounds.setEnabled( hasPrev );
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

			buildHeader(tr, true, false);
			buildHeader(tr, false, false);
			buildHeader(tr, false, false);
			buildHeader(tr, false, false);
			buildHeader(tr, false, true);

			tr.endTR();

			return true;
		}

		private void buildHeader(TableRowBuilder out, boolean isFirst, boolean isLast) {

			TableCellBuilder th = out.startTH().className(headerStyle);
			if (isFirst) {
				th.style().width(120, Unit.PX).textAlign(TextAlign.RIGHT).endStyle();
			}
			if (isLast) {
				th.style().width(120, Unit.PX).textAlign(TextAlign.LEFT).endStyle();
			}

			Header<String> header = new TextHeader( "" );

			// renderiza o header
			Context context = new Context(0, 2, header.getKey());
			renderHeader(th, context, header);

			th.endTH();
		}
	}

	/**
	 * Builder customizado para a renderização de cada linha da tabela de jogos.
	 *
	 */
	private class CustomTableBuilder extends AbstractCellTableBuilder<Round> {

		private final String cellStyles;
		
		private final String customGameTableRow;
		
		private final String customRoundSubHeader;

		public CustomTableBuilder(CellTable<Round> dataGrid) {
			super(dataGrid);

			cellStyles = resources.styles().customColumn();
			customRoundSubHeader = resources.styles().customRoundSubHeader();
			customGameTableRow = resources.styles().customGameTableRow();
		}

		@Override
		public void buildRowImpl(Round rowValue, int absRowIndex) {

     		TableRowBuilder row = startRow();
			
     		// adiciona a linha da rodada
			TableCellBuilder td = row.startTD().colSpan(5).className(customRoundSubHeader);
	        td.text( resolveRound( rowValue )).endTD();
	        row.endTR();
		      
			JsArray<Game> games = rowValue.getGames();
			for (int i = 0; i < games.length(); i++) {
				
				// adiciona linha com a mesa do jogo
				row = startRow();
				
				td = row.startTD().colSpan(5).className(customGameTableRow);
				td.text( constants.tableLabel() + " " + String.valueOf( games.get(i).getGameTable() ) );
				td.endTD();
				row.endTR();
				
				// adiciona linha do jogo
				row = startRow();
				
				// player 1
				buildRow( row, games.get(i).getPlayer1Name(), true, false );
	
				// score 1
				String score1 = String.valueOf(games.get(i).getScore1()) != "null" ? String.valueOf(games.get(i).getScore1()) : "";
				buildRow( row, score1, false, false );
	
				// versus
				buildRow( row, constants.versusSymbol(), false, false );
	
				// score 2
				String score2 = String.valueOf(games.get(i).getScore2()) != "null" ? String.valueOf(games.get(i).getScore2()) : "";
				buildRow( row, score2, false, false );
	
				// player 2
				buildRow( row, games.get(i).getPlayer2Name(), false, true );

				row.endTR();
			}
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
		
		private String resolveRound( Round round )
		{
			StringBuilder roundStr = new StringBuilder( constants.roundLabel() );
			if ( round != null ) {
				roundStr.append( " " + String.valueOf( round.getNumber() ) );
				
				Date date = DateTimeFormat.getFormat( "yyyy-dd-MM HH:mm:ss"  ).parse( round.getBaseDate() + " " + round.getBaseHour() );
				String dateString = DateTimeFormat.getFormat( "dd-MM-yy hh:mm" ).format( date );
				roundStr.append( " - " + dateString );
			}
			return roundStr.toString();
		}
	}
}