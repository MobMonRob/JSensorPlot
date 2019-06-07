/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import jsensorplot.util.Listenable;

/**
 *
 * @author MobMonRob
 */
public class DataPointCoordinatesList extends Listenable<DataPointCoordinatesList> {

    private final LinkedList<Double> fx;
    private final LinkedList<Double> fy;
    private final LinkedList<Double> fz;
    private final LinkedList<Double> mx;
    private final LinkedList<Double> my;
    private final LinkedList<Double> mz;
    private final LinkedList<Date> timestamps;
    private final ArrayList<Date> relativeTimestamps;
    private boolean isValid;
    private Date firstTimestamp;
    private final TimeWindowInSeconds timeWindowInSeconds;

    public DataPointCoordinatesList(TimeWindowInSeconds timeWindowInSeconds) {
	fx = new LinkedList();
	fy = new LinkedList();
	fz = new LinkedList();
	mx = new LinkedList();
	my = new LinkedList();
	mz = new LinkedList();
	timestamps = new LinkedList();
	relativeTimestamps = new ArrayList();
	isValid = false;

	this.addDataPoint(new DataPoint(0, 0, 0, 0, 0, 0, Date.from(Instant.now())));

	this.timeWindowInSeconds = timeWindowInSeconds;
	this.timeWindowInSeconds.addChangeListener(window -> this.invalidate()); //not nice
    }

    public List<Double> getFx() {
	ensureValidity();
	return fx;
    }

    public List<Double> getFy() {
	ensureValidity();
	return fy;
    }

    public List<Double> getFz() {
	ensureValidity();
	return fz;
    }

    public List<Double> getMx() {
	ensureValidity();
	return mx;
    }

    public List<Double> getMy() {
	ensureValidity();
	return my;
    }

    public List<Double> getMz() {
	ensureValidity();
	return mz;
    }

    public List<Date> getTimestamp() {
	ensureValidity();
	return relativeTimestamps;
    }

    private void ensureValidity() {
	if (!isValid) {
	    shrinkListsToTimeWindow();
	    recalculateRelativeTimestamp();
	    isValid = true;
	}
    }

    public void invalidate() {
	isValid = false;
	super.changed();
    }

    private void shrinkListsToTimeWindow() {
	Date lastTimestamp = timestamps.getLast();
	int timestampIndex = 0;

	ListIterator<Date> timestampIterator = timestamps.listIterator();

	while (timestampIterator.hasNext()) {
	    int nextTimestampIndex = timestampIterator.nextIndex();
	    long distance = (dateDifference(timestampIterator.next(), lastTimestamp).getTime()) / 1000;

	    if (distance < timeWindowInSeconds.getTimeWindow()) {
		timestampIndex = nextTimestampIndex;
		break;
	    }
	}
	removeFirstElements(timestampIndex);
	firstTimestamp = timestamps.getFirst();
    }

    private void removeFirstElements(int number) {
	removeFirstElementsOfList(number, fx);
	removeFirstElementsOfList(number, fy);
	removeFirstElementsOfList(number, fz);
	removeFirstElementsOfList(number, mx);
	removeFirstElementsOfList(number, my);
	removeFirstElementsOfList(number, mz);
	removeFirstElementsOfList(number, timestamps);
    }

    private void removeFirstElementsOfList(int numberOfElements, LinkedList list) {
	for (int i = 0; i < numberOfElements; ++i) {
	    list.removeFirst();
	}
    }

    private Date dateDifference(Date from, Date to) {
	return Date.from(Instant.ofEpochMilli(to.getTime() - from.getTime()));
    }

    private void recalculateRelativeTimestamp() {
	relativeTimestamps.clear();
	timestamps.forEach(currentTimestamp -> relativeTimestamps.add(dateDifference(firstTimestamp, currentTimestamp)));
    }

    public void addDataPoints(List<DataPoint> dataPoints) {
	dataPoints.forEach(dataPoint -> addDataPoint(dataPoint));
	super.changed();
    }

    private void addDataPoint(DataPoint dataPoint) {
	fx.add(dataPoint.fx);
	fy.add(dataPoint.fy);
	fz.add(dataPoint.fz);
	mx.add(dataPoint.mx);
	my.add(dataPoint.my);
	mz.add(dataPoint.mz);
	timestamps.add(dataPoint.timestamp);
	invalidate();
    }
}
