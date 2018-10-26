package com.futmesa.client.module.main.viewport.player.maintab;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Player;
import com.futmesa.client.businessinteligence.tablestructures.SimpleMapInfo;
import com.futmesa.client.module.main.viewport.player.PlayerViewport;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class PlayerMainTab implements ViewportInterface {

	interface Resources extends ClientBundle {

		@Source("playermaintab.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String resultGameTable();

		String customGamesHeader();

		String customColumn();
	}
	
	/**
	 * Constantes da classe.
	 */
	private PlayerMainTabConsts constants;

	/**
	 * Estilos da tabela.
	 */
	private Resources resources;
	
	private VerticalPanel panel;
	
	private PlayerViewport parent;
	private Player player;
	
	private CellTable<SimpleMapInfo> gameTable;
	
	/**
	 * Construtor padrão.
	 */
	public PlayerMainTab( PlayerViewport parent ) {
		
		constants = GWT.create(PlayerMainTabConsts.class);

		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		constants = GWT.create(PlayerMainTabConsts.class);
		
		this.parent = parent;
		player = null;
		
		panel = new VerticalPanel();
		gameTable = new CellTable<SimpleMapInfo>();
		createGrid();
		panel.add(gameTable);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public void udateGrid( JsArray<SimpleMapInfo> infos )
	{
		List<SimpleMapInfo> data = new ArrayList<SimpleMapInfo>();
		for (int i = 0; i < infos.length(); i++) {
			data.add(infos.get(i));
		}
		gameTable.setRowData(data);
	}
	
	private void createGrid() {
		gameTable.setTableBuilder(new CustomTableBuilder(gameTable));
		gameTable.setSkipRowHoverStyleUpdate(true);

		TextHeader header = new TextHeader("");
		header.setHeaderStyleNames(resources.styles().customGamesHeader());

		// player 1
		Column<SimpleMapInfo, String> player1Column = new Column<SimpleMapInfo, String>(new TextCell()) {
			@Override
			public String getValue(SimpleMapInfo object) {
				return object.getKey();
			}
		};
		gameTable.addColumn(player1Column, header);
		gameTable.setColumnWidth(player1Column, 120, Unit.PX);

		// player 2
		Column<SimpleMapInfo, String> player2Column = new Column<SimpleMapInfo, String>(new TextCell()) {
			@Override
			public String getValue(SimpleMapInfo object) {
				return object.getValue();
			}
		};
		gameTable.addColumn(player2Column, header);
		gameTable.setColumnWidth(player2Column, 120, Unit.PX);
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

			// adiciona linha do jogo
			row = startRow();

			// player 1
			buildRow(row, rowValue.getKey(), true );

			// player 2
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