/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot;

import jsensorplot.util.Listenable;

/**
 *
 * @author MobMonRob
 */
public class TimeWindowInSeconds extends Listenable<TimeWindowInSeconds> {

    private int timeWindowInSeconds;

    public TimeWindowInSeconds(int timeWindowInSeconds) {
	this.timeWindowInSeconds = timeWindowInSeconds;
    }

    public void setTimeWindow(int timeWindowInSeconds) {
	this.timeWindowInSeconds = timeWindowInSeconds;
	super.changed();
    }

    public int getTimeWindow() {
	return this.timeWindowInSeconds;
    }
}
