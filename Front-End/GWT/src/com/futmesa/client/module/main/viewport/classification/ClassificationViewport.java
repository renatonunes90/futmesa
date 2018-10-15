package com.futmesa.client.module.main.viewport.classification;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Classification;
import com.futmesa.client.businessinteligence.Player;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.OutlineStyle;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractCellTable.Style;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTableBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

/**
 * Viewport com exemplos de utilização do SimpleGrid.
 */
public class ClassificationViewport implements ViewportInterface {

	private static final ClassificationViewportUiBinder uiBinder = GWT.create(ClassificationViewportUiBinder.class);

	interface ClassificationViewportUiBinder extends UiBinder<SimplePanel, ClassificationViewport> {
	}
	
	 /**
	   * Renders the data rows that display each contact in the table.
	   */
	  private class CustomTableBuilder extends AbstractCellTableBuilder<Classification> {

	    private final String rowStyle;
	    private final String cellStyle;

	    @SuppressWarnings("deprecation")
	    public CustomTableBuilder( CellTable<Classification> dataGrid) {
	      super(dataGrid);

	      // Cache styles for faster access.
	      Style style = dataGrid.getResources().style();
	      rowStyle = style.evenRow();
	      cellStyle = style.cell() + " " + style.evenRowCell();
	    }

	    @SuppressWarnings("deprecation")
	    @Override
	    public void buildRowImpl(Classification rowValue, int absRowIndex) {
	      
	      StringBuilder trClasses = new StringBuilder(rowStyle);
	      //trClasses.append( ".customCellRow" );

	      // Calculate the cell styles.
	      String cellStyles = cellStyle;
	      
	      StringBuilder evenCellStyles = new StringBuilder(cellStyle);
	      //evenCellStyles.append( ".customEvenColumn" );

	      TableRowBuilder row = startRow();
	      row.className(trClasses.toString() );
	      row.style().borderStyle( BorderStyle.SOLID ).borderWidth( 0.2, Unit.PX ).endStyle();

	      // Player
	      TableCellBuilder td = row.startTD();
	      td.className(cellStyles);
	      td.style().outlineStyle(OutlineStyle.NONE).endStyle();
          td.text( String.valueOf( absRowIndex + 1 ) + " " + rowValue.getPlayerName());
	      td.endTD();

	      // Points
	      td = row.startTD();
	      td.className(evenCellStyles.toString());
	      td.style().outlineStyle(OutlineStyle.NONE).fontWeight( FontWeight.BOLD ).trustedBackgroundColor( "#e0e0e0" ).endStyle();
	      td.text( String.valueOf( rowValue.getPoints() ) );
	      td.endTD();

	      // Games
	      td = row.startTD();
	      td.className( cellStyles);
	      td.style().outlineStyle(OutlineStyle.NONE).endStyle();
          td.text( String.valueOf( rowValue.getNumberOfGames() ));
	      td.endTD();
			
	      // Wins
	      td = row.startTD();
	      td.className( evenCellStyles.toString());
	      td.style().outlineStyle(OutlineStyle.NONE).trustedBackgroundColor( "#e0e0e0" ).endStyle();
          td.text( String.valueOf( rowValue.getWins() ));
	      td.endTD();
			
		 // Ties
	      td = row.startTD();
	      td.className( cellStyles);
	      td.style().outlineStyle(OutlineStyle.NONE).endStyle();
          td.text( String.valueOf( rowValue.getTies() ));
	      td.endTD();

	      // Losses
	      td = row.startTD();
	      td.className( evenCellStyles.toString());
	      td.style().outlineStyle(OutlineStyle.NONE).trustedBackgroundColor( "#e0e0e0" ).endStyle();
          td.text( String.valueOf( rowValue.getLosses() ));
	      td.endTD();
			
	      // Goals pro
	      td = row.startTD();
	      td.className( cellStyles);
	      td.style().outlineStyle(OutlineStyle.NONE).endStyle();
          td.text( String.valueOf( rowValue.getGoalsPro() ));
	      td.endTD();
			
	      // Goals con
	      td = row.startTD();
	      td.className( evenCellStyles.toString());
	      td.style().outlineStyle(OutlineStyle.NONE).trustedBackgroundColor( "#e0e0e0" ).endStyle();
          td.text( String.valueOf( rowValue.getGoalsCon() ));
	      td.endTD();
		
			
		  // Goals difference
	      td = row.startTD();
	      td.className( cellStyles);
	      td.style().outlineStyle(OutlineStyle.NONE).endStyle();
          td.text( String.valueOf( rowValue.getGoalsDiff() ));
	      td.endTD();
			
		  // Win rate
	      td = row.startTD();
	      td.className( evenCellStyles.toString());
	      td.style().outlineStyle(OutlineStyle.NONE).trustedBackgroundColor( "#e0e0e0" ).endStyle();
	      td.text( String.valueOf( rowValue.getWinRate()));
	      td.endTD();
			
	      row.endTR();
	    }
	  }

