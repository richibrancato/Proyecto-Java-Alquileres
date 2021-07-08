/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.proyectofinal.excepciones.PersistenciaException;
import uy.proyectofinal.excepciones.PersonaYaExisteException;
import uy.proyectofinal.logica.Persona;

/**
 *
 * @author Richard
 */
public class PersistenciaPersona{

    private static final String PS_INSERT_PERSONA = "INSERT INTO personas (tipodocumento, nrodocumento, apellido1, apellido2, nombre1, nombre2, calle, nropuerta, apto, departamento, telefono, celular, email, nomusuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String PS_SELECT_PERSONA_POR_DOCUM = "SELECT tipodocumento, nrodocumento, apellido1,apellido2,nombre1, nombre2, calle, nropuerta, apto, departamento, telefono, celular, email, nomusuario FROM Persona WHERE tipodocumento = ? and nrodocumento = ?";
//    private static final String PS_UPDATE_USUARIO = "UPDATE usuario SET clave = ? WHERE nomUsuario = ?";
      

    public void guardarPersona(Persona persona) throws PersonaYaExisteException, PersistenciaException {
        
        //paso 1 : crear la conexion a la base
        //paso 2 : crear el prepare statement
        //paso 3 : ejecutar la consulta del preparestatement
        //paso 4 : cargar los resultados en los objetos de la capa logica
        //paso 5 : cerrar la conexion

        PreparedStatement ps; /*= new PreparedStatement();*/
        Conexion con = new Conexion();
        Connection conexion;
        
        try {
            conexion = con.conectar();
         
            ps = conexion.prepareStatement(PS_INSERT_PERSONA);
            
            ps.setString(1, persona.getTipoDocumento());
            ps.setString(2, persona.getNrodocumento());
            ps.setString(3, persona.getApellido1());
            ps.setString(4, persona.getApellido2());
            ps.setString(5, persona.getNombre1()); 
            ps.setString(6, persona.getNombre2());            
            ps.setString(7, persona.getCalle());
            ps.setString(8, persona.getNroPuerta());
            ps.setString(9, persona.getApto());
            ps.setString(10, persona.getDepartamento()); 
            ps.setString(11, persona.getTelefono());
            ps.setString(12, persona.getCelular());    
            ps.setString(13, persona.getEmail());
            ps.setString(14, persona.getUsuario().getNomUsuario());           
            
            ps.executeUpdate();
            
           
        } catch (SQLIntegrityConstraintViolationException ex1){
            throw new PersonaYaExisteException("Indice duplicado");
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getMessage());
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()); 
        }/*finally {
            try {
                ps.close();
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersistenciaPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }*/
        
    }

    public Boolean existePersona_por_docum(String tipoDoc, String nroDoc) throws PersistenciaException, PersonaYaExisteException {
        boolean existe = false;
        PreparedStatement ps;
        ResultSet rs;
        Conexion con = new Conexion();
        try {
            Connection conexion = con.conectar();
            ps = conexion.prepareStatement(PS_SELECT_PERSONA_POR_DOCUM);
            
            ps.setString(1, tipoDoc);
            ps.setString(2, nroDoc);
            rs = ps.executeQuery();
            while (rs.next()) {
                existe = true;
            } 
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            throw new PersonaYaExisteException(e.getMessage());
        } finally {


        }        
   
        return true;
        
    }

}
