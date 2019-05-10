/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import java.io.IOException;
import jsensorplot.sensordata.SensorDataProcessor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MobMonRob
 */
public class JSensorPlot extends Application {

    private final Plot plot;
    private final SensorDataProcessor sensorDataProcessor;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        JSensorPlot sensorPlot = new JSensorPlot();
        //sensorPlot.init();
        launch(args);
    }

    public JSensorPlot() {
        plot = new Plot();
        sensorDataProcessor = new SensorDataProcessor();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gui/JSensorPlot.fxml"));
        primaryStage.setTitle("JSensorPlot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initialize() {
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
