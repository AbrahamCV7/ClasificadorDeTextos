/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import modelo.DocumentoDTO;
import modelo.ElementoBolsaPalabras;
import modelo.ElementoCaracteristicaMorfologica;
import org.annolab.tt4j.TreeTaggerException;
import pln.etiquetado.EtiquetadorTree;
import pln.extraccionCaracteristicas.ExtractorCaracteristicas;
import pln.preprocesado.Preprocesado;
import pln.utilerias.ArchivoES;


/**
 *
 * 
 */
public class PipelinePLN {


     
    public static void main(String[] args) throws IOException, TreeTaggerException {
                 
        // Nombre de los archivos y rutas donde se leera y escribirá la información 
        String ARCHIVOS_TEXTOS = "datos/entrada";
        String ARCHIVO_TFIDF = "datos/salida/textosPesadosTFIDF.csv";
        String ARCHIVO_MORFOLOGICO = "datos/salida/textosPesadoMorfologico.csv";
        
       // Delcaración de objetos para cada proceso del pipeline PLN 
       //Se crea un objeto ArchivoEs donde se tienen metodos para leer archivo y escribir lineas
        ArchivoES archivoEntrada= new ArchivoES();
        //Objeto Preprocesado para limpieza
        Preprocesado preprocesado= new Preprocesado();
        
        //Objeto para extraccion de caracteristicas, bolsa de palabaras, pesado, caracteristicas morfologicas
        ExtractorCaracteristicas extractorCar = new ExtractorCaracteristicas();
        
        ArrayList<DocumentoDTO> documentosProcesados = new ArrayList<DocumentoDTO>();
            
        //Leer textos de una carpeta y los almacena en un arreglo de documentos     
        ArrayList<DocumentoDTO> documentos = archivoEntrada.leerArchivosTexto(ARCHIVOS_TEXTOS);
        //Imprime resultados de la lectura incial de textos
        System.out.println("Numero de documentos iniciales: "+documentos.size());
 
        //Preprocesa cada texto eliminacion de ruido, etiquetado y lematizado
        for(DocumentoDTO documento : documentos) {
             //Imprime cada documento original 
            System.out.println(documento.getTexto());
           
            // Pre-procesar los textos (limpieza)
            DocumentoDTO documentoProcesado = preprocesado.limpiarTexto(documento);
          
            //Etiquetado POS con TreeTagger
             documentoProcesado.setPalabrasEtiquetadas(EtiquetadorTree.etiquetar(documentoProcesado.getTextoPreprocesado().split(" ")));   
           
             // Imprime cada texto limpio, lematizado y etiquetado
             System.out.println(documentoProcesado.getPalabrasEtiquetadas().toString());
             
             documentosProcesados.add(documentoProcesado);
        } // termina etapa de procesamiento
        
        System.out.println("\n...........Extracción carcaterísticas estadísticas ...........\n");
        //Crear diccionario
        HashSet<String> diccionario =extractorCar.crearDicionario(documentosProcesados);
        System.out.println("Cantidad de palabras del diccionario: "+diccionario.size());
        
        ArrayList<ElementoBolsaPalabras> bolsaPalabrasTFIDF=extractorCar.crearBolsaPalabras(diccionario,documentosProcesados);
        
        //Imprimir matriz de pesos TF-IDF
        System.out.println(diccionario.toString());
        for(ElementoBolsaPalabras elementoBolsaPalabras:bolsaPalabrasTFIDF){
            System.out.println(elementoBolsaPalabras.getTDIDF());
        }
        
        // Guardar salida en archivo TFIDF
        guardarPesadoTFIDF(bolsaPalabrasTFIDF,ARCHIVO_TFIDF,documentos);
        
        System.out.println("\n...........Extracción carcaterísticas morfologicas ...........\n");
        
        //Extrayendo las carcaterísticas morfológicas
        ArrayList<ElementoCaracteristicaMorfologica> bolsaCaracteristicasMorfologicas=extractorCar.extraerCarcateristicasMorfologicas(documentosProcesados);
        
        //Imprimir matriz de pesos características morfológicas
         System.out.println(bolsaCaracteristicasMorfologicas.get(0).getNombresCaracteristicas().toString());
        for(ElementoCaracteristicaMorfologica elementoBolsaCaracteristicasMorfologicas:bolsaCaracteristicasMorfologicas){
            System.out.println(elementoBolsaCaracteristicasMorfologicas.getPesosCaracteristicas().toString());
        }
             // Guardar salida en archivo Carcateristicas Mortfologicas
        guardarMorfologico(bolsaCaracteristicasMorfologicas,ARCHIVO_MORFOLOGICO,documentos);
        
        System.out.println("¡...Archivo guardado....!");
    }
    
     private static void guardarPesadoTFIDF(ArrayList<ElementoBolsaPalabras> bolsaPalabrasTFIDF, String ARCHIVO_TFIDF,ArrayList<DocumentoDTO> documentos){
        ArchivoES archivoSalidaTFIDF= new ArchivoES();
         // Escribe la cabecera
         String cabecera="";
         for(String palabra:bolsaPalabrasTFIDF.get(0).getPalabrasDiccionario()){
            cabecera+=palabra+",";
         }
         cabecera+="clase,";
         
         archivoSalidaTFIDF.ecribeLinea(ARCHIVO_TFIDF, cabecera.substring(0, cabecera.length()-1));
         int i=0;
         for(ElementoBolsaPalabras elementoBolsaPalabras:bolsaPalabrasTFIDF){
            String pesosTFIDF="";
            
            for(Double pesoTFIDF:elementoBolsaPalabras.getTDIDF()){
                 pesosTFIDF+=pesoTFIDF.toString()+",";
          }
            pesosTFIDF+=documentos.get(i).getEstado()+",";
           archivoSalidaTFIDF.ecribeLinea(ARCHIVO_TFIDF, pesosTFIDF.substring(0, pesosTFIDF.length()-1));  
           i++;
        }
     }
     
     private static void guardarMorfologico(ArrayList<ElementoCaracteristicaMorfologica> bolsaCaracteristicasMorfologicas, String ARCHIVO_MORFOLOGICO,ArrayList<DocumentoDTO> documentos){
       ArchivoES archivoSalidaMorfo= new ArchivoES();
         // Escribe la cabecera
         String cabecera="";
         for(String palabra:bolsaCaracteristicasMorfologicas.get(0).getNombresCaracteristicas()){
            cabecera+=palabra+",";
         }cabecera+="clase,";
         
         archivoSalidaMorfo.ecribeLinea(ARCHIVO_MORFOLOGICO, cabecera.substring(0, cabecera.length()-1));
         int i=0;
         for(ElementoCaracteristicaMorfologica elementoBolsaCaracteristicasMorfologicas:bolsaCaracteristicasMorfologicas){
            String pesosMorfologico="";
            for(Integer pesoMorfo:elementoBolsaCaracteristicasMorfologicas.getPesosCaracteristicas()){
                 pesosMorfologico+=pesoMorfo.toString()+",";
          } pesosMorfologico+=documentos.get(i).getEstado2()+",";
           archivoSalidaMorfo.ecribeLinea(ARCHIVO_MORFOLOGICO, pesosMorfologico.substring(0, pesosMorfologico.length()-1)); 
           i++;
        }
         
         
      
     }
    
     
}
