/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.sensordata;

import jsensorplot.DataPoint;

/**
 *
 * @author MobMonRob
 */
public interface DataPointSource {

    public DataPoint getNextDataPoint();
}
