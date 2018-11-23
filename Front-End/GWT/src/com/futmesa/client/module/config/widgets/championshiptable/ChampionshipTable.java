package com.futmesa.client.module.config.widgets.championshiptable;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.base.event.EventBus;
import com.futmesa.client.base.event.EventProperty;
import com.futmesa.client.businessinteligence.Championship;
import com.futmesa.client.module.config.controller.championship.ChampionshipConfigController;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.builder.shared.DivBuilder;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Widget;

/**
 * Implementa a tabela de campeonatos do sistema da tela de gerenciamento.
 */
public class ChampionshipTable
{

   interface Resources
      extends ClientBundle
   {

      @Source ( "championshiptable.css" )
      Styles styles();
   }

   interface Styles
      extends CssResource
   {

      String customTable();

      String customHeader();

      String customColumn();

      String customEvenRow();

      String customBtn();

      String customEditBtn();

      String customRemoveBtn();
   }

   /**
    * Estilos da tabela.
    */
   private Resources resources;

   /**
    * Constantes da classe.
    */
   private ChampionshipTableConsts constants;

   private DataGrid< Championship > table;

   private Column< Championship, String > editColumn;

   private Column< Championship, String > removeColumn;

   /**
    * Construtor padrão.
    */
   public ChampionshipTable()
   {
      resources = GWT.create( Resources.class );
      resources.styles().ensureInjected();

      constants = GWT.create( ChampionshipTableConsts.class );

      table = new DataGrid< Championship >();
      table.setTableBuilder( new CustomTableBuilder( table ) );
      table.setSkipRowHoverStyleUpdate( true );
      table.setStyleName( resources.styles().customTable() );

      createColumn( constants.nameLabel(), 200 );
      createColumn( constants.seasonLabel(), 120 );
      createColumn( constants.typeLabel(), 160 );
      createColumn( constants.baseDateLabel(), 120 );
      createColumn( constants.gamesbByRoundLabel(), 100 );
      createColumn( constants.roundsByDayLabel(), 100 );
      createColumn( constants.dateIncrLabel(), 110 );
      createColumn( constants.finishedLabel(), 110 );

      // alterar
      TextHeader header = new TextHeader( "" );
      header.setHeaderStyleNames( resources.styles().customHeader() );
      editColumn = new Column< Championship, String >( new ClickableTextCell() )
      {
         @Override
         public String getValue( Championship object )
         {
            return " ";
         }
      };
      editColumn.setFieldUpdater( new FieldUpdater< Championship, String >()
      {
         @Override
         public void update( int index, Championship object, String value )
         {
            ChampionshipConfigController.UPDATE_CHAMPIONSHIP.setProperty( EventProperty.CHAMPIONSHIP, object );
            EventBus.getInstance().fireEvent( ChampionshipConfigController.UPDATE_CHAMPIONSHIP );
         }
      } );
      table.addColumn( editColumn, header );
      table.setColumnWidth( editColumn, 40, Unit.PX );

      // exluir
      header = new TextHeader( "" );
      header.setHeaderStyleNames( resources.styles().customHeader() );
      removeColumn = new Column< Championship, String >( new ClickableTextCell() )
      {
         @Override
         public String getValue( Championship object )
         {
            return " ";
         }
      };
      removeColumn.setFieldUpdater( new FieldUpdater< Championship, String >()
      {
         @Override
         public void update( int index, Championship object, String value )
         {
            ChampionshipConfigController.REMOVE_CHAMPIONSHIP.setProperty( EventProperty.CHAMPIONSHIP, object );
            EventBus.getInstance().fireEvent( ChampionshipConfigController.REMOVE_CHAMPIONSHIP );
         }
      } );
      table.addColumn( removeColumn, header );
      table.setColumnWidth( removeColumn, 40, Unit.PX );
      
      // coluna para o scroll
      createColumn( "", 20 );
   }

   public Widget asWidget()
   {
      return table;
   }

   public void updateTableInfo( JsArray< Championship > infos )
   {
      List< Championship > data = new ArrayList< Championship >();
      for ( int i = 0; i < infos.length(); i++ )
      {
         data.add( infos.get( i ) );
      }
      table.setRowData( data );
   }

   private void createColumn( String label, double size )
   {
      TextHeader header = new TextHeader( label );
      header.setHeaderStyleNames( resources.styles().customHeader() );

      Column< Championship, String > column = new Column< Championship, String >( new TextCell() )
      {
         @Override
         public String getValue( Championship object )
         {
            return "";
         }
      };

      table.addColumn( column, header );
      table.setColumnWidth( column, size, Unit.PX );
   }

   /**
    */
   private class CustomTableBuilder extends AbstractCellTableBuilder< Championship >
   {

      private final String cellStyles;

      private final String evenRowStyle;

      private final String editBtnStyle;

      private final String removeBtnStyle;

      public CustomTableBuilder( DataGrid< Championship > dataGrid )
      {
         super( dataGrid );

         cellStyles = resources.styles().customColumn();
         evenRowStyle = resources.styles().customEvenRow();
         editBtnStyle = resources.styles().customBtn() + " " + resources.styles().customEditBtn();
         removeBtnStyle = resources.styles().customBtn() + " " + resources.styles().customRemoveBtn();
      }

      @Override
      public void buildRowImpl( Championship rowValue, int absRowIndex )
      {

         TableRowBuilder row = startRow();

         if ( absRowIndex % 2 == 1 )
         {
            row.className( evenRowStyle );
         }

         buildRow( row, rowValue.getName() );
         buildRow( row, "2018" );
         buildRow( row, "Free-For-All" );
         buildRow( row, rowValue.getBaseDate() );
         buildRow( row, String.valueOf( rowValue.getGamesByRound() ) );
         buildRow( row, String.valueOf( rowValue.getRoundsByDay() ) );
         buildRow( row, String.valueOf( rowValue.getDateIncr() ) );
         buildRow( row, "Não" );
         buildButtonCell( row, rowValue, false );
         buildButtonCell( row, rowValue, true );
         buildRow( row, "" );

         row.endTR();
      }

      private void buildRow( TableRowBuilder row, String value )
      {
         TableCellBuilder td = row.startTD();
         td.className( cellStyles );
         td.text( value );
         td.endTD();
      }

      private void buildButtonCell( TableRowBuilder row, Championship rowValue, boolean isRemove )
      {
         TableCellBuilder td = row.startTD();
         td.className( cellStyles );
         DivBuilder div = td.startDiv();
         div.className( isRemove ? removeBtnStyle : editBtnStyle );
         renderCell( div, createContext( 0 ), isRemove ? removeColumn : editColumn, rowValue );
         div.endDiv();
         td.endTD();
      }

   }

}
