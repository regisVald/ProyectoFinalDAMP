package com.proyectoFinal.bean;

import com.proyectoFinal.dao.PersonaDAO;
import com.proyectoFinal.model.Persona;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PersonaBean implements Serializable {
    private Persona persona = new Persona();
    private List<Persona> lstPersonas;

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void registrar() throws Exception {
        PersonaDAO dao;
        try {
            dao = new PersonaDAO();
            dao.registrar(persona);
            this.listar();            
                        
        } catch (Exception e) {
            throw e;
        }
    }
    public void listar() throws Exception {
        PersonaDAO dao;
        try {
            dao = new PersonaDAO();
            lstPersonas = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void leerID(Persona per) throws Exception{
        PersonaDAO dao;
        Persona temp;
        try {
            dao = new PersonaDAO();
            temp = dao.leerID(per);
            if(temp != null){
                this.persona = temp;
            }
        } catch (Exception e) {
            throw e;
        }
    }
   
    public void modificar() throws Exception {
        PersonaDAO dao;
        try {
            dao = new PersonaDAO();
            dao.modificar(persona);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Persona per) throws Exception {
        PersonaDAO dao;
        try {
            dao = new PersonaDAO();
            dao.eliminar(per);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
}
