/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import java.util.List;
import javax.swing.SwingWorker;
import jsensorplot.DataPoint;
import jsensorplot.DataPointCoordinatesList;
import jsensorplot.sensordata.SensorDataProcessor;

/**
 *
 * @author MobMonRob
 */
public class NextDataPointsWorker extends SwingWorker<Boolean, DataPoint> {

    private final SensorDataProcessor sensorDataProcessor;
    private final DataPointCoordinatesList dataPointCoordinatesList;
    private final Plot plot;
    private long lastUpdate;

    public NextDataPointsWorker(SensorDataProcessor sensorDataProcessor, DataPointCoordinatesList dataPointCoordinatesList, Plot plot) {
	this.sensorDataProcessor = sensorDataProcessor;
	this.dataPointCoordinatesList = dataPointCoordinatesList;
	this.plot = plot;
	this.lastUpdate = System.currentTimeMillis();
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
	dataPointCoordinatesList.addDataPoints(dataPoints);

	if ((System.currentTimeMillis() - lastUpdate) > 100) { //10fps
	    plot.update();
	    lastUpdate = System.currentTimeMillis();
	}
    }
}
