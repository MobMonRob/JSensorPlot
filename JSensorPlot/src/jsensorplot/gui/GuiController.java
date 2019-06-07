/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import javax.swing.JPanel;
import jsensorplot.TimeWindowInSeconds;
import jsensorplot.sensordata.SensorDataProcessor;

/**
 *
 * @author MobMonRob
 */
public class GuiController {

    private final SensorDataProcessor sensorDataProcessor;
    private final Plot plot;
    private final NextDataPointsWorker nextDataPointsWorker;

    private final TimeWindowInSeconds timeWindowInSeconds;
    private final Zoom zoom;

    public GuiController(boolean DEBUG_MODE) {
	zoom = new Zoom(0, true);
	timeWindowInSeconds = new TimeWindowInSeconds(20);

	sensorDataProcessor = new SensorDataProcessor(DEBUG_MODE);

	plot = new Plot(timeWindowInSeconds, zoom);
	nextDataPointsWorker = new NextDataPointsWorker(sensorDataProcessor, plot);
    }

    public void init() {
	sensorDataProcessor.init();
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
