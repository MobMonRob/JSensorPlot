/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import jsensorplot.DataPoint;
import jsensorplot.DataPointCoordinatesList;
import org.knowm.xchart.*;

/**
 *
 * @author MobMonRob
 */
public class Plot {

    private final XYChart chart;
    public final JPanel jPanelChart;
    private final DataPointCoordinatesList dataPointCoordinatesList;

    public Plot() {
	dataPointCoordinatesList = new DataPointCoordinatesList(10);
	DataPoint dummyPoint = new DataPoint(0, 0, 0, 0, 0, 0, Date.from(Instant.now()));
	dataPointCoordinatesList.addDataPoint(dummyPoint);

	chart = initChart();
	jPanelChart = new XChartPanel(chart);
    }

    private XYChart initChart() {
	XYChart newChart = new XYChartBuilder().width(800).height(600).title("SensorPlot").xAxisTitle("Time").yAxisTitle("Coordinates").build();

	newChart.addSeries​("fx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFx());
	newChart.addSeries​("fy", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFy());
	newChart.addSeries​("fz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFz());
	newChart.addSeries​("mx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMx());
	newChart.addSeries​("my", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMy());
	newChart.addSeries​("mz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMz());

	newChart.getStyler().setDatePattern("mm:ss");

	return newChart;
    }

    public void addDataPoints(List<DataPoint> dataPoints) {
	dataPointCoordinatesList.addDataPoints(dataPoints);
	updateDataPointCoordinatesList();
	jPanelChart.repaint();
    }

    private void updateDataPointCoordinatesList() {
	chart.updateXYSeries("fx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFx(), null);
	chart.updateXYSeries("fy", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFy(), null);
	chart.updateXYSeries("fz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getFz(), null);
	chart.updateXYSeries("mx", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMx(), null);
	chart.updateXYSeries("my", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMy(), null);
	chart.updateXYSeries("mz", dataPointCoordinatesList.getTimestamp(), dataPointCoordinatesList.getMz(), null);
    }
}
