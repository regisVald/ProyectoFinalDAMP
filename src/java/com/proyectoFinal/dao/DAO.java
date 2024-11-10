/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoFinal.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author CITYLAB
 */
public class DAO {
    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    public void Conectar() throws Exception{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProyectoFinal?user=root&password=123&useUnicode=true&characterEncoding=UTF-8");
        }catch(Exception e){
            throw e;
        }
    }
    public void Cerrar() throws Exception{
        try{
             if(cn != null){
            if(cn.isClosed() == false){
                cn.close();
            }
        }
        }catch(Exception e){
            throw e;

        }
       
    }
}
