/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.gui;

import jsensorplot.util.Listenable;

/**
 *
 * @author MobMonRob
 */
public class Zoom extends Listenable<Zoom> {

    private int zoom;
    private boolean isEnabled;

    public Zoom(int zoom, boolean isEnabled) {
	this.zoom = zoom;
	this.isEnabled = isEnabled;
    }

    public void setZoom(int zoom) {
	this.zoom = zoom;
	super.changed();
    }

    public int getZoom() {
	return this.zoom;
    }

    public void setIsEnabled(boolean isEnabled) {
	this.isEnabled = isEnabled;
	super.changed();
    }

    public boolean isEnabled() {
	return isEnabled;
    }
}
