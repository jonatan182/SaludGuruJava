/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.entityes.Paciente;
import java.util.List;

public interface pacienteImplementacion {
    public abstract List<Paciente>lista();
    public abstract String guardar(Paciente obj);
}
