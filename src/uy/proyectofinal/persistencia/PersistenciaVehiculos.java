/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.persistencia;

/**
 *
 * @author Richard
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.proyectofinal.excepciones.PersistenciaException;
import uy.proyectofinal.excepciones.UsuarioException;
import uy.proyectofinal.logica.Usuario;
import uy.proyectofinal.logica.Usuarios;
//import uy.proyectoFinal.Logica.Vehiculos;
import uy.proyectofinal.logica.Vehiculo;
import uy.proyectofinal.logica.Vehiculos;

public class PersistenciaVehiculos {

    public static final String PS_SELECT_VEHICULOS = "SELECT *FROM vehiculos";
    public static final String PS_INSERT_VEHICULOS = "INSERT INTO vehiculos (padron,patente,marca,modelo,kilometros,cantidadAsientos,precio,disponibilidad) VALUES (?,?,?,?,?,?,?,?)";
    public static final String PS_DELETE_VEHICULO = "DELETE FROM vehiculos WHERE marca = ?";
   
    public void guardarVehiculo(Vehiculo vehiculo) throws PersistenciaException, SQLException {
        PreparedStatement ps;
        Conexion cn = new Conexion();
        //Connection conexion = cn.Conexion;
        Connection conexion = cn.conectar();
        try {
            ps = conexion.prepareStatement(PS_INSERT_VEHICULOS);
            //ps.setInt(1, v.getIdVehiculos());
            //ps.setString(2, vehiculo.getIdTipoCategoria());
            ps.setString(1, vehiculo.getPadron());
            ps.setString(2, vehiculo.getPatente());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getModelo());
            ps.setString(5, vehiculo.getKilometros());
            ps.setString(6, vehiculo.getCantidadAsientos());
            ps.setString(7, vehiculo.getPrecio());
            ps.setString(8, vehiculo.getDisponibilidad());
            ps.executeUpdate();
            //ps.close();

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()); 
            //throw new PersistenciaException(e.getMessage());
         
        }finally{
            //ps.close();
        }
    }

    public Vehiculos obtenerVehiculos() throws UsuarioException {

        PreparedStatement ps;
        ResultSet rs;

        Vehiculos listaVehiculos = new Vehiculos();
        //primer paso obtener la conexion

        Conexion conexion = new Conexion();

        Connection con = null;
        try {

            con = conexion.conectar();
            ps = con.prepareStatement(PS_SELECT_VEHICULOS);
            rs = ps.executeQuery();

            while (rs.next()) {

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setPadron(rs.getString("padron"));
                vehiculo.setPatente(rs.getString("patente"));
                vehiculo.setKilometros(rs.getString("kilometros"));
                vehiculo.setCantidadAsientos(rs.getString("cantidadAsientos"));
                vehiculo.setPrecio(rs.getString("precio"));
                vehiculo.setFecha(rs.getString("fecha"));
                vehiculo.setDisponibilidad(rs.getString("disponibilidad"));

                listaVehiculos.agegarVehiculos(vehiculo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaVehiculos.class.getName()).log(Level.SEVERE, null, ex);

        } catch (PersistenciaException ex) {
            throw new UsuarioException("No pude obtener un vehiculo");

        } finally {

           
        }
        return listaVehiculos;
    }
    
    public void borrarVehiculo(String marca) throws PersistenciaException, SQLException {
        PreparedStatement ps = null;
        Conexion con = new Conexion();
        try {
            Connection conexion = con.conectar();
            ps = conexion.prepareStatement(PS_DELETE_VEHICULO);
            ps.setString(1, marca);
            ps.executeUpdate();
           
            //conexion.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            //throw new SQLException("No existe el Vehiculo");
        } finally {
             ps.close();
        }
    }
}
