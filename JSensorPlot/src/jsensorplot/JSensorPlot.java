/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import java.io.IOException;
import jsensorplot.sensordata.SensorDataProcessor;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;

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

        SwingNode swingNode = new SwingNode();
        createAndSetSwingContent(swingNode);
        ScrollPane scrollPane = (ScrollPane) root.lookup("plotPane");
        //Nullpointer Exception -> Findet es nichts mit der Id: "plotPane"???
        //scrollPane.setContent(swingNode);

        primaryStage.setTitle("JSensorPlot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void createAndSetSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(plot.jPanelChart);
            }
        });
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
