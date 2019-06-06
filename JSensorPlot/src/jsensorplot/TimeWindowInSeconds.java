/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import java.util.function.Consumer;
import jsensorplot.util.Listenable;
import jsensorplot.util.ListenerList;

/**
 *
 * @author MobMonRob
 */
public class TimeWindowInSeconds implements Listenable<TimeWindowInSeconds> {

    private int timeWindowInSeconds;
    private ListenerList<TimeWindowInSeconds> listenerList;

    public TimeWindowInSeconds(int timeWindowInSeconds) {
	this.timeWindowInSeconds = timeWindowInSeconds;
	listenerList = new ListenerList(this);
    }

    public void setTimeWindow(int timeWindowInSeconds) {
	this.timeWindowInSeconds = timeWindowInSeconds;
	listenerList.changed();
    }

    public int getTimeWindow() {
	return this.timeWindowInSeconds;
    }

    @Override
    public void addChangeListener(Consumer<TimeWindowInSeconds> changeListener) {
	listenerList.addChangeListener(changeListener);
    }
}
