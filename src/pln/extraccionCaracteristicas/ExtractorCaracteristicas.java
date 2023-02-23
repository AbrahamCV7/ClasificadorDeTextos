/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pln.extraccionCaracteristicas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import modelo.*;

/**
 *
 * @author Profesor
 */
public class ExtractorCaracteristicas {
    
    /**
    * @param documentosProcesados Conjunto de tweets procesados
     * @return  retorna el diccionario
     */
    public HashSet<String> crearDicionario(ArrayList<DocumentoDTO> documentosProcesados){
        // creando el diccionario (vocabulario)
        HashSet<String> diccionario = new HashSet<>(); 
        for(DocumentoDTO doc:documentosProcesados){
            for(PalabraEtiquetadaDTO palabraEtiquetada:doc.getPalabrasEtiquetadas())
            {
                diccionario.add(palabraEtiquetada.getLema());
            }
        }
             
    return diccionario;
    }
    
   // Método para crear la bolsa de palabras
    public ArrayList<ElementoBolsaPalabras> crearBolsaPalabras(HashSet<String> diccionario, ArrayList<DocumentoDTO> documentosProcesados) {
        ArrayList<ElementoBolsaPalabras> bolsaPalabras = new ArrayList<ElementoBolsaPalabras>();
       
        //Calcular el vector para cada documento
        for(DocumentoDTO documento : documentosProcesados) {
            ElementoBolsaPalabras elemBolsaPalabras = new ElementoBolsaPalabras();
            ArrayList<Integer> vectorTF = new ArrayList<Integer>();
            ArrayList<Double> vectorTFIDF = new ArrayList<Double>();
            elemBolsaPalabras.setIdTexto(documento.getId());
            elemBolsaPalabras.setPalabrasDiccionario(diccionario);
            //Calcular tf y tfidf
            for(String elemDic:diccionario){
                int tf=calcularFreqTermino(elemDic,documento.getPalabrasEtiquetadas());
                double tfidf= calcularTFIDF(tf,documentosProcesados.size(),elemDic,documentosProcesados); 
                vectorTF.add(tf);
                vectorTFIDF.add(tfidf);
            }
            elemBolsaPalabras.setTF(vectorTF);
            elemBolsaPalabras.setTFIDF(vectorTFIDF);  
            
            bolsaPalabras.add(elemBolsaPalabras);
        } 

    return bolsaPalabras;
    }
 
    // Método para extraer caracteristicas sintacticas y semánticas
    public ArrayList<ElementoCaracteristicaMorfologica> extraerCarcateristicasMorfologicas(ArrayList<DocumentoDTO> documentosProcesados) {
        ArrayList<ElementoCaracteristicaMorfologica> bolsaCaracteristicasMorfologicas = new ArrayList<ElementoCaracteristicaMorfologica>();
    
       
        //Calcular el vector para cada documento
        for(DocumentoDTO documento : documentosProcesados) {
            ElementoCaracteristicaMorfologica elemCaracteristicasMorfologicas = new ElementoCaracteristicaMorfologica();
           ArrayList<Integer> vectorPesosCaracteristicas = new ArrayList<Integer>();
                        
            elemCaracteristicasMorfologicas.setIdDocumento(documento.getId());
            elemCaracteristicasMorfologicas.setNombresCaracteristicas(new ArrayList<String>(Arrays.asList("NumeroVerbos","NumeroNC","NumeroNP")));

                    
            int cantidadVerbos=0,cantidadNC=0, cantidadNP=0;
            for(PalabraEtiquetadaDTO palabraEtiquetada:documento.getPalabrasEtiquetadas()){
                //Contar verbos
                if(palabraEtiquetada.getCategoria().startsWith("V"))
                    cantidadVerbos++;
                //Contar  nombres comunes
                if(palabraEtiquetada.getCategoria().startsWith("NC"))
                    cantidadNC++;
                //Contar nombres propios 
                if(palabraEtiquetada.getCategoria().startsWith("NP"))
                    cantidadNP++;
            }
            vectorPesosCaracteristicas.add(cantidadVerbos);
            vectorPesosCaracteristicas.add(cantidadNC);
            vectorPesosCaracteristicas.add(cantidadNP);
            
            elemCaracteristicasMorfologicas.setPesosCaracteristicas(vectorPesosCaracteristicas);
       
            bolsaCaracteristicasMorfologicas.add(elemCaracteristicasMorfologicas);
        } 

    return bolsaCaracteristicasMorfologicas;
    }
 
    /**
    * Calcula el número de veces que una palabra aparece en una documento (TF) 
    * @param palabra palabra o término de interés 
    * @param documento documento para calcular el número de apariciones
    * @return número de veces que aparece una palabra en una frase 
    */
    private int calcularFreqTermino(String palabra, ArrayList<PalabraEtiquetadaDTO> palabrasEtiquetadasDocumento){
        int contador=0; 
        for(PalabraEtiquetadaDTO palabraDoc :palabrasEtiquetadasDocumento){
                   if (palabra.equals(palabraDoc.getLema()))
                       contador++;
                }
        return contador;
    }
    
    /**
    * Calcula la frecuencia inversa de un termino en una frase
    * @param tf  Número de ocurrencias del termino en la frase
    * @param N  Número total de frases o documentos o textos 
    * @param palabra  paalabra a 
    * @return recuencia inversa de un termino en una frase 
    */
    private double calcularTFIDF(int tf, int N, String palabra, ArrayList<DocumentoDTO> documentos){
        
        //Calcular df
        int df=0;
        for(DocumentoDTO doc:documentos){
            boolean palabraPresente=false;
            for(PalabraEtiquetadaDTO palabraEtiquetadaDoc:doc.getPalabrasEtiquetadas())
            {
                if (palabra.equals(palabraEtiquetadaDoc.getLema())){
                    palabraPresente=true;
                }   
            }
            if(palabraPresente){
               df++; 
            }      
        }
            
        double tfidf= ((double) (tf*(Math.log((double)N/df))));
        return Math.round(tfidf * 1000) / 1000d;

    }


}
