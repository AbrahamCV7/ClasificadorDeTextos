/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pln.utilerias;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import modelo.DocumentoDTO;

/**
 *
 * @author Profesor
 */
public class ArchivoES {
    
    
    //Metodo para lectura del archivo txt
     public ArrayList<DocumentoDTO> leerArchivosTexto(String folder) throws FileNotFoundException, IOException {
        
                File folderFile = new File(folder);
                ArrayList<DocumentoDTO> documentos = new ArrayList<DocumentoDTO>();
                int id=1;
         
                File[] files = folderFile.listFiles();
                    for (File archivo : files) {
                        if(archivo.getName().endsWith("txt")){
                            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));

                            String linea;
                            String [] parcial;
                            while((linea = br.readLine()) != null)
                            {
                                DocumentoDTO doc = new DocumentoDTO();
                                doc.setId(id);
                                parcial=linea.split("\t");
                                doc.setTexto(parcial[4]);
                                doc.setEstado(parcial[2]);
                                if(parcial[2].equalsIgnoreCase("VERDAD")){
                                    doc.setEstado2(1);}else doc.setEstado2(0);
                                documentos.add(doc);
                                id++;
                               
                             }
                        }
                    }
                        
                     
           
        return documentos;
    }
    
    
     
    
    /**
     * Escribe una linea al final del archivo.
     *
     * 
     */
    public void ecribeLinea(String archivo, String linea) {
        try {
            FileOutputStream fs = new FileOutputStream(archivo, true);
            DataOutputStream os = new DataOutputStream(fs);
          
            os.writeBytes(linea + "\n");
            os.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error de escritura... " + ex.getMessage());
        }
    }  
   
}
