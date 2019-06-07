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

    public NextDataPointsWorker(SensorDataProcessor sensorDataProcessor, DataPointCoordinatesList dataPointCoordinatesList) {
	this.sensorDataProcessor = sensorDataProcessor;
	this.dataPointCoordinatesList = dataPointCoordinatesList;
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
    }
}
