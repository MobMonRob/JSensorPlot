/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsensorplot.sensordata;

import java.io.*;
import java.net.*;

/**
 *
 * @author MobMonRob
 */
public class SensorDataReceiver {

    //test: socat - TCP4:192.168.3.2:63351
    private static final String SENSOR_IP_ADRESS = "192.168.3.2";
    private static final int SENSOR_PORT = 63351;

    private final String ipAdress;
    private final int port;
    private Socket socket;

    public SensorDataReceiver(String ipAdress, int port) {
	this.ipAdress = ipAdress;
	this.port = port;
	this.socket = new Socket();
    }

    public static SensorDataReceiver createStandardReceiver() {
	return new SensorDataReceiver(SENSOR_IP_ADRESS, SENSOR_PORT);
    }

    public InputStreamReader connect() {
	InputStreamReader socketReader = null;
	socket = new Socket();

	try {
	    InetSocketAddress inetSocketAdress = new InetSocketAddress(InetAddress.getByName(ipAdress), port);
	    socket.connect(inetSocketAdress, 1000); //with timeout
	    socketReader = new InputStreamReader(socket.getInputStream());
	} catch (SocketTimeoutException e) {
	    System.err.println("Connection timed out!");
	    System.err.println(e.toString());
	} catch (IOException e) {
	    System.err.println("Connection failed!");
	    System.err.println(e.toString());
	}

	return socketReader;
    }

    public void deconnect() {
	System.out.println("SensorDataReceiver.deconnect()");
	try {
	    socket.close();
	} catch (IOException e) {
	    System.err.println("deconnecting failed!");
	}
    }
}
