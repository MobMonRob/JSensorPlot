/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import jsensorplot.gui.JSensorPlotGui;
import jsensorplot.sensordata.SensorDataProcessor;

/**
 *
 * @author MobMonRob
 */
public class JSensorPlot {

    private final Plot plot;
    private final SensorDataProcessor sensorDataProcessor;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //JSensorPlot sensorPlot = new JSensorPlot();
        
        JSensorPlotGui.main(args);
    }

    public JSensorPlot() {
        plot = new Plot();
        sensorDataProcessor = new SensorDataProcessor();
    }

    public void initialize() {
        //plot.display();
        sensorDataProcessor.init();
        this.loop();
    }

    public void loop() {
        DataPointCoordinatesList allDataPointCoordinates = new DataPointCoordinatesList();

        while (true) {
            allDataPointCoordinates.addDataPoint(sensorDataProcessor.getNextDataPoint());
            //plot.updateDatePointCoordinatesList(allDataPointCoordinates);
            //plot.repaint();
        }
    }
}
