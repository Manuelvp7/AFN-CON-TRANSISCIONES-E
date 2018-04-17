/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista;

import automatafinitonodeterminista.vista.SelectorDeArchivos;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;

/**
 *
 * @author manuel
 */
public class LectorDeArchivos {
    
    private SelectorDeArchivos unSelectorDeArchivos;
    
    private File archivo;
    private Scanner x;
    private char a;
    private String rutaDeArchivo;
    private JFileChooser selector;
    
    
    public LectorDeArchivos(){
        
        rutaDeArchivo = "src/automatafinitonodeterminista/y.csv";
        //selector = new JFileChooser();
        //archivo = selector.getSelectedFile();
        
    }

    public JFileChooser getSelector() {
        return selector;
    }
    

    

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {

        this.archivo = archivo;
    }
    
    public void abrirArchivo(){
      
        try {
            
            archivo = new File(rutaDeArchivo);
            x = new Scanner(archivo);
            
        } catch (Exception e) {
            
            System.out.println("no se encotro el archivo");
            e.printStackTrace();
        }
        

    }
    
    public ArrayList<String> leerArchivo(){
        
        
        ArrayList<String> quintupla = new ArrayList<>();
        
        
        while(x.hasNext()){
        
            String buffer = new String();
            
            buffer = x.next();
            quintupla.add(buffer);
            
            
            
        }
        
        return quintupla;
    }
    
    public void cerrarArchivo(){
        
        x.close();
    }
    
    
    
    
    
}
