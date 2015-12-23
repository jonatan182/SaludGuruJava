/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entityes.Medico;
import com.services.medicoImplementacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jonatan
 */
public class medicoDao implements medicoImplementacion {

    private EntityManagerFactory emf;
    private EntityManager em;
    private String msg = "";

    public void open() {
        emf = Persistence.createEntityManagerFactory("SalugGuruJSFGlassFishPU");
        em = emf.createEntityManager();
    }

    public void close() {
     
    }

    @Override
    public List<Medico> lista() {
        List<Medico> list = null;
        try {
            open();
            Query q;
            q = em.createQuery("SELECT m FROM Medico m");
            list = q.getResultList();
            close();
        } catch (Exception e) {
            System.out.println("Error:************************************************"+e.getMessage());
            close();
        }
        return list;
    }

    @Override
    public String guardar(Medico obj) {
        try {
            open();
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            msg = "Se Guardo Satisfactoriamente";
            close();
        } catch (Exception e) {
            
            msg = "Error Al Guardar: " + e;
            close();
        }
        return msg;
    }

    @Override
    public String eliminar(Medico obj) {
        Medico obj2;
        try {
            open();
            em.getTransaction().begin();
            obj2 = em.find(Medico.class, obj.getIdMedico());
            if (obj2 != null) {
                em.remove(obj2);
                em.getTransaction().commit();
                msg = "Se Elimino CorrectaMente";
                close();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            close();
        }
        return msg;
    }

    @Override
    public Medico buscar(Medico obj) {
        Medico obj2 = null;
        try {
            open();
            em.getTransaction().begin();
            obj2 = em.find(Medico.class, obj.getIdMedico());
            em.getTransaction().commit();
            close();

        } catch (Exception e) {
            em.getTransaction().rollback();
            close();
        }
        return obj2;
    }

    @Override
    public String actualizar(Medico obj) {
        try{
            open();
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            msg="Se Actualizo Satisfactoriamente";
            close();
        }catch(Exception e){
            msg="NO Se Pudo Actualizar";
            em.getTransaction().rollback();
            close();
        }
        return msg;
    }

}