	@UiField(provided = false)
	protected SimplePanel panel;
	
	/**
	 * The main CellTable.
	 */
	@UiField(provided = true)
	protected CellTable<Classification> cellTable;

	
	/**
	 * Construtor padrão.
	 */
	public ClassificationViewport() {

		// Create a CellTable.

		// Set a key provider that provides a unique key for each contact. If key is
		// used to identify contacts when fields (such as the name and address)
		// change.
		cellTable = new CellTable<Classification>(Classification.KEY_PROVIDER);
//		cellTable.setWidth("100%", true);
//		cellTable.setTitle("Tabela" );

		// Do not refresh the headers and footers every time the data is updated.
		cellTable.setAutoHeaderRefreshDisabled(true);
		cellTable.setAutoFooterRefreshDisabled(true);

		// Specify a custom table.
		cellTable.setTableBuilder(new CustomTableBuilder( cellTable ));

		// Initialize the columns.
		initTableColumns();//selectionModel);

		// Create the UiBinder.
		uiBinder.createAndBindUi(this);
	}

	/**
	 * Add the columns to the table.
	 */
	private void initTableColumns() {//final SelectionModel<Player> selectionModel) {

		// Player name
		Column<Classification, String> firstNameColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return object.getPlayerName();
			}
		};

		cellTable.addColumn(firstNameColumn, "Classificação");
		cellTable.setColumnWidth(firstNameColumn, 60, Unit.PCT);

		// Points
		Column<Classification, String> pointsColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getPoints() );
			}
		};
		cellTable.addColumn(pointsColumn, "P");
		cellTable.setColumnWidth(pointsColumn, 60, Unit.PCT);
		
		
		// Games
		Column<Classification, String> gamesColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getNumberOfGames() );
			}
		};
	
		cellTable.addColumn(gamesColumn, "J");
		cellTable.setColumnWidth(gamesColumn, 60, Unit.PCT);
		
		// Wins
		Column<Classification, String> winsColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getWins() );
			}
		};
	
		cellTable.addColumn(winsColumn, "V");
		cellTable.setColumnWidth(winsColumn, 60, Unit.PCT);
		
		// Ties
		Column<Classification, String> tiesColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getTies() );
			}
		};
	
		cellTable.addColumn(tiesColumn, "E");
		cellTable.setColumnWidth(tiesColumn, 60, Unit.PCT);
		
		// Losses
		Column<Classification, String> lossesColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getLosses() );
			}
		};
	
		cellTable.addColumn(lossesColumn, "D");
		cellTable.setColumnWidth(lossesColumn, 60, Unit.PCT);
		
		// Goals pro
		Column<Classification, String> goalsProColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getGoalsPro() );
			}
		};
	
		cellTable.addColumn(goalsProColumn, "GP");
		cellTable.setColumnWidth(goalsProColumn, 60, Unit.PCT);
		
		// Goals con
		Column<Classification, String> goalsConColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getGoalsCon() );
			}
		};
	
		cellTable.addColumn(goalsConColumn, "GC");
		cellTable.setColumnWidth(goalsConColumn, 60, Unit.PCT);
		
		// Goals difference
		Column<Classification, String> goalsDifferenceColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getGoalsDiff() );
			}
		};
	
		cellTable.addColumn(goalsDifferenceColumn, "SD");
		cellTable.setColumnWidth(goalsDifferenceColumn, 60, Unit.PCT);
		
		// Win rate
		Column<Classification, String> winRateColumn = new Column<Classification, String>(new TextCell()) {
			@Override
			public String getValue(Classification object) {
				return String.valueOf( object.getWinRate() );
			}
		};
	
		cellTable.addColumn(winRateColumn, "%");
		cellTable.setColumnWidth(winRateColumn, 60, Unit.PCT);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	public void updateClassification(JsArray<Classification> classifications) {
		List<Classification> data = new ArrayList<Classification>();
		for ( int i=0; i<classifications.length();i++ )
		{
			data.add( classifications.get(i));
		}
		cellTable.setRowData(data);
	}
}



