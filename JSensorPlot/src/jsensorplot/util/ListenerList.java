/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author MobMonRob
 */
public class ListenerList<T extends Listenable<T>> implements Listenable<T> {

    private final List<Consumer<T>> changeListeners;
    private final T listenable;

    public ListenerList(T listenable) {
	changeListeners = new ArrayList();
	this.listenable = listenable;
    }

    @Override
    public void addChangeListener(Consumer<T> changeListener) {
	changeListeners.add(changeListener);
    }

    public void changed() {
	changeListeners.forEach(listener -> listener.accept(listenable));
    }
}
