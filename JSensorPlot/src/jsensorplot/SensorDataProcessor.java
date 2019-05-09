/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.time.OffsetDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author MobMonRob
 */
public class SensorDataProcessor {

    static final Pattern WHOLE_COORDINATE_FORMAT = Pattern.compile("\\(.+?\\)");

    SensorDataReceiver dataReceiver;
    BufferedReader dataReader;

    char[] dataReaderBuffer;
    CharBuffer dataConcatBuffer;
    String dataParseBuffer;

    FakeDataSource fakeDataSource;
    boolean DEBUG = false;

    public SensorDataProcessor() {
        dataReceiver = SensorDataReceiver.createStandardReceiver();

        dataReaderBuffer = new char[SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE];
        dataConcatBuffer = CharBuffer.allocate(SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE * 3);
        dataParseBuffer = "";

        fakeDataSource = new FakeDataSource();
    }

    public void init() {
        if (!DEBUG) {
            dataReader = new BufferedReader(dataReceiver.connect());
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(SensorDataProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public DataPoint getNextDataPoint() {
        System.out.println("SensorDataProcessor.getNextDataPoint()");
        dataConcatBuffer.clear();

        if (dataParseBuffer.length() < SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE) {
            if (DEBUG) {
                dataParseBuffer = dataParseBuffer + fakeDataSource.getNext();
            } else {
                fetchNextDataPoint();
            }
        }

        return SensorDataPointParser.parse(recognizeNextDataPointString(), OffsetDateTime.now());
    }

    private void fetchNextDataPoint() {
        System.out.println("fetchNextDataPoint()");
        int readCount = 0;

        try {
            while (readCount < SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE) {
                while (!dataReader.ready()) {
                    Thread.sleep(10);
                }

                int newCharactersReadCount = dataReader.read(dataReaderBuffer, 0, SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE);

                if (newCharactersReadCount < 1) {
                    continue;
                }

                dataConcatBuffer.put(dataReaderBuffer, 0, newCharactersReadCount); //Überträgt nicht
                readCount = readCount + newCharactersReadCount;
            }
            
            dataConcatBuffer.flip();
            dataParseBuffer = dataParseBuffer + dataConcatBuffer.toString();
        } catch (IOException e) {
            System.err.println("Reading next Characters of the sensor data failed!");
        } catch (InterruptedException ex) {
            System.err.println("Interrupted while sleeping!");
        }
    }

    private String recognizeNextDataPointString() {
        Matcher wholeCoordinateMatcher = WHOLE_COORDINATE_FORMAT.matcher(dataParseBuffer);
        wholeCoordinateMatcher.find();
        String nextDataPointString = dataParseBuffer.substring(wholeCoordinateMatcher.start(), wholeCoordinateMatcher.end());
        dataParseBuffer = dataParseBuffer.substring(wholeCoordinateMatcher.end(), dataParseBuffer.length());
        return nextDataPointString;
    }
}
