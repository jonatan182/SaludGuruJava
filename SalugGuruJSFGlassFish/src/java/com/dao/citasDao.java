/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entityes.Citas;
import com.entityes.Medico;
import com.services.citaImplementacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class citasDao implements citaImplementacion{
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
    public List<Citas> lista() {
        List<Citas>list=null;
        try{
            open();
            Query q;
            q = em.createNamedQuery("Citas.findAll");
            list = q.getResultList();
            close();
        }catch(Exception e){
            System.out.println("Errorrrrrrrrrrrrrr:"+e.getMessage());
        }
        return list;
    }

    @Override
    public String guardar(Citas obj) {
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
            mesg="Error Al Guardar: "+e;
            close();
        }
        return mesg;
    }
        @Override
    public List<Citas> consultaM(Citas obj, String fecha){
        List<Citas> list = null;
        try {
            open();
            Query q;
            q = em.createNamedQuery("consultaMedico");
            q.setParameter(1, obj.getIdMedico().getIdMedico());
            q.setParameter(2, fecha);
            list = q.getResultList();
            close();
        } catch (Exception e) {
            close();
        }
        return list;
        
    }

    @Override
    public String eliminar(Citas obj) {
        String mesg="";
        Citas objc;
        try{
            open();
            em.getTransaction().begin();
            objc = em.find(Citas.class, obj.getIdCita());
            if(objc != null){
                em.remove(objc);
                em.getTransaction().commit();
                mesg="Se Ha Eliminado Correctamente";
            }
            em.close();
        }catch(Exception e){
            em.close();
            em.getTransaction().rollback();
            mesg = "Error Al Eliminar";
        }
        return mesg;
    }

    @Override
    public String actualizar(Citas obj) {
        String mesg=null;
        try{
            open();
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mesg = "Se Actualizo Correctamente !!";
            em.close();
        }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            mesg = "Error Al Actualizar";
        }
        return mesg;
    }

    @Override
    public Citas buscar(Citas obj) {
        Citas objc = null;
        try{
            open();
            em.getTransaction().begin();
            objc = em.find(Citas.class,obj.getIdCita());
            em.getTransaction().commit();
            em.close();
        }catch(Exception e){
            em.close();
            em.getTransaction().rollback();
        }
        return objc;
    }
    
}
