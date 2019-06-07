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
 * @param <T>
 */
public abstract class Listenable<T extends Listenable> {

    private final List<Consumer<T>> changeListeners;

    public Listenable() {
	changeListeners = new ArrayList();
    }

    public void addChangeListener(Consumer<T> changeListener) {
	changeListeners.add(changeListener);
    }

    protected void changed() {
	changeListeners.forEach(listener -> listener.accept((T) this));
    }
}
