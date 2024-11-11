package com.proyectoFinal.bean;

import com.proyectoFinal.dao.PersonaDAO;
import com.proyectoFinal.model.Persona;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class PersonaBean implements Serializable {
    private Persona persona = new Persona();
    private List<Persona> lstPersonas;
        private String act;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.limpiar();
        this.act = act;
    }
    
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
    
    private boolean isPostBack(){
        boolean res;
        res = FacesContext.getCurrentInstance().isPostback();
        return res;
    }
    
    public void operar() throws Exception{
        switch(act){
            case "Registrar":
                this.registrar();
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }
    
    public void limpiar(){
        this.persona.setCodigo(0);
        this.persona.setNombre("");
        this.persona.setSexo("");
    }
    public void registrar() throws Exception {
        PersonaDAO dao;
        try {
            dao = new PersonaDAO();
            dao.registrar(persona); 
            this.listar("V");            
        } catch (Exception e) {
            throw e;
        }
    }
    
        private void modificar() throws Exception {
        PersonaDAO dao;
        try {
            dao = new PersonaDAO();
            dao.modificar(persona);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }
    
        
    public void listar(String val) throws Exception {
        PersonaDAO dao;
        try {
            if (val.equals("F")){
                 if(isPostBack() == false){
                dao = new PersonaDAO();
                lstPersonas = dao.listar();
           }
            }else{
                dao = new PersonaDAO();
                lstPersonas = dao.listar();
            }

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
                this.act = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
   
    public void eliminar(Persona per) throws Exception {
        PersonaDAO dao;
        try {
            dao = new PersonaDAO();
            dao.eliminar(per);
            this.listar("V");
        } catch (Exception e) {
            throw e;
        }
    }
}
