/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.proyectofinal.logica;

import java.util.ArrayList;
//import uy.proyectofinal.logica.Vehiculo;
/**
 *
 * @author Richard
 */
public class Vehiculos {
    private ArrayList<Vehiculo> vehiculos = new ArrayList();
    
    public ArrayList<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    public void setVehiculos(ArrayList<Vehiculo> vehiculos){
        this.vehiculos = vehiculos;        
    }
    public void agegarVehiculos (Vehiculo vehiculo){
        this.vehiculos.add(vehiculo);
    }
}
