/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.logica;

import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class Usuarios {
    
    private ArrayList<Usuario> usuarios = new ArrayList();
    

    public ArrayList<Usuario> getUsuarioList() {
        return usuarios;
    }

    public void setUsuarioList(ArrayList<Usuario> usuarioList) {
        this.usuarios = usuarioList;
    }
   
    
     public void agregarUsuario (Usuario usuario){
    
    this.usuarios.add(usuario);
    }
    
}
