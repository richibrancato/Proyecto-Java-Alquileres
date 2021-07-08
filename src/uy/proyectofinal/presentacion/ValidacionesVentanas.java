/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.presentacion;

import javax.swing.JOptionPane;

/**
 *
 * @author Richard
 */
public class ValidacionesVentanas {

    
    public Boolean usuarioValido(String nomusuario) {
        Boolean valido;

        if (nomusuario.length() > 15) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    public Boolean claveValida(String clave) {
        Boolean valido;

        if (clave.length() > 5) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    public Boolean confirmarClave(String clave,String confirmarclave){
        Boolean valido;
        if(clave.equals(confirmarclave)){
            valido = true;
            System.out.println("Las claves son correctas");
        }else{
            valido = false;
            JOptionPane.showMessageDialog(null, "Las Claves no coinciden");
        }
        return valido;
    }
        /*VentanaUsuario ventanaUsuario = new VentanaUsuario();
        Boolean valido;
        
         if (txtClave.getText().trim()equals(txtRepClave.getText().trim())){
            
    }    else{
            JOptionPane.showMessageDialog(null, "Las Claves no coinciden");
            }
        return valido;        
    }*/
    public Boolean largo20(String campo) {
        Boolean valido;

        if (campo.length() > 20) {
            valido = false;
        } else {
            valido = true;
        }
        return valido;
    }

    public Boolean esUnMail(String campo) {
        Boolean valido;
        if (!campo.isEmpty()){
            if (!campo.contains("@")){
                valido = false;
            } else if (!campo.contains(".")){
                    valido = false;
                    } else valido = true;
        }else {
            valido = true;
        }
        return valido;
    }
    public boolean isNumeric(String cadena){
        try {
                Integer.parseInt(cadena);
                return true;
        } catch (NumberFormatException nfe){
                return false;
        }
    }
    
    public boolean documento(String campo){
        ValidacionesVentanas validacion = new ValidacionesVentanas();
        Boolean valido = false;
        
        if (!campo.isEmpty()){
            if (validacion.isNumeric(campo)){
                valido = true;
            }  
        }
        return valido;
    }
}
