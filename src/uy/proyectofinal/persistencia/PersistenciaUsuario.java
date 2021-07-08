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
import uy.proyectofinal.excepciones.UsuarioException;
import uy.proyectofinal.excepciones.UsuarioYaExisteException;
import uy.proyectofinal.logica.Usuario;
import uy.proyectofinal.logica.Usuarios;

/**
 *
 * @author Richard
 */
public class PersistenciaUsuario {

    private static final String PS_INSERT_USUARIO = "INSERT INTO usuarios (nomUsuario, clave) VALUES (?, ?)";
    private static final String PS_SELECT_USUARIO = "SELECT * FROM todo_alquileres.usuarios";
    private static final String PS_SELECT_ADMIN = "SELECT * FROM todo_alquileres.usuario where nomUsuario = ? and clave = ?";
    private static final String PS_UPDATE_USUARIO = "UPDATE usuarios SET clave = ?, nomUsuario = ? WHERE idUsuario = ?";
    private static final String PS_DELETE_USUARIO = "DELETE FROM usuarios WHERE nomUsuario = ?";
    private static final String PS_EXISTE_USUARIO = "SELECT * FROM todo_alquileres.usuario where nomUsuario = ? and clave = ?";

    public void guardarUsuario(Usuario usuario) throws PersistenciaException, UsuarioYaExisteException, SQLException {
        //paso 1 : crear la conexion a la base
        //paso 2 : crear el prepare statement
        //paso 3 : ejecutar la consulta del preparestatement
        //paso 4 : cargar los resultados en los objetos de la capa logica
        //paso 5 : cerrar la conexion
        PreparedStatement ps = null;
        Conexion con = new Conexion();

        try {
            Connection conexion = con.conectar();

            ps = conexion.prepareStatement(PS_INSERT_USUARIO);
            ps.setString(1, usuario.getNomUsuario());
            ps.setString(2, usuario.getClave());

            ps.executeUpdate();
            //ps.close();

            //conexion.close();
        } catch (SQLIntegrityConstraintViolationException ex1) {
            throw new UsuarioYaExisteException("Ya existe el usuario, use otro");
        } catch (PersistenciaException e) { //SQLException | 
            throw new PersistenciaException(e.getMessage());
        }catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()); 
        }finally {
            //CERRAR CONEXION
            ps.close();
        }

    }
    public Boolean existeUsuarioxNom(String nomUsuario) throws PersistenciaException {
        PreparedStatement ps;
        ResultSet rs = null;
        Boolean existe = false;

        Conexion con = new Conexion();
        try {
            Connection conexion = con.conectar();
            ps = conexion.prepareStatement(PS_SELECT_USUARIO);
            ps.setString(1, nomUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                String clave = rs.getString("clave");
                existe = true;
            } else {
            }
            ps.close();
            conexion.close();
            return existe;
        } catch (SQLException | PersistenciaException e) {
            throw new PersistenciaException("Error al validar Usuario");
        } finally {

        }
    }
    //ADMIN
    public Boolean existeAdmin(Usuario usuario) throws PersistenciaException {
        PreparedStatement ps;
        ResultSet rs = null;
        Boolean existe = false;

        Conexion con = new Conexion();
        try {
            String nombre = usuario.getNomUsuario();
            String clave = usuario.getClave();
            Connection conexion = con.conectar();
            ps = conexion.prepareStatement(PS_SELECT_ADMIN);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                clave = rs.getString("clave");
                existe = true;
            } else {
            }
            ps.close();
            conexion.close();
            return existe;
        } catch (SQLException | PersistenciaException e) {
            throw new PersistenciaException("Error al validar Usuario");
        } finally {

        }
    }
    

    public void modificarUsuario(Usuario usuario) throws PersistenciaException, SQLException {
        //paso 1 : crear la conexion a la base
        //paso 2 : crear el prepare statement
        //paso 3 : ejecutar la consulta del preparestatement
        //paso 4 : cargar los resultados en los objetos de la capa logica
        //paso 5 : cerrar la conexion
        PreparedStatement ps = null;
        Conexion con = new Conexion();
        try {
            Connection conexion = con.conectar();
            ps = conexion.prepareStatement(PS_UPDATE_USUARIO);
            ps.setString(3, usuario.getIdUsuario());
            ps.setString(2, usuario.getNomUsuario());
            ps.setString(1, usuario.getClave());
            ps.executeUpdate();
            //ps.close();
           // conexion.close();
        } catch (PersistenciaException e) {
            throw new PersistenciaException(e.getMessage());
        }catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }finally {
        ps.close();
        }
        
    }

    public void borrarUsuario(String nomUsuario) throws PersistenciaException, SQLException {
        PreparedStatement ps = null;
        Conexion con = new Conexion();
        try {
            Connection conexion = con.conectar();
            ps = conexion.prepareStatement(PS_DELETE_USUARIO);
            ps.setString(1, nomUsuario);
            ps.executeUpdate();
           
            //conexion.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            //throw new SQLException("No existe el Usuario");
        } finally {
             ps.close();
        }
    }

    public Boolean validarIngreso(Usuario usuario) throws PersistenciaException, SQLException {

        Boolean usuarioValido = false;
        PreparedStatement ps = null;
        //PASO 1 
        //Conexion conexion = new Conexion();
        //Connection con = conexion.conectar();
        Conexion con = new Conexion();
        try {
            //PASO 2 

            String nombre = usuario.getNomUsuario();
            String clave = usuario.getClave();
            Connection conexion = con.conectar();
            String sqlStm = "select * from todo_alquileres.usuarios where nomUsuario='" + nombre + "' and clave='" + clave + "';";
            //ps = con.prepareStatement(sqlStm);
            ps = conexion.prepareStatement(sqlStm);
            //PASO 3 
            ResultSet rs = ps.executeQuery();

            //PASO 4
            while (rs != null && rs.next()) {

                usuarioValido = true;

            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Error al validar el usuario ingresado");
        } finally {
            //PASO 5 cerrrar la conexion
            //conexion.cerrarConectar(con);//PREGUNTAR PORQUE DA ERROR ESTE METODO
            ps.close();
        }
        return usuarioValido;

    }

    public Usuarios obtenerUsuarios() throws UsuarioException, PersistenciaException, SQLException {

        PreparedStatement ps = null;
        ResultSet rs;

        Usuarios listaUsuarios = new Usuarios();
        //primer paso obtener la conexion

        //Conexion conexion = new Conexion();
        Conexion con = new Conexion();
        //Connection con = null;
        try{
           // Conexion conexion = new Conexion();
            //con = conexion.conectar();
            Connection conexion = con.conectar();
            ps = conexion.prepareStatement(PS_SELECT_USUARIO);
            rs = ps.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getString("idUsuario"));
                usuario.setNomUsuario(rs.getString("nomUsuario"));
                usuario.setClave(rs.getString("clave"));
                listaUsuarios.agregarUsuario(usuario);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaUsuario.class.getName()).log(Level.SEVERE, null, ex);

        } catch (PersistenciaException ex) {
            throw new UsuarioException("No pude obtener un usuario");

        } finally{
            ps.close();
            
           //conexion.cerrarConectar(con);

        }
        return listaUsuarios;
    }
}
