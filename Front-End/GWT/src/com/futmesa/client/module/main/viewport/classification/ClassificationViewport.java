package com.futmesa.client.module.main.viewport.classification;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Player;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

/**
 * Viewport com exemplos de utilização do SimpleGrid.
 */
public class ClassificationViewport implements ViewportInterface {

	private static final ClassificationViewportUiBinder uiBinder = GWT.create(ClassificationViewportUiBinder.class);

	interface ClassificationViewportUiBinder extends UiBinder<Widget, ClassificationViewport> {
	}

	@UiField(provided = false)
	protected HTMLPanel panel;
	
	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	protected CellTable<Player> cellTable;

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	protected SimplePager pager;

	/**
	 * Construtor padrão.
	 */
	public ClassificationViewport() {

		this.createExample1();
	}

	/**
	 * Criação de exemplo 1 de tabela. Possui renderer customizado, filtros e
	 * listener de duplo clique.
	 */
	public void createExample1() {
		// Create a CellTable.

		// Set a key provider that provides a unique key for each contact. If key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellTable = new CellTable<Player>(Player.KEY_PROVIDER);
		cellTable.setWidth("100%", true);

		// Do not refresh the headers and footers every time the data is updated.
		cellTable.setAutoHeaderRefreshDisabled(true);
		cellTable.setAutoFooterRefreshDisabled(true);
		
		// Create a Pager to control the table.
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(cellTable);

		// Add a selection model so we can select cells.
		final SelectionModel<Player> selectionModel = new MultiSelectionModel<Player>(Player.KEY_PROVIDER);
		cellTable.setSelectionModel(selectionModel, DefaultSelectionEventManager.<Player>createCheckboxManager());

		// Initialize the columns.
		initTableColumns(selectionModel);

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns(final SelectionModel<Player> selectionModel) {

		// First name.
		Column<Player, String> firstNameColumn = new Column<Player, String>(new TextCell()) {
			@Override
			public String getValue(Player object) {
				return String.valueOf(object.getId());
			}
		};
		firstNameColumn.setSortable(true);

		cellTable.addColumn(firstNameColumn, "Id");
		cellTable.setColumnWidth(firstNameColumn, 20, Unit.PCT);

		// Last name.
		Column<Player, String> lastNameColumn = new Column<Player, String>(new TextCell()) {
			@Override
			public String getValue(Player object) {
				return object.getName();
			}
		};
		lastNameColumn.setSortable(true);
		cellTable.addColumn(lastNameColumn, "Nome");
		cellTable.setColumnWidth(lastNameColumn, 20, Unit.PCT);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public void addRows(JsArray<Player> players) {
		List<Player> data = new ArrayList<Player>();
		for ( int i=0; i<players.length();i++ )
		{
			data.add( players.get(i));
		}
		cellTable.setRowData(data);
	}
}
