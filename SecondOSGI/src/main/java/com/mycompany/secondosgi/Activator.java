package com.mycompany.secondosgi;

import com.mycompany.secondosgi.utils.WaitThread;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

public class Activator implements BundleActivator, ServiceListener {
 
    public void start(BundleContext context) {
        System.out.println("Arrancando!");
        WaitThread escucha = new WaitThread();
        escucha.waitForConnection();    
    }
 
    public void stop(BundleContext context) {
        context.removeServiceListener(this);
        System.out.println("Parando!");
        }
   @Override
    public void serviceChanged(ServiceEvent event) {
        String[] objectClass = (String[])
            event.getServiceReference().getProperty("objectClass");
 
        if (event.getType() == ServiceEvent.REGISTERED)
        {
            System.out.println(
                "Ex1: Service of type " + objectClass[0] + " registered.");
        }
        else if (event.getType() == ServiceEvent.UNREGISTERING)
        {
            System.out.println(
                "Ex1: Service of type " + objectClass[0] + " unregistered.");
        }
        else if (event.getType() == ServiceEvent.MODIFIED)
        {
            System.out.println(
                "Ex1: Service of type " + objectClass[0] + " modified.");
        }
    }   
}