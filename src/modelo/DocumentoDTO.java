/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Profesor
 * id
 */
public class DocumentoDTO {
    
    /**
    * Atributos de la clase 
    * @param id Consecutivo del documento
    * @param texto Texto original del documento
    * @param textoPreprocesado Texto preprocesado del documento después de limpiarlo
    * @param ArrayList<PalabraEtiquetadaDTO> Listado de palabras del texto que contiene el lema, categoria y palabra original

    */
    
    private int id;
    private String texto;
    private String textoPreprocesado;
    private String estado;
    private int estado2;
    private ArrayList<PalabraEtiquetadaDTO> palabrasEtiquetadas;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getEstado2() {
        return estado2;
    }

    public void setEstado2(int estado2) {
        this.estado2 = estado2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTextoPreprocesado() {
        return textoPreprocesado;
    }

    public void setTextoPreprocesado(String textoPreprocesado) {
        this.textoPreprocesado = textoPreprocesado;
    }

    public ArrayList<PalabraEtiquetadaDTO> getPalabrasEtiquetadas() {
        return palabrasEtiquetadas;
    }

    public void setPalabrasEtiquetadas(ArrayList<PalabraEtiquetadaDTO> palabras) {
        this.palabrasEtiquetadas = palabras;
    }

    @Override
    public String toString() {
        return "TweetDTO{" + "id=" + id + ", texto=" + texto + ", textoPreprocesado=" + textoPreprocesado + ", palabras=" + palabrasEtiquetadas + '}';
    }


    
   
     
}
