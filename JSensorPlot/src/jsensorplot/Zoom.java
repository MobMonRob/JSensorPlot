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
public class Zoom implements Listenable<Zoom> {

    private int zoom;
    private boolean isEnabled;
    private final ListenerList<Zoom> listenerList;

    public Zoom(int zoom, boolean isEnabled) {
	this.zoom = zoom;
	this.isEnabled = isEnabled;
	listenerList = new ListenerList(this);
    }

    public void setZoom(int zoom) {
	this.zoom = zoom;
	listenerList.changed();
    }

    public int getZoom() {
	return this.zoom;
    }

    public void enable() {
	this.isEnabled = true;
	listenerList.changed();
    }

    public void disable() {
	this.isEnabled = false;
	listenerList.changed();
    }

    public boolean isEnabled() {
	return isEnabled;
    }

    @Override
    public void addChangeListener(Consumer<Zoom> changeListener) {
	listenerList.addChangeListener(changeListener);
    }
}
