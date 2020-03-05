package com.francisco.rubio.cu_server_bundle;

import com.francisco.rubio.cu_server_bundle.service.DoThingsService;
import com.francisco.rubio.cu_server_bundle.service_implementation.DoThingsServiceImplementation;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(
                DoThingsService.class.getName(), new DoThingsServiceImplementation(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
