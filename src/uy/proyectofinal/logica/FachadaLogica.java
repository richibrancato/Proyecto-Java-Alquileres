/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.logica;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.proyectofinal.excepciones.PersistenciaException;
import uy.proyectofinal.excepciones.PersonaException;
import uy.proyectofinal.excepciones.PersonaYaExisteException;
import uy.proyectofinal.excepciones.UsuarioException;
import uy.proyectofinal.excepciones.UsuarioYaExisteException;
import uy.proyectofinal.excepciones.VehiculosException;
import uy.proyectofinal.persistencia.PersistenciaPersona;
import uy.proyectofinal.persistencia.PersistenciaVehiculos;
import uy.proyectofinal.persistencia.PersistenciaUsuario;

/**
 *
 * @author Richard
 */
public class FachadaLogica {
    PersistenciaPersona persistenciaPersona = new PersistenciaPersona();
    PersistenciaUsuario persistenciaUsuario = new PersistenciaUsuario();
    //ALTA VEHICULO

    public void ingresarVehiculo(Vehiculo v) throws PersistenciaException, SQLException {
        //Vehiculo veh = new Vehiculo();
        PersistenciaVehiculos persist = new PersistenciaVehiculos();
        persist.guardarVehiculo(v);

    }
    public Vehiculos obtenerVehiculos() throws VehiculosException, UsuarioException {

        PersistenciaVehiculos persVehiculos = new PersistenciaVehiculos();

        Vehiculos vehiculos = persVehiculos.obtenerVehiculos();

        return vehiculos;

    }
    public void bajaVehiculos(Vehiculo vehiculo) throws PersistenciaException, SQLException {
        PersistenciaVehiculos pVehiculos = new PersistenciaVehiculos();
        try {
            pVehiculos.borrarVehiculo(vehiculo.getMarca());
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getMessage());

        }catch (SQLException ex){
            throw new PersistenciaException(ex.getMessage());        }
    }
    

    public boolean existePersona(String tipodoc, String nrodoc) throws PersistenciaException, PersonaYaExisteException {
        try {
            return (persistenciaPersona.existePersona_por_docum(tipodoc, nrodoc));
        } catch (PersonaYaExisteException ex) {
            throw new PersonaYaExisteException("Ya existe la Persona");
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error en la conexion");
        }

    }

    //ADMIN
    public boolean existeAdmin(Usuario usuario) {
        Boolean usuarioAdmin = false;
        PersistenciaUsuario pAdmin = new PersistenciaUsuario();
        try {
            usuarioAdmin = pAdmin.existeAdmin(usuario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FachadaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioAdmin;
    }
    
    public boolean existeUsuario(String nomusuario) {
        try {
            return (persistenciaUsuario.existeUsuarioxNom(nomusuario));
        } catch (PersistenciaException e) {
            return false;
        }
    }
    public void modificarUsuario(Usuario usuario) throws PersistenciaException {
        PersistenciaUsuario puser = new PersistenciaUsuario();
        try {
            puser.modificarUsuario(usuario);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getMessage());
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void altaUsuario(Usuario usuario) throws PersistenciaException, UsuarioYaExisteException, SQLException{
        PersistenciaUsuario puser = new PersistenciaUsuario();
        try {
            puser.guardarUsuario(usuario);
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getMessage());
        } catch (UsuarioYaExisteException ex) {
            throw new UsuarioYaExisteException(ex.getMessage());
        }catch (SQLException ex2) {
            throw new SQLException(ex2.getMessage());
        }
    }

    public void bajaUsuario(Usuario usuario) throws PersistenciaException, SQLException {
        PersistenciaUsuario puser = new PersistenciaUsuario();
        try {
            puser.borrarUsuario(usuario.getNomUsuario());
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getMessage());

        }catch (SQLException ex){
            throw new PersistenciaException(ex.getMessage());        }
    }

    public Boolean validarIngreso(Usuario usuario) throws UsuarioException {
        Boolean usuarioValido = false;
        PersistenciaUsuario persUsu = new PersistenciaUsuario();
        try {
            usuarioValido = persUsu.validarIngreso(usuario);
        } catch (PersistenciaException ex) {
            throw new UsuarioException(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(FachadaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarioValido;

    }

    public void ingresarPersona(Persona persona) throws PersonaException {
        PersistenciaPersona persPersona = new PersistenciaPersona();
        try {
            persPersona.guardarPersona(persona);
        } catch (PersistenciaException ex) {
            throw new PersonaException("Fallo el ingreso de la persona");
        } catch (PersonaYaExisteException ex) {
            Logger.getLogger(FachadaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Usuarios obtenerUsuarios() throws UsuarioException {

        try {
            PersistenciaUsuario persUsuario = new PersistenciaUsuario();
            
            Usuarios usuarios = persUsuario.obtenerUsuarios();
            
            return usuarios;
        } catch (PersistenciaException ex) {
            Logger.getLogger(FachadaLogica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FachadaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
