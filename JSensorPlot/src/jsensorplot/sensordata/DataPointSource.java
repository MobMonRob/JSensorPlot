/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.sensordata;

import jsensorplot.DataPoint;

/**
 *
 * @author MobMonRob
 */
public class DataPointSource {

    private final DataPointSourceProvider dataPointSourceProvider;
    private final SensorDataPointParser sensorDataPointParser;

    public DataPointSource(DataPointSourceProvider dataPointSourceProvider) {
	this.dataPointSourceProvider = dataPointSourceProvider;
	sensorDataPointParser = new SensorDataPointParser();
    }

    public DataPoint getNextDataPoint() {
	if (!sensorDataPointParser.isBufferFullEnough()) {
	    sensorDataPointParser.addToParseBuffer(dataPointSourceProvider.nextDataPointString());
	}

	return sensorDataPointParser.parseNextDataPoint();
    }
}
