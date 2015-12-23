/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dao.medicoDao;
import com.entityes.Medico;
import java.util.ArrayList;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author jonatan
 */
public class medicoJSF {

    private int idMedico;
    private String nombreMedico;
    private DataModel medicoModelo;
    private medicoDao dao = new medicoDao();
    private String msg = "";
    private int estado = 0;
    private ArrayList<SelectItem> itemsM;

    /**
     * @return the idMedico
     */
    public void guardar() {
        Medico obj = new Medico();
        obj.setIdMedico(getIdMedico());
        obj.setNombreMedico(getNombreMedico());
        if (estado == 0) {
            setMsg(dao.guardar(obj));
        } else {
            dao.actualizar(obj);
        }

    }

    public void eliminar(ActionEvent e) {//importamos la segunda "fjavax faces"
        Medico obj = new Medico();
        obj.setIdMedico(Integer.parseInt(e.getComponent().getAttributes().get("mid").toString()));
        setMsg(dao.eliminar(obj));
    }

    public void buscar(ActionEvent e) {
        Medico obj = new Medico();
        obj.setIdMedico(Integer.parseInt(e.getComponent().getAttributes().get("mid").toString()));
        obj = dao.buscar(obj);
        if (obj != null) {
            setIdMedico(obj.getIdMedico());
            setNombreMedico(obj.getNombreMedico());
            estado = 1;
        }
    }

    public int getIdMedico() {
        return idMedico;
    }

    public ArrayList<SelectItem> getItemsM() {
        itemsM = new ArrayList<SelectItem>();

        for (Medico m : dao.lista()) {
            itemsM.add(new SelectItem(m.getIdMedico(), m.getNombreMedico()));
        }
        return itemsM;
    }

    /**
     * @param itemsP the itemsP to set
     */
    public void setItemsM(ArrayList<SelectItem> itemsM) {
        this.itemsM = itemsM;
    }

    /**
     * @param idMedico the idMedico to set
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * @return the nombreMedico
     */
    public String getNombreMedico() {
        return nombreMedico;
    }

    /**
     * @param nombreMedico the nombreMedico to set
     */
    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    /**
     * @return the medicoModelo
     */
    public DataModel getMedicoModelo() {
        medicoModelo = new ListDataModel<Medico>(dao.lista());
        return medicoModelo;
    }

    /**
     * @param medicoModelo the medicoModelo to set
     */
    public void setMedicoModelo(DataModel medicoModelo) {
        this.medicoModelo = medicoModelo;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the medicoModeloConsulta
     */
}
