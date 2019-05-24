/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import java.awt.Dimension;
import jsensorplot.TimeWindowInSeconds;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

/**
 *
 * @author MobMonRob
 */
public class TimeWindowPanel extends XChartPanel {

    private final TimeWindowInSeconds timeWindowInSeconds;

    public TimeWindowPanel(XYChart chart, TimeWindowInSeconds timeWindowInSeconds) {
	super(chart);
	this.timeWindowInSeconds = timeWindowInSeconds;
    }

    @Override
    public Dimension getPreferredSize() {
	if (getParent() == null) {
	    return getSize();
	}

	Dimension parentSize = getParent().getSize();

	//this.
	//
	//return new Dimension(10000, 100);
	return new Dimension(getParent().getSize());
    }
}
