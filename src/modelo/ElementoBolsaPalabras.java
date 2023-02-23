
package modelo;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Usuario
 */
public class ElementoBolsaPalabras {
    
    private int idTexto;
    private HashSet<String> palabrasDiccionario;
    private ArrayList<Integer> tf;
    private ArrayList<Double> tfidf;

    public int getIdTexto() {
        return idTexto;
    }

    public void setIdTexto(int idTexto) {
        this.idTexto = idTexto;
    }

    public HashSet<String> getPalabrasDiccionario() {
        return palabrasDiccionario;
    }

    public void setPalabrasDiccionario(HashSet<String> palabrasDiccionario) {
        this.palabrasDiccionario = palabrasDiccionario;
    }

    public ArrayList<Integer> getTF() {
        return tf;
    }

    public void setTF(ArrayList<Integer> tf) {
        this.tf = tf;
    }

    public ArrayList<Double> getTDIDF() {
        return tfidf;
    }

    public void setTFIDF(ArrayList<Double> tfidf) {
        this.tfidf = tfidf;
    }

    @Override
    public String toString() {
        return "ElementoBolsaPalabras{" + "idTexto=" + idTexto + ", palabrasDiccionario=" + palabrasDiccionario + ", tf=" + tf + ", tfidf=" + tfidf + '}';
    }
    
    
    
}
