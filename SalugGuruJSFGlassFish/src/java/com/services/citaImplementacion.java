/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entityes.Citas;
import com.entityes.Medico;
import java.util.List;

public interface citaImplementacion {
    public abstract List<Citas>lista();
    public abstract String guardar(Citas obj);
    public abstract String eliminar(Citas obj);
    public abstract String actualizar(Citas obj);
    public abstract Citas buscar(Citas obj);
    public abstract List<Citas> consultaM(Citas obj,String fecha);
}
