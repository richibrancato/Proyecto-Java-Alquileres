/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.proyectofinal.excepciones.PersistenciaException;

public class Conexion {

    private String nombreDB = "todo_alquileres";
    private String usuario = "root";
    private String password = "1234";
    private String url = "jdbc:mysql://localhost:3306/" + nombreDB + "?serverTimezone=UTC";
    Connection con;
    Connection Conexion;
    //CONTRUCTOR DE CLASE
    public Connection conectar() throws PersistenciaException {
        
        try {
            //OBTENER EL DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, usuario, password);
            if (con != null) {
                //System.out.println("Conexion a BASE DE DATOS Exitosa! \n" + "Connecting database [" + con + "] ok");
                System.out.println("Conexion a BASE DE DATOS Exitosa! \n" + "Connecting database [" + nombreDB + "] ok");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se encontro el driver de BASE DE DATOS");
           /* } catch (InstantiationException exInst){
            throw new PersistenciaException("Acceso ilegal a la base de datos");
         } catch (IllegalAccessException exIllegal){
            
             //throw new PersistenciaException("Acceso ilegal a la BASE DE DATOS");*/
        } catch (SQLException eSql) {
            //System.out.println("REVISA LA BASE DE DATOS");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, eSql);
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
        return con;
    }
        //ESTE METODO ME DA ERROR//
        public void cerrarConectar (Connection con) throws PersistenciaException{
            try {
                con.close();
            }catch (SQLException ex){
                System.err.println("Error:" + ex);
                //throw new PersistenciaException("ERROR AL CERRAR LA CONEXION");
            }
        }
    }

   
