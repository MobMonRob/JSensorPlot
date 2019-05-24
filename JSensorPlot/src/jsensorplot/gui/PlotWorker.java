/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import java.util.List;
import javax.swing.SwingWorker;
import jsensorplot.DataPoint;
import jsensorplot.sensordata.SensorDataProcessor;

/**
 *
 * @author MobMonRob
 */
public class PlotWorker extends SwingWorker<Boolean, DataPoint> {

    private final SensorDataProcessor sensorDataProcessor;
    private final Plot plot;

    public PlotWorker(SensorDataProcessor sensorDataProcessor, Plot plot) {
	this.sensorDataProcessor = sensorDataProcessor;
	this.plot = plot;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
	while (!this.isCancelled()) {
	    publish(sensorDataProcessor.getNextDataPoint());
	}

	return true;
    }

    @Override
    protected void process(List<DataPoint> dataPoints) {
	//dataPoints.stream().forEach(dp -> System.out.println(dp.toString()));

	plot.addDataPoints(dataPoints);
    }
}
