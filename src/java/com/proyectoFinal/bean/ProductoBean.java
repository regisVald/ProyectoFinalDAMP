package com.proyectoFinal.bean;

import com.proyectoFinal.dao.ProductoDAO;
import com.proyectoFinal.model.Producto;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductoBean implements Serializable {
    private Producto producto = new Producto();
    private List<Producto> lstProductos;
        private String act;

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.limpiar();
        this.act = act;
    }
    
    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        this.producto.setCodigo(0);
        this.producto.setNombre("");
        this.producto.setPrecio(0);
    }
    public void registrar() throws Exception {
        ProductoDAO dao;
        try {
            dao = new ProductoDAO();
            dao.registrar(producto); 
            this.listar();            
        } catch (Exception e) {
            throw e;
        }
    }
    
        private void modificar() throws Exception {
        ProductoDAO dao;
        try {
            dao = new ProductoDAO();
            dao.modificar(producto);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
        
    public void listar() throws Exception {
        ProductoDAO dao;
        try {
            dao = new ProductoDAO();
            lstProductos = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void leerID(Producto pro) throws Exception{
        ProductoDAO dao;
        Producto temp;
        try {
            dao = new ProductoDAO();
            temp = dao.leerID(pro);
            if(temp != null){
                this.producto = temp;
                this.act = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
   
    public void eliminar(Producto pro) throws Exception {
        ProductoDAO dao;
        try {
            dao = new ProductoDAO();
            dao.eliminar(pro);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
}
