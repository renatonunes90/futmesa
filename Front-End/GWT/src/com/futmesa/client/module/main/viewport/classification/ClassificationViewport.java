package com.futmesa.client.module.main.viewport.classification;

import java.util.List;

import com.futmesa.client.FutMesaConsts;
import com.futmesa.client.base.FilterConfig;
import com.futmesa.client.base.ViewportInterface;
import com.futmesa.client.businessinteligence.Player;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Viewport com exemplos de utilização do SimpleGrid.
 */
public class ClassificationViewport
   implements ViewportInterface
{

   private static final SampleTableViewportUiBinder uiBinder = GWT.create( SampleTableViewportUiBinder.class );

   interface SampleTableViewportUiBinder
      extends UiBinder< HorizontalPanel, ClassificationViewport >
   {}

   @UiField
   protected HorizontalPanel portal;

   /**
    * Construtor padrão.
    */
   public ClassificationViewport()
   {
      uiBinder.createAndBindUi( this );

      this.createExample1();
   }

   /**
    * Criação de exemplo 1 de tabela. Possui renderer customizado, filtros e listener de duplo clique.
    */
   public void createExample1()
   {
      // example1 = new SimpleGrid<>( tProperties.key() );
      //
      // // colunas padrões
      // example1.addColumn( new ColumnConfig<>( tProperties.idType(), 100, SampleTableConsts.INSTANCE.id() ) );
      // example1.addColumnWithTooltip( new ColumnConfig<>( tProperties.name(), 250, SampleTableConsts.INSTANCE.name() ) );
      // example1.addColumnWithTooltip( new ColumnConfig<>( tProperties.typeOfName(), 250, SampleTableConsts.INSTANCE.typeOfName() ) );
      // example1.addColumnWithTooltip( new ColumnConfig<>( tProperties.description(), 450, SampleTableConsts.INSTANCE.description() ) );
      //
      // // coluna com renderer customizado
      // ColumnConfig< Type, Integer > custom = new ColumnConfig<>( tProperties.idTypeOf(), 150, SampleTableConsts.INSTANCE.button() );
      // ButtonCell< Integer > customCell = new ButtonCell< Integer >()
      // {
      // @Override
      // public void render( Context context, Integer value, SafeHtmlBuilder sb )
      // {
      // super.render( context, null, sb );
      // }
      // };
      // customCell.setIcon( TriResources.INSTANCE.help() );
      // customCell.addSelectHandler( handler ->
      // {
      // Type selected = example1.getSelectedItems().get( 0 );
      // AlertMessageBox msgBox = new AlertMessageBox( SampleTableConsts.INSTANCE.warningTitle(),
      // SampleTableMessages.INSTANCE.clickedMessage( selected.getIdTypeOf() ) );
      // msgBox.show();
      // } );
      // custom.setCell( customCell );
      // example1.addColumn( custom );
      //
      // // filtros
      // example1.addFilter( new NumericFilter< Type, Integer >( tProperties.idType(), new IntegerPropertyEditor() ) );
      // example1.addFilter( new StringFilter< Type >( tProperties.name() ) );
      // example1.addFilter( new StringFilter< Type >( tProperties.description() ) );
      //
      // // ordenação default
      // example1.addSortInfo( new StoreSortInfo< Type >( tProperties.name(), SortDir.ASC ) );
      //
      // // listener para duplo clique
      // example1.addCellDoubleClickHandler( handler ->
      // {
      // Type selected = example1.getSelectedItems().get( 0 );
      // AlertMessageBox msgBox = new AlertMessageBox( SampleTableConsts.INSTANCE.warningTitle(),
      // SampleTableMessages.INSTANCE.doubleClickedMessage( selected.getName() ) );
      // msgBox.show();
      // } );
      //
      // // adiciona na tela
      // firstTable.setHeading( SampleTableConsts.INSTANCE.firstTableHeader() );
      // firstTable.add( example1 );
   }

   @Override
   public HorizontalPanel asWidget()
   {
      return portal;
   }

   @Override
   public FilterConfig getFilterConfig()
   {
      return null;
   }

   @Override
   public String getHelp()
   {

      return FutMesaConsts.INSTANCE.helpPage();
   }

   @Override
   public void updateView( FilterConfig filterConfig )
   {
      // preenche os grids com as informações do TypeProvider
      // List< Type > types = TypeProvider.getInstance().getAllTypes();
      // List< Integer > alreadyAdded = new ArrayList<>();
      // for ( int index = 0; index < types.size(); index++ )
      // {
      // example1.addRow( types.get( index ) );
      // if ( alreadyAdded.indexOf( types.get( index ).getIdTypeOf() ) == -1 )
      // {
      // alreadyAdded.add( types.get( index ).getIdTypeOf() );
      // example3.addRow( types.get( index ) );
      // }
      // }
   }

   public void setPlayers( Player[] players )
   {
	   
   }
}
