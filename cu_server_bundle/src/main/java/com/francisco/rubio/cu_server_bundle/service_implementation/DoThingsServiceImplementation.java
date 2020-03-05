/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.francisco.rubio.cu_server_bundle.service_implementation;

import com.francisco.rubio.cu_server_bundle.service.DoThingsService;
import com.francisco.rubio.cu_server_bundle.utils.ConfigurationUtil;
import com.francisco.rubio.cu_server_bundle.utils.DictionaryUtil;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class DoThingsServiceImplementation implements DoThingsService {

    private static final String TAG = DoThingsServiceImplementation.class.getName();

    public boolean doAction(int actionInteger) {
        // Revisar que accion se debe realizar
        switch (actionInteger) {
            case DictionaryUtil.ACTION_OPEN_FILE_EXPLORER:
                return openFileExplorer();
            case DictionaryUtil.ACTION_PLAY_MUSIC_FILE:
                return playFile();
            case DictionaryUtil.ACTION_OPEN_WEB_BROWSER:
                return openWebBrowser();
            default:
                return false;
        }
    }

    /**
     * Abrir el explorador de archivos de windows.
     */
    private boolean openFileExplorer() {
        String METHOD_TAG = TAG + " - openFileExplorer";
        System.out.println("Running: " + METHOD_TAG);
        try {
            String command = "explorer.exe /select";
            System.out.println("> " + command);
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(DoThingsServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("METHOD_TAG: " + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Reproducir una cancion.
     */
    private boolean playFile() {
        String METHOD_TAG = TAG + " - playFile";
        System.out.println("Running: " + METHOD_TAG);
        try {
            // Verificar la ubicación del archivo
            File file = new File(ConfigurationUtil.MUSIC_FILE_PATH);
            if (file.exists()) {
                String command = "cmd.exe /C start " + ConfigurationUtil.MUSIC_FILE_PATH;
                System.out.println("> " + command);
                Runtime.getRuntime().exec(command);
            } else {
                System.err.println("METHOD_TAG: No se encontro el archivo " + ConfigurationUtil.MUSIC_FILE_PATH);
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(DoThingsServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("METHOD_TAG: " + ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Abrir el navegador web.
     */
    private boolean openWebBrowser() {
        String METHOD_TAG = TAG + " - openWebBrowser";
        System.out.println("Running: " + METHOD_TAG);
        try {
            String command = "explorer \"https://google.com\"";
            System.out.println("> " + command);
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(DoThingsServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("METHOD_TAG: " + ex.getMessage());
            return false;
        }
        return true;
    }

}
