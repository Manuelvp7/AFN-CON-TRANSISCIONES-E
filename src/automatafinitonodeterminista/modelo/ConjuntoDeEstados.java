/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author manuel
 */
public class ConjuntoDeEstados {
    
    private int [] estados = null;
    
    private int estadoInicial ;
    
    private int [] estadosFinales = null;
    
    int i;
    
    

    public ConjuntoDeEstados() {
        
        
        
    }

    public int[] getEstados() {
        return estados;
    }


    public int getEstadoInicial() {
        return estadoInicial;
    }


    public void setEstadoInicial(int estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public int[] getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstados(int[] estados) {
        this.estados = estados;
    }

    public void setEstadosFinales(int[] estadosFinales) {
        this.estadosFinales = estadosFinales;
    }
    
    
    public int getIndexEstado(int estado){
        
        i=0;
        
        for (int f : estados) {
            if(estado==f){
                return i;        
            }else{
                i++;
            }
        }
        
        return -1;
    }
    
    
    public boolean validarEtadoFinal(int estado){
        for (int estadosFinale : estadosFinales) {
            if(estado==estadosFinale)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
    
        StringBuilder sb = new StringBuilder();
        sb.append("ESTADO INICIAL: "+ getEstadoInicial()+"\n"
                + "ESTADOS FINALES ");
        for(int i : getEstadosFinales()){
            sb.append(i+",");
            
        }
        sb.append("\nESTADOS :");
         
        for(int i : getEstados()){
            sb.append(i+",");
            
        }
        
        
        return sb.toString();
    }
    
    
    
    
    
    
    
        
    
    
}
