/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import javax.swing.JPanel;
import jsensorplot.sensordata.SensorDataProcessor;

/**
 *
 * @author MobMonRob
 */
public class GuiController {

    private final SensorDataProcessor sensorDataProcessor;
    private final Plot plot;
    private final PlotWorker plotWorker;

    public GuiController(boolean DEBUG_MODE) {
	sensorDataProcessor = new SensorDataProcessor(DEBUG_MODE);
	plot = new Plot();
	plotWorker = new PlotWorker(sensorDataProcessor, plot);
    }

    public void init() {
	sensorDataProcessor.init();
	plotWorker.execute();
    }

    public void setTimeWindowInSeconds(int timeWindowInSeconds) {
	if (timeWindowInSeconds > 0) {
	    plot.timeWindowInSeconds.setTimeWindow(timeWindowInSeconds);
	}
    }

    public int getTimeWindowInSeconds() {
	return plot.timeWindowInSeconds.getTimeWindow();
    }

    public void setZoom(int zoom) {
	plot.zoomPanel.zoom.setZoom(zoom);
    }

    public int getZoom() {
	return plot.zoomPanel.zoom.getZoom();
    }

    public void setIsZoomEnabled(boolean isZoomEnabled) {
	plot.zoomPanel.zoom.setIsEnabled(isZoomEnabled);
    }

    public JPanel getPanel() {
	return plot.zoomPanel;
    }
}
