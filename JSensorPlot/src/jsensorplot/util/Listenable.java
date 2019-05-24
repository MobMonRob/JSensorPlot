/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.util;

import java.util.function.Consumer;

/**
 *
 * @author MobMonRob
 */
public interface Listenable<T> {

    public void addChangeListener(Consumer<T> changeListener);
}