///**
// * The resources used by this example.
// */
//interface Resources extends ClientBundle {
//
//  /**
//   * Get the styles used but this example.
//   */
//  @Source("CwCustomDataGrid.css")
//  Styles styles();
//}
//
///**
// * The CSS Resources used by this example.
// */
//interface Styles extends CssResource {
//  /**
//   * Indents cells in child rows.
//   */
//  String childCell();
//
//  /**
//   * Applies to group headers.
//   */
//  String groupHeaderCell();
//}
//
//
//
///**
// * The main DataGrid.
// */
//@UiField(provided = true)
//DataGrid<ContactInfo> dataGrid;
//
///**
// * The pager used to change the range of data.
// */
//@UiField(provided = true)
//SimplePager pager;
//
///**
// * An instance of the constants.
// */
//private final CwConstants constants;
//
///**
// * The resources used by this example.
// */
//private Resources resources;
//
///**
// * Contains the contact id for each row in the table where the friends list is
// * currently expanded.
// */
//private final Set<Integer> showingFriends = new HashSet<Integer>();
//
///**
// * Column to control selection.
// */
//private Column<ContactInfo, Boolean> checkboxColumn;
//
///**
// * Column to expand friends list.
// */
//private Column<ContactInfo, String> viewFriendsColumn;
//
///**
// * Column displays first name.
// */
//private Column<ContactInfo, String> firstNameColumn;
//
///**
// * Column displays last name.
// */
//private Column<ContactInfo, String> lastNameColumn;
//
///**
// * Column displays age.
// */
//private Column<ContactInfo, Number> ageColumn;
//
///**
// * Column displays category.
// */
//private Column<ContactInfo, String> categoryColumn;
//
///**
// * Column displays address.
// */
//private Column<ContactInfo, String> addressColumn;
//
///**
// * Initialize this example.
// */
//@Override
//public Widget onInitialize() {
//  resources = GWT.create(Resources.class);
//  resources.styles().ensureInjected();
//
//  // Create a DataGrid.
//
//  /*
//   * Set a key provider that provides a unique key for each contact. If key is
//   * used to identify contacts when fields (such as the name and address)
//   * change.
//   */
//  dataGrid = new DataGrid<ContactInfo>(ContactDatabase.ContactInfo.KEY_PROVIDER);
//  dataGrid.setWidth("100%");
//
//  /*
//   * Do not refresh the headers every time the data is updated. The footer
//   * depends on the current data, so we do not disable auto refresh on the
//   * footer.
//   */
//  dataGrid.setAutoHeaderRefreshDisabled(true);
//
//  // Set the message to display when the table is empty.
//  dataGrid.setEmptyTableWidget(new Label(constants.cwCustomDataGridEmpty()));
//
//
//
//  // Initialize the columns.
//  initializeColumns(sortHandler);
//
//  // Specify a custom table.
//  dataGrid.setTableBuilder(new CustomTableBuilder())
//
//
//  // Create the UiBinder.
//  Binder uiBinder = GWT.create(Binder.class);
//  return uiBinder.createAndBindUi(this);
//}
//
///**
// * Defines the columns in the custom table. Maps the data in the ContactInfo
// * for each row into the appropriate column in the table, and defines handlers
// * for each column.
// */
//private void initializeColumns() {
//
//
//  // First name.
//  firstNameColumn = new Column<ContactInfo, String>(new EditTextCell()) {
//    @Override
//    public String getValue(ContactInfo object) {
//      return object.getFirstName();
//    }
//  };
//  firstNameColumn.setSortable(true);
//  sortHandler.setComparator(firstNameColumn, new Comparator<ContactInfo>() {
//    @Override
//    public int compare(ContactInfo o1, ContactInfo o2) {
//      return o1.getFirstName().compareTo(o2.getFirstName());
//    }
//  });
//  firstNameColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
//    @Override
//    public void update(int index, ContactInfo object, String value) {
//      // Called when the user changes the value.
//      object.setFirstName(value);
//      ContactDatabase.get().refreshDisplays();
//    }
//  });
//  dataGrid.setColumnWidth(2, 20, Unit.PCT);
//
//  // Last name.
//  lastNameColumn = new Column<ContactInfo, String>(new EditTextCell()) {
//    @Override
//    public String getValue(ContactInfo object) {
//      return object.getLastName();
//    }
//  };
//  lastNameColumn.setSortable(true);
//  sortHandler.setComparator(lastNameColumn, new Comparator<ContactInfo>() {
//    @Override
//    public int compare(ContactInfo o1, ContactInfo o2) {
//      return o1.getLastName().compareTo(o2.getLastName());
//    }
//  });
//  lastNameColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
//    @Override
//    public void update(int index, ContactInfo object, String value) {
//      // Called when the user changes the value.
//      object.setLastName(value);
//      ContactDatabase.get().refreshDisplays();
//    }
//  });
//  dataGrid.setColumnWidth(3, 20, Unit.PCT);
//
//  // Age.
//  ageColumn = new Column<ContactInfo, Number>(new NumberCell()) {
//    @Override
//    public Number getValue(ContactInfo object) {
//      return object.getAge();
//    }
//  };
//  ageColumn.setSortable(true);
//  sortHandler.setComparator(ageColumn, new Comparator<ContactInfo>() {
//    @Override
//    public int compare(ContactInfo o1, ContactInfo o2) {
//      return o1.getAge() - o2.getAge();
//    }
//  });
//  dataGrid.setColumnWidth(4, 7, Unit.EM);
//
//  // Category.
//  final Category[] categories = ContactDatabase.get().queryCategories();
//  List<String> categoryNames = new ArrayList<String>();
//  for (Category category : categories) {
//    categoryNames.add(category.getDisplayName());
//  }
//  SelectionCell categoryCell = new SelectionCell(categoryNames);
//  categoryColumn = new Column<ContactInfo, String>(categoryCell) {
//    @Override
//    public String getValue(ContactInfo object) {
//      return object.getCategory().getDisplayName();
//    }
//  };
//  categoryColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
//    @Override
//    public void update(int index, ContactInfo object, String value) {
//      for (Category category : categories) {
//        if (category.getDisplayName().equals(value)) {
//          object.setCategory(category);
//        }
//      }
//      ContactDatabase.get().refreshDisplays();
//    }
//  });
//  dataGrid.setColumnWidth(5, 130, Unit.PX);
//
//  // Address.
//  addressColumn = new Column<ContactInfo, String>(new TextCell()) {
//    @Override
//    public String getValue(ContactInfo object) {
//      return object.getAddress();
//    }
//  };
//  addressColumn.setSortable(true);
//  sortHandler.setComparator(addressColumn, new Comparator<ContactInfo>() {
//    @Override
//    public int compare(ContactInfo o1, ContactInfo o2) {
//      return o1.getAddress().compareTo(o2.getAddress());
//    }
//  });
//  dataGrid.setColumnWidth(6, 60, Unit.PCT);
//}
