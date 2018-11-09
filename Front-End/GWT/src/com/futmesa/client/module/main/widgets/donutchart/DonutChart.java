package com.futmesa.client.module.main.widgets.donutchart;

import java.util.List;

import com.futmesa.client.businessinteligence.tablestructures.SimpleMapInfo;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;

/**
 * Viewport datela de um campeonato, com a sua classificação e rodadas.
 */
public class DonutChart {


	private PieChart chart;
	
	/**
	 * Construtor padrão.
	 */
	public DonutChart() {
		chart = new PieChart();
	}

	public Widget asWidget() {
		return chart;
	}

	public void updateData(List<SimpleMapInfo> infos) {

		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Key");
		data.addColumn(ColumnType.NUMBER, "Value");

		for ( int i=0; i < infos.size(); i++ ) {
			data.addRow( infos.get( i ).getKey(), Integer.parseInt(infos.get( i ).getValue()) );
		}
		PieChartOptions options = PieChartOptions.create();

		options.setTitle("");
		options.setPieHole(0.4);
		options.setHeight(350);
		options.setWidth(350);

		// Draw the chart
		chart.draw(data, options);
		chart.setWidth("400px");
		chart.setHeight("400px");
   }

}