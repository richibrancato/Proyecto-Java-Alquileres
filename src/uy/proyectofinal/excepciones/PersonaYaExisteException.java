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
public class PersonaYaExisteException extends Exception {

    /**
     * Creates a new instance of <code>PersonaYaExisteException</code> without
     * detail message.
     */
    public PersonaYaExisteException() {
    }

    /**
     * Constructs an instance of <code>PersonaYaExisteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PersonaYaExisteException(String msg) {
        super(msg);
    }
}
