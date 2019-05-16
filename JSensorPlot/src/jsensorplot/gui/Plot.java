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

    public Plot() {
	chart = new XYChartBuilder().width(800).height(600).title("SensorPlot").xAxisTitle("Time").yAxisTitle("Coordinates").build();
	List yDummy = new ArrayList<Double>();
	yDummy.add(0.0);
	List xDummy = new ArrayList<Date>();
	xDummy.add(Date.from(Instant.now()));

	chart.addSeries​("fx", xDummy, yDummy);
	chart.addSeries​("fy", xDummy, yDummy);
	chart.addSeries​("fz", xDummy, yDummy);
	chart.addSeries​("mx", xDummy, yDummy);
	chart.addSeries​("my", xDummy, yDummy);
	chart.addSeries​("mz", xDummy, yDummy);

	chart.getStyler().setDatePattern("mm:ss");

	jPanelChart = new XChartPanel(chart);
    }

    public void addDataPoints(List<DataPoint> dataPoints) {
	//
    }

    public void updateDataPointCoordinatesList(DataPointCoordinatesList dataPointCoordinatesList) {
	chart.updateXYSeries("fx", dataPointCoordinatesList.timestamp, dataPointCoordinatesList.fx, null);
	chart.updateXYSeries("fy", dataPointCoordinatesList.timestamp, dataPointCoordinatesList.fy, null);
	chart.updateXYSeries("fz", dataPointCoordinatesList.timestamp, dataPointCoordinatesList.fz, null);
	chart.updateXYSeries("mx", dataPointCoordinatesList.timestamp, dataPointCoordinatesList.mx, null);
	chart.updateXYSeries("my", dataPointCoordinatesList.timestamp, dataPointCoordinatesList.my, null);
	chart.updateXYSeries("mz", dataPointCoordinatesList.timestamp, dataPointCoordinatesList.mz, null);
    }

    public void repaint() {
	//wrappedChart.repaintChart();
    }
}
