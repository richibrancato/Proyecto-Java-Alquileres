/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.excepciones;

/**
 *
 * @author Richard
 */
public class VehiculosException extends Exception {
    
    public VehiculosException(String mensaje){
        System.out.println("Error al obtener los datos de los autos.");
        
    }

    public VehiculosException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
