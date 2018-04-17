/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo;

import java.awt.List;

/**
 *
 * @author manuel
 */
public class Transicion {

    private int[] estados;
    
    public Transicion(int estado) {
        
        estados = new int[1];
        estados[0]=estado;
        
        
    }

    public int[] getEstados() {
        return estados;
    }
    

    public void setEstados(int[] estados) {
        this.estados = estados;
    }
    
    public void anadirEstado(int nuevoEstado){
        
        int [] aux = estados;
        
        int tamanoEstado = estados.length+1;
        estados = new int[tamanoEstado];
        for (int i : aux) {
            
            tamanoEstado--;
            
            estados[tamanoEstado]=i;
            
        }
        
        estados[tamanoEstado-1]=nuevoEstado;
        
    }

    @Override
    public String toString() {
    
        int espacios = 6-estados.length;
        
        StringBuilder sb = new StringBuilder();

        sb.append(" | ");
        for (int i = 0; i < espacios; i++) {
            sb.append(" ");            
        }        
        for (int estado : estados) {

            
            sb.append(estado+",");
            
        }
        for (int i = 0; i < espacios; i++) {
                
            sb.append(" ");
            
        }
        
        
        return sb.toString();
    }
    
    
    
    
    
    
    
}
