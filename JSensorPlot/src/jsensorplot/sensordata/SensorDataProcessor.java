/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.sensordata;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.CharBuffer;

/**
 *
 * @author MobMonRob
 */
public class SensorDataProcessor implements DataPointSourceProvider {

    private final BufferedReader sensorDataReader;

    private final char[] dataReaderBuffer;
    private final CharBuffer dataConcatBuffer;

    public SensorDataProcessor(BufferedReader sensorDataReader) {
	this.sensorDataReader = sensorDataReader;

	dataReaderBuffer = new char[SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE];
	dataConcatBuffer = CharBuffer.allocate(SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE * 3);
    }

    @Override
    public String nextDataPointString() {
	dataConcatBuffer.clear();
	int readCount = 0;

	try {
	    while (readCount < SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE) {
		while (!sensorDataReader.ready()) {
		    Thread.sleep(40); //empirically tested optimal duration
		}

		int newCharactersReadCount = sensorDataReader.read(dataReaderBuffer, 0, SensorDataPointParser.MAX_DATA_POINT_STRING_SIZE);

		if (newCharactersReadCount < 1) {
		    continue;
		}

		dataConcatBuffer.put(dataReaderBuffer, 0, newCharactersReadCount);
		readCount = readCount + newCharactersReadCount;
	    }

	    dataConcatBuffer.flip();
	} catch (IOException e) {
	    System.err.println("Reading next Characters of the sensor data failed!");
	} catch (InterruptedException e) {
	    System.err.println("Interrupted while sleeping!");
	}

	return dataConcatBuffer.toString();
    }
}
