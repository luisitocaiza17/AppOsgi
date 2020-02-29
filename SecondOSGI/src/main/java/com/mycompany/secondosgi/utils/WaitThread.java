/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.secondosgi.utils;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 *
 * @author luisi
 */
public class WaitThread implements Runnable{

	/** Constructor */
	public WaitThread() {
	}

	@Override
	public void run() {
		waitForConnection();
	}

	/** Waiting for connection from devices */
	public void waitForConnection() {
		// retrieve the local Bluetooth device object
		LocalDevice local = null;

		StreamConnectionNotifier notifier;
		StreamConnection connection = null;

		// setup the server to listen for connection
		try {
			local = LocalDevice.getLocalDevice();
			local.setDiscoverable(DiscoveryAgent.GIAC);

			UUID uuid = new UUID("d0c722b07e1511e1b0c40800200c9a66", false);
			System.out.println(uuid.toString());

            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier)Connector.open(url);
		} catch (Exception e) {
            e.printStackTrace();
            return;
        }

		// waiting for connection
		while(true) {
			try {
				System.out.println("waiting for connection...");
	            connection = notifier.acceptAndOpen();
	            System.out.println("After AcceptAndOpen...");

	            Thread processThread = new Thread(new ProcessConnectionThread(connection));
	            processThread.start();

			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
}