package com.futmesa.client.module.main.widgets.simpletable;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.tablestructures.SimpleMapInfo;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementa uma tabela simples com apenas duas colunas (chave e valor).
 */
public class SimpleTable {

	interface Resources extends ClientBundle {

		@Source("simpletable.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String customSimpleTable();

		String customSimpleHeader();

		String customColumn();
	}

	/**
	 * Estilos da tabela.
	 */
	private Resources resources;
	
	private CellTable<SimpleMapInfo> table;
	
	/**
	 * Construtor padrão.
	 */
	public SimpleTable( int firstColumnSize, int secondColumnSize ) {
		
		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		table = new CellTable<SimpleMapInfo>();
		table.setTableBuilder(new CustomTableBuilder(table));
		table.setSkipRowHoverStyleUpdate(true);
		table.setStyleName( resources.styles().customSimpleTable() );
		table.setWidth( String.valueOf( firstColumnSize + secondColumnSize  ) + "px" );

		TextHeader header = new TextHeader("");
		header.setHeaderStyleNames(resources.styles().customSimpleHeader());

		// key
		Column<SimpleMapInfo, String> keyColumn = new Column<SimpleMapInfo, String>(new TextCell()) {
			@Override
			public String getValue(SimpleMapInfo object) {
				return object.getKey();
			}
		};
		table.addColumn(keyColumn, header);
		table.setColumnWidth(keyColumn, firstColumnSize, Unit.PX);

		// value
		Column<SimpleMapInfo, String> valueColumn = new Column<SimpleMapInfo, String>(new TextCell()) {
			@Override
			public String getValue(SimpleMapInfo object) {
				return object.getValue();
			}
		};
		table.addColumn(valueColumn, header);
		table.setColumnWidth(valueColumn, secondColumnSize, Unit.PX);
	}

	public Widget asWidget() {
		return table;
	}

	public void updateTableInfo( JsArray<SimpleMapInfo> infos )
	{
		List<SimpleMapInfo> data = new ArrayList<SimpleMapInfo>();
		for (int i = 0; i < infos.length(); i++) {
			data.add(infos.get(i));
		}
		table.setRowData(data);
	}

	/**
	 * Builder customizado para a renderização de cada linha da tabela de jogos.
	 */
	private class CustomTableBuilder extends AbstractCellTableBuilder<SimpleMapInfo> {

		private final String cellStyles;

		public CustomTableBuilder(CellTable<SimpleMapInfo> dataGrid) {
			super(dataGrid);

			cellStyles = resources.styles().customColumn();
		}

		@Override
		public void buildRowImpl(SimpleMapInfo rowValue, int absRowIndex) {

			TableRowBuilder row = startRow();

			row = startRow();

			// key
			buildRow(row, rowValue.getKey(), true );

			// value
			buildRow(row, rowValue.getValue(), false );

			row.endTR();
		}

		private void buildRow(TableRowBuilder row, String value, boolean isFirst) {

			TableCellBuilder td = row.startTD();
			td.className(cellStyles);
			if (isFirst) {
				td.style().textAlign(TextAlign.LEFT).endStyle();
			} 
			td.text(value);
			td.endTD();
		}

	}

}