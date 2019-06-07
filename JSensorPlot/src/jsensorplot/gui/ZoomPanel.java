/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import jsensorplot.Zoom;
import java.awt.Dimension;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

/**
 *
 * @author MobMonRob
 */
public class ZoomPanel extends XChartPanel {

    private final Zoom zoom;

    public ZoomPanel(XYChart chart, Zoom zoom) {
	super(chart);
	this.zoom = zoom;
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
	    zoomedSize = new Dimension(parentSize.width + zoom.getZoom() * 50, parentSize.height);
	}

	return zoomedSize;
    }
}
