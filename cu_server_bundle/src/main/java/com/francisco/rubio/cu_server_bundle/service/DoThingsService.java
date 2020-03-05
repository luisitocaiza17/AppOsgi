/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.francisco.rubio.cu_server_bundle.service;

/**
 *
 * @author franc
 */
public interface DoThingsService {
    /**
     * Abrir un diferente programa de Windows dependiendo de la accion indicada.
     * @param actionInteger accion a realizar.
     * @return true si la accion se realizo con exito,
     *         false si la accion fallo.
    **/
    public boolean doAction(int actionInteger);
}
