/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.HashSet;


/**
 *
 * @author Usuario
 */
public class ElementoCaracteristicaMorfologica {
    private int idDocumento;
    private ArrayList<String> nombresCaracteristicas;
    private ArrayList<Integer> pesosCaracteristicas;

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public ArrayList<String> getNombresCaracteristicas() {
        return nombresCaracteristicas;
    }

    public void setNombresCaracteristicas(ArrayList<String> nombresCaracteristicas) {
        this.nombresCaracteristicas = nombresCaracteristicas;
    }

    public ArrayList<Integer> getPesosCaracteristicas() {
        return pesosCaracteristicas;
    }

    public void setPesosCaracteristicas(ArrayList<Integer> pesosCaracteristicas) {
        this.pesosCaracteristicas = pesosCaracteristicas;
    }

    @Override
    public String toString() {
        return "ElementoCaracteristicaMorfologica{" + "idDocumento=" + idDocumento + ", nombresCaracteristicas=" + nombresCaracteristicas + ", pesosCaracteristicas=" + pesosCaracteristicas + '}';
    }



    
    
}
