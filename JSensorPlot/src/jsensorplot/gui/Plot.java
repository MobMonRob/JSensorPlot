/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import jsensorplot.Zoom;
import javax.swing.JPanel;
import jsensorplot.DataPointCoordinatesList;
import org.knowm.xchart.*;
import org.knowm.xchart.style.XYStyler;

/**
 *
 * @author MobMonRob
 */
public class Plot {

    private final XYChart chart;
    private final ZoomPanel zoomPanel;
    private final DataPointCoordinatesList dataPointCoordinatesList;

    public Plot(DataPointCoordinatesList dataPointCoordinatesList, Zoom zoom) {
	this.dataPointCoordinatesList = dataPointCoordinatesList;
	chart = initChart();
	zoomPanel = new ZoomPanel(chart, zoom);

	this.dataPointCoordinatesList.addChangeListener(list -> this.update());
    }

    private XYChart initChart() {
	XYChart newChart = new XYChartBuilder().title("SensorPlot").xAxisTitle("Time").yAxisTitle("Coordinates").build();

	newChart.addSeries​("fx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFx());
	newChart.addSeries​("fy", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFy());
	newChart.addSeries​("fz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFz());
	newChart.addSeries​("mx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMx());
	newChart.addSeries​("my", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMy());
	newChart.addSeries​("mz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMz());

	XYStyler styler = newChart.getStyler();

	styler.setDatePattern("mm:ss");
	styler.setMarkerSize(0);
	styler.setPlotContentSize(1.0);

	styler.setYAxisMin(-200.0); // Problem: what is the interval of the data?
	styler.setYAxisMax(200.0);

	return newChart;
    }

    public void update() {
	updateFromDataPointCoordinatesList();
	zoomPanel.repaint();
    }

    private void updateFromDataPointCoordinatesList() {
	chart.updateXYSeries("fx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFx(), null);
	chart.updateXYSeries("fy", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFy(), null);
	chart.updateXYSeries("fz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFz(), null);
	chart.updateXYSeries("mx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMx(), null);
	chart.updateXYSeries("my", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMy(), null);
	chart.updateXYSeries("mz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMz(), null);
    }

    public JPanel getPanel() {
	return zoomPanel;
    }
}
