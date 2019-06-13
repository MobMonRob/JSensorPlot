/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.sensordata;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import jsensorplot.DataPoint;

/**
 *
 * @author MobMonRob
 */
public class SensorDataPointParser {

    public static final int MAX_DATA_POINT_STRING_SIZE = 83;
    private static final Pattern WHOLE_COORDINATE_FORMAT = Pattern.compile("\\(.+?\\)");
    private static final Pattern COORDINATE_FORMAT = Pattern.compile("-?[0-9]+.{1}[0-9]+");

    private String dataParseBuffer;

    public SensorDataPointParser() {
	dataParseBuffer = "";
    }

    public DataPoint parseNextDataPoint() {
	return parseDataPoint(splitNextDataPointString(), Date.from(Instant.now()));
    }

    public void addToParseBuffer(String appendage) {
	dataParseBuffer = dataParseBuffer + appendage;
    }

    public boolean isBufferFullEnough() {
	return dataParseBuffer.length() >= MAX_DATA_POINT_STRING_SIZE;
    }

    private DataPoint parseDataPoint(String dataPointString, Date now) {
	ArrayList<String> stringCoordinates = new ArrayList();

	Matcher coordinateMatcher = COORDINATE_FORMAT.matcher(dataPointString);

	while (coordinateMatcher.find()) {
	    stringCoordinates.add(dataPointString.substring(coordinateMatcher.start(), coordinateMatcher.end()));
	}
	assert stringCoordinates.size() == 6;

	List<Double> dc = stringCoordinates.stream().map(s -> Double.parseDouble(s)).collect(Collectors.toList()); //dc: doubleCoordinates

	return new DataPoint(dc.get(0), dc.get(1), dc.get(2), dc.get(3), dc.get(4), dc.get(5), now);
    }

    private String splitNextDataPointString() {
	Matcher wholeCoordinateMatcher = WHOLE_COORDINATE_FORMAT.matcher(dataParseBuffer);
	wholeCoordinateMatcher.find();
	String nextDataPointString = dataParseBuffer.substring(wholeCoordinateMatcher.start(), wholeCoordinateMatcher.end());
	dataParseBuffer = dataParseBuffer.substring(wholeCoordinateMatcher.end(), dataParseBuffer.length());
	return nextDataPointString;
    }
}
