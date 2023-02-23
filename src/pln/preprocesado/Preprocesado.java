/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pln.preprocesado;


import modelo.DocumentoDTO;
import logica.PalabrasVacias;

/**
 *
 * @author Profesor
 */
public class Preprocesado {
    
    private final String[] PALABRAS_VACIAS;

    
    public Preprocesado(){
      PALABRAS_VACIAS = PalabrasVacias.PALABRAS_VACIAS;
    
    }

    /*   Pre-procesar los textos
             Elimina enlaces   
             Eliminar todo caracter especial,  Se coinvierte todo a minuscula
             Eliminar palabras vacias
            
   
        */
    public DocumentoDTO limpiarTexto(DocumentoDTO documento){
       documento.setTextoPreprocesado(quitarURL(documento.getTexto()));
       documento.setTextoPreprocesado(extraerPal(documento.getTextoPreprocesado()));
       documento.setTextoPreprocesado(eliminaPalabrasVacias(documento.getTextoPreprocesado()));
       
       
        return documento;
    }
            
            

    
    //Elimina carcateres especiales, numeros, etc. Solo se queda con las palabras compuestas por letras
     private String extraerPal(String cadenas) {
    	String res="";
     	for(String c:cadenas.split(" ")){
            c=c.toLowerCase();
            c=c.replaceAll("[^a-zA-Zαινσϊρό]","");
            res=res+c+" ";    
     	}
     	return res;
     	
     }
     
          
     
     // Elimina palabras vacias
     private String eliminaPalabrasVacias(String cadenas) {
    	  String res="";
     	for(String c:cadenas.split(" ")){
            boolean estaPresentePalVacia=false;
            for(String palVacia:PALABRAS_VACIAS){
                if(palVacia.equals(c))
                    estaPresentePalVacia=true;
            }
 	    if(!estaPresentePalVacia)
                res=res+c+" ";

     	}
     	return res;
     	
     }
     
     
           
     
     //Metodo para quitar urls
    private String quitarURL(String texto) {
      String textoSinURL="";
     	for(String palabra:texto.split(" ")){
     	    if(!palabra.startsWith("http"))  	
                textoSinURL=textoSinURL+palabra+" ";
          
     	}
     	return textoSinURL;
    }
    
    //Metodo para dar anonimato al usuario //omitido en este programa para no asignar peso a @user
    public String cambiarUser(String texto){
        String textoCamdiado="";
        for(String palabra:texto.split(" ")){
            if(palabra.startsWith("@")){
                textoCamdiado=textoCamdiado+"@user "; 
            }else{
                textoCamdiado=textoCamdiado+palabra+" ";
            }
        }
        return textoCamdiado;
    }
    
   
}
