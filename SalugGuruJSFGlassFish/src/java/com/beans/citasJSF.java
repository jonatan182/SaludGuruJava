/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dao.citasDao;
import com.dao.medicoDao;
import com.dao.pacienteDao;
import com.entityes.Citas;
import com.entityes.Medico;
import com.entityes.Paciente;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author jonatan
 */
public class citasJSF {
    private int idCita,idPaciente,idMedico;
    private String horaCita;
    private Date fechaCita;
    private DataModel citaModelo;
    private int valorCita;
    private citasDao dao = new citasDao();
    private ArrayList<SelectItem>itemsP;
    private DataModel medicoModeloConsulta;
    private String msg="";
    private int estado=0;
    /**
     * @return the idCita
     */
    public Citas buscar(ActionEvent ae){
        Citas objc = new Citas();
        try{
           objc.setIdCita(Integer.parseInt(ae.getComponent().getAttributes().get("mid").toString()));
           objc = dao.buscar(objc);
           if(objc != null){
               setIdCita(objc.getIdCita());
               setHoraCita(objc.getHoraCita()+"");
               setEstado(1);
               setIdPaciente(objc.getIdPaciente().getIdPaciente());//para que el combobox tenga seleccionado el valor de la consulta
               setIdMedico(objc.getIdMedico().getIdMedico());//en la consulta el combobox tendra seleccionado como item el valor de la consulta por que el componente se inializa con la variable 
           }
        }catch(Exception e){
            
        }
        return objc;
    }
    public String eliminar(ActionEvent e){
        try{
            Citas obj = new Citas();
            obj.setIdCita(Integer.parseInt(e.getComponent().getAttributes().get("mid").toString()));
            msg = dao.eliminar(obj);
        }catch(Exception ae){
            msg = "Error Al Eliminar";
        }
        return msg;
    }
    public String guardar(){
        try{
            Citas objc = new Citas();
            Medico objm = new Medico();
            objm.setIdMedico(getIdMedico());
            Paciente objp = new Paciente();
            objp.setIdPaciente(getIdPaciente());

            objc.setIdCita(getIdCita());
            objc.setHoraCita(getFechaCita());
            objc.setIdMedico(objm);
            objc.setIdPaciente(objp);
            if(getEstado()==0){
                setMsg(dao.guardar(objc));
            }else{
                setMsg(dao.actualizar(objc));
            }
            
        }catch(Exception e){
            setMsg("Error: "+e.getMessage());
        }
        return getMsg();
    }
    public int getIdCita() {
        return idCita;
    }

    /**
     * @param idCita the idCita to set
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    /**
     * @return the idPaciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the idMedico
     */
    public int getIdMedico() {
        return idMedico;
    }

    /**
     * @param idMedico the idMedico to set
     */
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * @return the horaCita
     */
    public String getHoraCita() {
        return horaCita;
    }

    /**
     * @param horaCita the horaCita to set
     */
    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    /**
     * @return the citaModelo
     */
    public DataModel getCitaModelo() {
        citaModelo = new ListDataModel<Citas>(dao.lista());
        return citaModelo;
    }

    /**
     * @param citaModelo the citaModelo to set
     */
    public void setCitaModelo(DataModel citaModelo) {
        this.citaModelo = citaModelo;
    }

    /**
     * @return the itemsP
     */


    /**
     * @return the medicoModeloConsulta
     */
    public DataModel getMedicoModeloConsulta() {
        Citas objc = new Citas();
        Medico objm = new Medico();
   

        objm.setIdMedico(getIdMedico());
        objc.setIdMedico(objm);
        
        
        medicoModeloConsulta = new ListDataModel<Citas>(dao.consultaM(objc,getHoraCita()));
        return medicoModeloConsulta;
    }

    /**
     * @param medicoModeloConsulta the medicoModeloConsulta to set
     */
    public void setMedicoModeloConsulta(DataModel medicoModeloConsulta) {
        this.medicoModeloConsulta = medicoModeloConsulta;
    }

    /**
     * @return the itemsP
     */
    public ArrayList<SelectItem> getItemsP() {//es recomendable dejar los items  en la clase propia en este caso la clase pacientejsf pero como no la tenemos y es un ejemplo la indicamos aca
        pacienteDao daoP = new pacienteDao();
        itemsP = new ArrayList<SelectItem>();
        for(Paciente m : daoP.lista()){
            itemsP.add(new SelectItem(m.getIdPaciente(),m.getNombrePaciente()));
        }
        return itemsP;
    }

    /**
     * @param itemsP the itemsP to set
     */
    public void setItemsP(ArrayList<SelectItem> itemsP) {
        this.itemsP = itemsP;
    }

    /**
     * @return the fechaCita
     */
    public Date getFechaCita() {
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.S");//para formatear la fecha de string a date con el formato indicado dia/mes/año hora minito segundo
            fechaCita = df.parse(getHoraCita());//convertimos el estring a fecha si tiene el formato indicado
        }catch(Exception e){
            System.out.println();
        }
        return fechaCita;
    }

    /**
     * @param fechaCita the fechaCita to set
     */
    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
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
     * @return the valorCita
     */
    public int getValorCita() {
        return valorCita;
    }

    /**
     * @param valorCita the valorCita to set
     */
    public void setValorCita(int valorCita) {
        this.valorCita = valorCita;
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
    
}
