package com.futmesa.client.module.main.viewport.player.maintab;

import java.util.ArrayList;
import java.util.List;

import com.futmesa.client.businessinteligence.tablestructures.SimpleMapInfo;
import com.futmesa.client.module.main.viewport.player.PlayerViewport;
import com.futmesa.client.module.main.widgets.donutchart.DonutChart;
import com.futmesa.client.module.main.widgets.simpletable.SimpleTable;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class PlayerMainTab {

	interface Resources extends ClientBundle {

		@Source("playermaintab.css")
		Styles styles();
	}

	interface Styles extends CssResource {

		String sectionTitle();

	}
	
	/**
	 * Constantes da classe.
	 */
	private PlayerMainTabConsts constants;

	/**
	 * Estilos da tabela.
	 */
	private Resources resources;
	
	private HorizontalPanel panel;
	
//	private PlayerViewport parent;
//	private Player player;
	
	private SimpleTable reviewTable;
//	private SimpleTable staticticsTable;
	
	private DonutChart winsChart;
	private DonutChart goalsChart;
	
	/**
	 * Construtor padrão.
	 */
	public PlayerMainTab( PlayerViewport parent ) {
		
		constants = GWT.create(PlayerMainTabConsts.class);

		resources = GWT.create(Resources.class);
		resources.styles().ensureInjected();

		constants = GWT.create(PlayerMainTabConsts.class);
		
//		this.parent = parent;
//		player = null;
		
		panel = new HorizontalPanel();
		
		VerticalPanel leftPanel = new VerticalPanel();
		
		Label reviewTitle = new Label( constants.reviewLabel() );
		reviewTitle.setStyleName( resources.styles().sectionTitle() );
		leftPanel.add( reviewTitle );
		
		reviewTable = new SimpleTable( 240, 120 );
		leftPanel.add(reviewTable.asWidget());
		
		VerticalPanel rightPanel = new VerticalPanel();
		
		Label statisticsTitle = new Label( constants.statisticsLabel() );
		statisticsTitle.setStyleName( resources.styles().sectionTitle() );
		rightPanel.add( statisticsTitle );
		
		HorizontalPanel chartsPanel = new HorizontalPanel();
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
	      chartLoader.loadApi(new Runnable() {
	         public void run() {
	            // Create and attach the chart
	        	 winsChart = new DonutChart();
	        	 goalsChart = new DonutChart();
                 chartsPanel.add( winsChart.asWidget() );
	             chartsPanel.add( goalsChart.asWidget() );
	         }
	      });
		rightPanel.add( chartsPanel );
		
//		staticticsTable = new SimpleTable( 240, 120 );
		//rightPanel.add(staticticsTable.asWidget());
		
		panel.add(leftPanel);
		panel.add(rightPanel);
	}

	public Widget asWidget() {
		return panel;
	}

	public void updateReviewTable( JsArray<SimpleMapInfo> infos )
	{
		reviewTable.updateTableInfo( infos );
	}
	
	public void updateStatisticsCharts( JsArray<SimpleMapInfo> infos )
	{
//		staticticsTable.updateTableInfo( infos );
		List<SimpleMapInfo> winsInfo = new ArrayList<>();
		List<SimpleMapInfo> goalsInfo = new ArrayList<>();
		
		for ( int i=0; i<infos.length();i++ ) {
			if ( !infos.get(i).getKey().contains( "Gols" ) ) {
				winsInfo.add(infos.get(i));
			} else 
				goalsInfo.add(infos.get(i));
		}
		
		if ( winsChart != null ) {
			winsChart.updateData( winsInfo );
		}
		if ( goalsChart != null ) {
			goalsChart.updateData( goalsInfo );
		}
	}
}