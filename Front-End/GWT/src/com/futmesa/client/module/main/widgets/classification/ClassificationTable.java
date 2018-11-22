package com.futmesa.client.module.main.widgets.classification;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.base.Modules;
import com.futmesa.client.base.URLFilter;
import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.module.main.MainModulePanel;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.builder.shared.SpanBuilder;
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
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.Window;

/**
 * Classe com a tabela de classificação de um campeonato.
 */
public class ClassificationTable {


	/**
	 * Carrega os recursos de estilos da tabela.
	 */
	interface Resources extends ClientBundle {

		@Source("classificationtable.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String customClassificationHeader();

		String customColumn();
		
	   String customSpanPlayerColumn();

		String customEvenColumn();

		String cellTable();
		
		String customIcon();
		
		String customWinIcon();
		
		String customNeutralIcon();
		
		String customLossIcon();
	}

	/**
	 * Constantes da classe.
	 */
	private ClassificationTableConsts constants;

	/**
	 * Estilos da tabela.
	 */
	private Resources resources;

	private CellTable<Classification> cellTable;

	private Column<Classification, String> playerColumn;

	public ClassificationTable() {

	   constants = GWT.create(ClassificationTableConsts.class);
		
		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		cellTable = new CellTable<Classification>(Classification.KEY_PROVIDER);
		cellTable.setStyleName( resources.styles().cellTable() );
		cellTable.setSkipRowHoverStyleUpdate( true );

		playerColumn = new Column<Classification, String>(new ClickableTextCell()) {
		      @Override
		      public String getValue(Classification object) {
		         return String.valueOf(object.getPosition()) + " " + object.getPlayerName();
		      }
		    };
		    
		playerColumn.setFieldUpdater(new FieldUpdater<Classification, String>() {
		      @Override
		      public void update(int index, Classification object, String value) {
		         URLFilter filter = new URLFilter( Modules.MAIN_MODULE, MainModulePanel.PLAYER_PANEL );
		         filter.addFilter( "id", String.valueOf( object.getPlayerId() ) );
		         Window.Location.assign( filter.toURLString()  );
		      }
		    });
			
		// seta os builders customizados
		cellTable.setHeaderBuilder(new CustomHeaderBuilder(cellTable));
		cellTable.setTableBuilder(new CustomTableBuilder(cellTable));
	}

	/**
	 * 
	 * @return o Widget da tabela.
	 */
   public CellTable<Classification> asWidget()
   {
	   return cellTable;
   }

   /**
    * Atualiza a tabela de classificação.
    * 
    * @param classifications
    */
	public void updateClassification(JsArray<Classification> classifications) {
		List<Classification> data = new ArrayList<Classification>();
		for (int i = 0; i < classifications.length(); i++) {
			data.add(classifications.get(i));
		}
		cellTable.setRowData(data);
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

			buildHeader(tr, constants.classificationColumn(), true, false);
			buildHeader(tr, constants.pointsColumn(), false, false);
			buildHeader(tr, constants.gamesColumn(), false, false);
			buildHeader(tr, constants.winsColumn(), false, false);
			buildHeader(tr, constants.tiesColumn(), false, false);
			buildHeader(tr, constants.lossesColumn(), false, false);
			buildHeader(tr, constants.goalsProColumn(), false, false);
			buildHeader(tr, constants.goalsConColumn(), false, false);
			buildHeader(tr, constants.goalsDifferenceColumn(), false, false);
			buildHeader(tr, constants.winRatioColumn(), false, false);
			buildHeader(tr, constants.lastGamesColumn(), false, true);

			tr.endTR();

			return true;
		}

		private void buildHeader(TableRowBuilder out, String headerStr, boolean isFirst, boolean isLast) {

			// inicia a célula
			TableCellBuilder th = out.startTH().className(headerStyle);
			if (isFirst) {
				th.style().width(240, Unit.PX).textAlign(TextAlign.LEFT).endStyle();
			} else if ( isLast ) {
				th.style().width(80, Unit.PX).endStyle();
			}

			Header<String> header = new TextHeader(headerStr);

			// renderiza o header
			Context context = new Context(0, 2, header.getKey());
			renderHeader(th, context, header);

			// finaliza a célula
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
		private final String customSpanStyle;
		private final String winStyle;
		private final String neutralStyle;
		private final String lossStyle;
		private NumberFormat numberFormatter;

		public CustomTableBuilder(CellTable<Classification> dataGrid) {
			super(dataGrid);

			cellStyles = resources.styles().customColumn();

			evenCellStyles = new StringBuilder(resources.styles().customEvenColumn());
			evenCellStyles.append(" " + resources.styles().customColumn());

			customSpanStyle = resources.styles().customSpanPlayerColumn();
			
			winStyle = resources.styles().customIcon() + " " + resources.styles().customWinIcon(); 
			neutralStyle = resources.styles().customIcon() + " " + resources.styles().customNeutralIcon(); 
			lossStyle = resources.styles().customIcon() + " " + resources.styles().customLossIcon(); 
			
			numberFormatter = NumberFormat.getFormat("###.##");
		}

		@Override
		public void buildRowImpl(Classification rowValue, int absRowIndex) {

			TableRowBuilder row = startRow();
			
			buildPlayerCell( row, rowValue );
			buildRow( row, String.valueOf(rowValue.getPoints()), true, true );
			buildRow( row, String.valueOf(rowValue.getNumberOfGames()), false,  false );
			buildRow( row, String.valueOf(rowValue.getWins()), true, false );
			buildRow( row, String.valueOf(rowValue.getTies()), false, false );
			buildRow( row, String.valueOf(rowValue.getLosses()), true, false );
			buildRow( row, String.valueOf(rowValue.getGoalsPro()), false, false );
			buildRow( row, String.valueOf(rowValue.getGoalsCon()), true, false );
			buildRow( row, String.valueOf(rowValue.getGoalsDiff()), false, false );
			buildRow( row, numberFormatter.format(rowValue.getWinRate()), true, false );
			buildLastGamesCell( row, rowValue.getLast5Games() );

			row.endTR();
		}
		
		private void buildRow(TableRowBuilder row, String text, boolean isEven, boolean isBold )
		{
			TableCellBuilder td = row.startTD();
			if ( isEven ) {
				td.className(evenCellStyles.toString());
			}
			else {
				td.className(cellStyles);
			}
			
			if ( isBold ) {
				td.style().fontWeight(FontWeight.BOLD).endStyle();
			}
			td.text( text );
				
			td.endTD();
		}
		
		private void buildPlayerCell(TableRowBuilder row, Classification rowValue )
		{
			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			td.style().textAlign(TextAlign.LEFT).fontSize(17, Unit.PX).endStyle();
			SpanBuilder sp = td.startSpan();
			sp.className( customSpanStyle );
			renderCell(sp, createContext(0), playerColumn, rowValue);
			sp.endSpan();
			td.endTD();
		}
		
		private void buildLastGamesCell(TableRowBuilder row, JsArrayString values )
		{
			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			
			for ( int i =0; i< values.length();i++) {
				if ( "V".equals( values.get(i) ) ) {
					td.startSpan().className( winStyle ).endSpan();
				} else if ( "D".equals( values.get(i) ) ) {
					td.startSpan().className( lossStyle ).endSpan();
				} else {
					td.startSpan().className( neutralStyle ).endSpan();
				}
			}	
			td.endTD();
		}
	}
}