/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entityes.Medico;
import java.util.List;


public interface medicoImplementacion {
    public abstract List<Medico>lista();
    public abstract String guardar(Medico obj);
    public abstract String eliminar(Medico obj);
    public abstract Medico buscar(Medico obj);
    public abstract String actualizar(Medico obj);
    
}
