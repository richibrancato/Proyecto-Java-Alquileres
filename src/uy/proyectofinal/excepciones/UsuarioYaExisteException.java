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
public class UsuarioYaExisteException extends Exception {

    /**
     * Creates a new instance of <code>UsuarioYaExiste</code> without detail
     * message.
     */
    public UsuarioYaExisteException() {
    }

    /**
     * Constructs an instance of <code>UsuarioYaExiste</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioYaExisteException(String msg) {
        super(msg);
    }
}