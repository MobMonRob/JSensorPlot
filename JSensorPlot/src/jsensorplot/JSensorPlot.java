/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

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
    public static void main(String[] args) {
        JSensorPlot sensorPlot = new JSensorPlot();
        sensorPlot.init();
    }

    public JSensorPlot() {
        plot = new Plot();
        sensorDataProcessor = new SensorDataProcessor();
    }

    public void init() {
        System.out.println("SensorPlot.init()");

        plot.display();
        sensorDataProcessor.init();
        this.loop();
    }

    public void loop() {
        DataPointCoordinatesList allDataPointCoordinates = new DataPointCoordinatesList();

        while (true) {
            allDataPointCoordinates.addDataPoint(sensorDataProcessor.getNextDataPoint());
            plot.updateDatePointCoordinatesList(allDataPointCoordinates);
            plot.repaint();
        }
    }
}
