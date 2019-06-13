/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsensorplot.Zoom;
import javax.swing.JPanel;
import jsensorplot.DataPointCoordinatesList;
import jsensorplot.TimeWindowInSeconds;
import jsensorplot.sensordata.SensorDataProcessor;
import jsensorplot.sensordata.SensorDataReceiver;

/**
 *
 * @author MobMonRob
 */
public class GuiController {

    private BufferedReader sensorDataReader;
    private final SensorDataReceiver sensorDataReceiver;
    private SensorDataProcessor sensorDataProcessor;

    private final Zoom zoom;
    private final TimeWindowInSeconds timeWindowInSeconds;
    private final DataPointCoordinatesList dataPointCoordinatesList;

    private final Plot plot;
    private NextDataPointsWorker nextDataPointsWorker;

    private final boolean DEBUG_MODE;

    public GuiController(boolean DEBUG_MODE) {
	sensorDataReceiver = SensorDataReceiver.createStandardReceiver();
	sensorDataReader = null;
	sensorDataProcessor = null;

	zoom = new Zoom(0, true);
	timeWindowInSeconds = new TimeWindowInSeconds(10);
	dataPointCoordinatesList = new DataPointCoordinatesList(timeWindowInSeconds);

	plot = new Plot(dataPointCoordinatesList, zoom);
	nextDataPointsWorker = null;

	this.DEBUG_MODE = DEBUG_MODE;
    }

    public void init() {
	if (!DEBUG_MODE) {
	    sensorDataReader = new BufferedReader(sensorDataReceiver.connect());
	    try {
		Thread.sleep(20);
	    } catch (InterruptedException ex) {
		Logger.getLogger(SensorDataProcessor.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	sensorDataProcessor = new SensorDataProcessor(DEBUG_MODE, sensorDataReader);

	nextDataPointsWorker = new NextDataPointsWorker(sensorDataProcessor, dataPointCoordinatesList, plot);
	nextDataPointsWorker.execute();
    }

    public void setTimeWindowInSeconds(int timeWindow) {
	if (timeWindow > 0) {
	    timeWindowInSeconds.setTimeWindow(timeWindow);
	}
    }

    public int getTimeWindowInSeconds() {
	return timeWindowInSeconds.getTimeWindow();
    }

    public void setZoom(int theZoom) {
	zoom.setZoom(theZoom);
    }

    public int getZoom() {
	return zoom.getZoom();
    }

    public void setIsZoomEnabled(boolean isZoomEnabled) {
	zoom.setIsEnabled(isZoomEnabled);
    }

    public JPanel getPanel() {
	return plot.getPanel();
    }
}
