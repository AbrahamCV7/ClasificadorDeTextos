/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author Profesor
 */
public class PalabraEtiquetadaDTO {
   private String  palabra;
    private String categoria;
    private String lema;
    

    public PalabraEtiquetadaDTO(String palabra, String categoria, String lema) {
        this.palabra = palabra;
        this.categoria = categoria;
        this.lema = lema;
    }

    public PalabraEtiquetadaDTO() {
    }

    
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    @Override
    public String toString() {
        return "Token{" + "palabra=" + palabra + ", categoria=" + categoria + ", lema=" + lema + '}';
    }
    
    
    
}
