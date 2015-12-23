/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entityes.Paciente;
import com.services.pacienteImplementacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jonatan
 */
public class pacienteDao implements pacienteImplementacion{

    private EntityManagerFactory emf;
    private EntityManager em;
    
    public void open(){
        emf = Persistence.createEntityManagerFactory("SalugGuruJSFGlassFishPU");
        em = emf.createEntityManager();
    }
    public void close(){
        em.close();
        emf.close();
    }
    
    @Override
    public List<Paciente> lista() {
        List<Paciente> list= null;
        try{
            open();
            Query q;
            q = em.createQuery("SELECT p FROM Paciente p");
            list = q.getResultList();
         
        }catch(Exception e){
            close();
        }
        return list;
    }

    @Override
    public String guardar(Paciente obj) {
        String mesg="";
        try{
            open();
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mesg="Se Guardo Satisfactoriamente";
            close();
        }catch(Exception e){
            em.getTransaction().rollback();
            mesg="Error Al Guardar";
            close();
        }
        return mesg;
    }
    
}
