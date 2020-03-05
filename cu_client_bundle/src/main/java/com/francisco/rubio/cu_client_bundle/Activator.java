package com.francisco.rubio.cu_client_bundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.francisco.rubio.cu_server_bundle.service.DoThingsService;
import com.francisco.rubio.cu_server_bundle.utils.DictionaryUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Activator implements BundleActivator {

    // Bundle's context.
    private BundleContext m_context = null;
    // The service tacker object.
    private ServiceTracker m_tracker = null;

    public void start(BundleContext context) throws Exception {
        m_context = context;

        // Create a service tracker to monitor dictionary services.
        m_tracker = new ServiceTracker(
                m_context,
                m_context.createFilter(
                        "(&(objectClass=" + DoThingsService.class.getName() + "))"), null);
        m_tracker.open();

        try {
            // Imprimir menu
            System.out.println("Posibles acciones");
            System.out.println(DictionaryUtil.ACTION_OPEN_FILE_EXPLORER + " - Abrir explorador de archivos");
            System.out.println(DictionaryUtil.ACTION_PLAY_MUSIC_FILE + " - Reproducir archivo de audio");
            System.out.println(DictionaryUtil.ACTION_OPEN_WEB_BROWSER + " - Abrir navegador web");

            System.out.println(DictionaryUtil.ACTION_EXIT + " - Salir");

            System.out.println("Ingresa el número de la accion a realizar");
            String textLine = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            // Loop endlessly.
            while (true) {
                System.out.print("Acción: ");
                textLine = in.readLine();
                int action = Integer.parseInt(textLine);
                // Get the selected dictionary service, if available.
                DoThingsService doThingsService = (DoThingsService) m_tracker.getService();

                // If the user entered a blank line, then
                // exit the loop.
                System.out.println("");
                if (action == 0) {
                    break;
                } // If there is no dictionary, then say so.
                else if (doThingsService == null) {
                    System.out.println("El servicio no esta disponible");
                } // Otherwise print whether the word is correct or not.
                else if (doThingsService.doAction(action)) {
                    System.out.println("Acción completada");
                } else {
                    System.out.println("Error al ejecutar la acción");
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
