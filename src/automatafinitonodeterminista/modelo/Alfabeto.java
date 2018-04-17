/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Alfabeto {
    
    
    private char[] caracteres = null;

    public Alfabeto() {
    }

    
    public char[] getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(char[] caracteres) {
        this.caracteres = caracteres;
    }
    
    public int getIndexAlfabeto(char caracter){
        
        int i = 0;
        for (char aux : caracteres) {
            
            if(Character.toString(aux).equals(Character.toString(caracter))){
                return i;
            }else{
                i++;
            }
        }
        return -1;
    }
    
    public String getCabezeraTblaAlfabeto(){
        
        StringBuilder sb = new StringBuilder();
        int espacios = 5;
        sb.append("              ");
        for (int i = caracteres.length-1; i >-1; i--) {
            
            sb.append(" | ");
            for (int j = 0; j < espacios; j++) {
                sb.append(" ");
            }
            sb.append(caracteres[i]+",");
            for (int j = 0; j < espacios; j++) {
                sb.append(" ");
            }
            
            
        }
        
        for (char c : caracteres) {
            

        }
        
        return sb.toString();
        
    }

    @Override
    public String toString() {
    
        StringBuilder sb = new StringBuilder();
        sb.append("ALFABETO : " );
        for(char c : caracteres){
            sb.append(c+",");
        }
                

        
        
        
        sb.append("\n");
        return sb.toString();
    }
    
    
    
    
    
    
    
}
