/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.bean;

import com.proyectoFinal.dao.VentaDAO;
import com.proyectoFinal.model.DetalleVenta;
import com.proyectoFinal.model.Producto;
import com.proyectoFinal.model.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
//import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
/**
 *
 * @author renes
 */
@ManagedBean(name = "ventaBean")
@ViewScoped
public class VentaBean {
    private Venta venta = new Venta();
    private Producto producto = new Producto();
    private  int cantidad;
    private List<DetalleVenta> lista = new ArrayList();

    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }

  
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    
    public void Agregar(){
        DetalleVenta det = new DetalleVenta();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        this.lista.add(det);
    }
    
    public void registrar()
    {
        VentaDAO dao;
        double monto =0;
        try{
            for(DetalleVenta det : lista )
            {
                monto += det.getProducto().getPrecio();
            }
            dao = new VentaDAO();
            venta.setMonto(monto);
            dao.registrar(venta,lista);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Procesado con exito", "Venta bien procesada"));
        }catch(Exception e){
            
        }
    }    
}
