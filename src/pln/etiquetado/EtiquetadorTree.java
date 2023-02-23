/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pln.etiquetado;

import modelo.PalabraEtiquetadaDTO;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

/**
 *
 * @author Profesor
 */
public class EtiquetadorTree {
    
    
    public static ArrayList<PalabraEtiquetadaDTO> etiquetar(String [] cadenaEntrada) throws IOException, TreeTaggerException{
        final ArrayList<PalabraEtiquetadaDTO> palabrasEtiquetadas = new ArrayList<PalabraEtiquetadaDTO>();
    	// Point TT4J to the TreeTagger installation directory. The executable is expected
        // in the "bin" subdirectory - in this example at "/opt/treetagger/bin/tree-tagger"
        System.setProperty("treetagger.home", "recursos/TreeTagger");
        
        TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
        try {
                tt.setModel("recursos/TreeTagger/modelos/sp.par:iso8859-1");
                tt.setHandler(new TokenHandler<String>() {
                        public void token(String token, String pos, String lemma) {
                                palabrasEtiquetadas.add(new PalabraEtiquetadaDTO(token,pos,lemma));
                                //System.out.println(token + "\t" + pos + "\t" + lemma);
                        }
                });
                
                tt.process(asList(cadenaEntrada));
        }
        finally {
            tt.destroy();
        }
      return palabrasEtiquetadas;
    }
}
