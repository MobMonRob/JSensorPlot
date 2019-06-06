/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import java.awt.Dimension;
import jsensorplot.Zoom;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

/**
 *
 * @author MobMonRob
 */
public class ZoomPanel extends XChartPanel {

    public final Zoom zoom;

    public ZoomPanel(XYChart chart) {
	super(chart);
	zoom = new Zoom(0, true);
	zoom.addChangeListener(theZoom -> {
	    this.invalidate();
	    this.getParent().validate();
	});
    }

    @Override
    public Dimension getPreferredSize() {
	if (getParent() == null) {
	    return getSize();
	}

	Dimension parentSize = getParent().getSize();

	Dimension zoomedSize = parentSize;

	if (zoom.isEnabled()) {
	    zoomedSize = new Dimension(parentSize.width + zoom.getZoom() * 100, parentSize.height);
	}

	return zoomedSize;
    }
}
