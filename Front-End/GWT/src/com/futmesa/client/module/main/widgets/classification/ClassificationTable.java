package com.futmesa.client.module.main.widgets.classification;

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

		String customEvenColumn();

		String cellTable();
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

	public ClassificationTable() {

	    constants = GWT.create(ClassificationTableConsts.class);
		
		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		cellTable = new CellTable<Classification>(Classification.KEY_PROVIDER);
		cellTable.setStyleName( resources.styles().cellTable() );
		cellTable.setSkipRowHoverStyleUpdate( true );

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

			buildHeader(tr, constants.classificationColumn(), true);
			buildHeader(tr, constants.pointsColumn(), false);
			buildHeader(tr, constants.gamesColumn(), false);
			buildHeader(tr, constants.winsColumn(), false);
			buildHeader(tr, constants.tiesColumn(), false);
			buildHeader(tr, constants.lossesColumn(), false);
			buildHeader(tr, constants.goalsProColumn(), false);
			buildHeader(tr, constants.goalsConColumn(), false);
			buildHeader(tr, constants.goalsDifferenceColumn(), false);
			buildHeader(tr, constants.winRatioColumn(), false);

			tr.endTR();

			return true;
		}

		private void buildHeader(TableRowBuilder out, String headerStr, boolean isFirst) {

			// inicia a célula
			TableCellBuilder th = out.startTH().className(headerStyle);
			if (isFirst) {
				th.style().width(240, Unit.PX).textAlign(TextAlign.LEFT).endStyle();
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
			
			buildRow( row, String.valueOf(absRowIndex + 1) + " " + rowValue.getPlayerName(), false, true, false );
			buildRow( row, String.valueOf(rowValue.getPoints()), true, false, true );
			buildRow( row, String.valueOf(rowValue.getNumberOfGames()), false, false, false );
			buildRow( row, String.valueOf(rowValue.getWins()), true, false, false );
			buildRow( row, String.valueOf(rowValue.getTies()), false, false, false );
			buildRow( row, String.valueOf(rowValue.getLosses()), true, false, false );
			buildRow( row, String.valueOf(rowValue.getGoalsPro()), false, false, false );
			buildRow( row, String.valueOf(rowValue.getGoalsCon()), true, false, false );
			buildRow( row, String.valueOf(rowValue.getGoalsDiff()), false, false, false );
			buildRow( row, numberFormatter.format(rowValue.getWinRate()), true, false, false );

			row.endTR();
		}
		
		private void buildRow(TableRowBuilder row, String text, boolean isEven, boolean isFirst, boolean isBold )
		{
			TableCellBuilder td = row.startTD();
			if ( isEven ) {
				td.className(evenCellStyles.toString());
			}
			else {
				td.className(cellStyles);
			}
			
			if ( isFirst ) {
				td.style().textAlign(TextAlign.LEFT).fontSize(17, Unit.PX).endStyle();
			}
			
			if ( isBold ) {
				td.style().fontWeight(FontWeight.BOLD).endStyle();
			}
			td.text( text );
			td.endTD();
		}
	}
}