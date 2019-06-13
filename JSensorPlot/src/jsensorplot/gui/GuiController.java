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
import jsensorplot.sensordata.DataPointSource;
import jsensorplot.sensordata.FakeDataSource;
import jsensorplot.sensordata.SensorDataProcessor;
import jsensorplot.sensordata.SensorDataReceiver;

/**
 *
 * @author MobMonRob
 */
public class GuiController {

    private DataPointSource dataPointSource;

    private final Zoom zoom;
    private final TimeWindowInSeconds timeWindowInSeconds;
    private final DataPointCoordinatesList dataPointCoordinatesList;

    private final Plot plot;
    private NextDataPointsWorker nextDataPointsWorker;

    private final boolean DEBUG_MODE;

    public GuiController(boolean DEBUG_MODE) {
	dataPointSource = null;

	zoom = new Zoom(0, true);
	timeWindowInSeconds = new TimeWindowInSeconds(10);
	dataPointCoordinatesList = new DataPointCoordinatesList(timeWindowInSeconds);

	plot = new Plot(dataPointCoordinatesList, zoom);
	nextDataPointsWorker = null;

	this.DEBUG_MODE = DEBUG_MODE;
    }

    public void init() {
	if (DEBUG_MODE) {
	    dataPointSource = new DataPointSource(new FakeDataSource());

	} else {
	    try {
		Thread.sleep(20);
	    } catch (InterruptedException ex) {
		Logger.getLogger(SensorDataProcessor.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    SensorDataReceiver sensorDataReceiver = SensorDataReceiver.createStandardReceiver();
	    BufferedReader sensorDataReader = new BufferedReader(sensorDataReceiver.connect());
	    SensorDataProcessor sensorDataProcessor = new SensorDataProcessor(sensorDataReader);

	    dataPointSource = new DataPointSource(sensorDataProcessor);
	}

	nextDataPointsWorker = new NextDataPointsWorker(dataPointSource, dataPointCoordinatesList, plot);
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
