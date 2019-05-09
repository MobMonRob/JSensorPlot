/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author MobMonRob
 */
public class DataPointCoordinatesList {

    public final List<Double> fx;
    public final List<Double> fy;
    public final List<Double> fz;
    public final List<Double> mx;
    public final List<Double> my;
    public final List<Double> mz;
    public final List<Date> timestamp;
    private Date firstTimestamp;

    public DataPointCoordinatesList() {
        fx = new ArrayList();
        fy = new ArrayList();
        fz = new ArrayList();
        mx = new ArrayList();
        my = new ArrayList();
        mz = new ArrayList();
        timestamp = new ArrayList();
    }

    public void addDataPoints(List<DataPoint> dataPoints) {
        fx.addAll(dataPoints.stream().map(dataPoint -> dataPoint.fx).collect(Collectors.toList()));
        fy.addAll(dataPoints.stream().map(dataPoint -> dataPoint.fy).collect(Collectors.toList()));
        fz.addAll(dataPoints.stream().map(dataPoint -> dataPoint.fz).collect(Collectors.toList()));
        mx.addAll(dataPoints.stream().map(dataPoint -> dataPoint.mx).collect(Collectors.toList()));
        my.addAll(dataPoints.stream().map(dataPoint -> dataPoint.my).collect(Collectors.toList()));
        mz.addAll(dataPoints.stream().map(dataPoint -> dataPoint.mz).collect(Collectors.toList()));
        timestamp.addAll(dataPoints.stream().map(dataPoint -> dataPoint.timestamp).collect(Collectors.toList()));
    }

    public void addDataPoint(DataPoint dataPoint) {
        fx.add(dataPoint.fx);
        fy.add(dataPoint.fy);
        fz.add(dataPoint.fz);
        mx.add(dataPoint.mx);
        my.add(dataPoint.my);
        mz.add(dataPoint.mz);

        if (timestamp.isEmpty()) {
            firstTimestamp = dataPoint.timestamp;
        }

        timestamp.add(Date.from(Instant.ofEpochMilli(dataPoint.timestamp.getTime() - firstTimestamp.getTime())));
    }
}
