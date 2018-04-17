/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo;

import automatafinitonodeterminista.LectorDeArchivos;
import automatafinitonodeterminista.modelo.AutomataFinitoNoDeterminista;
import automatafinitonodeterminista.modelo.FuncionDeTransicion;
import automatafinitonodeterminista.modelo.TablaDeTransiciones;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author manuel
 */
public class VerificadorDeCadenas {
    
    private AutomataFinitoNoDeterminista automataFinitoNoDeterminista;
    private LectorDeArchivos lectorDeArchivos;
    private String cadena;

    public VerificadorDeCadenas() {

        lectorDeArchivos = new LectorDeArchivos();
        automataFinitoNoDeterminista = new AutomataFinitoNoDeterminista();

    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    
    public AutomataFinitoNoDeterminista getAutomataFinitoNoDeterminista() {
        return automataFinitoNoDeterminista;
    }

    public void setAutomataFinitoNoDeterminista(AutomataFinitoNoDeterminista automataFinitoNoDeterminista) {
        this.automataFinitoNoDeterminista = automataFinitoNoDeterminista;
    }

    public LectorDeArchivos getLectorDeArchivos() {
        return lectorDeArchivos;
    }

    public void setLectorDeArchivos(LectorDeArchivos lectorDeArchivos) {
        this.lectorDeArchivos = lectorDeArchivos;
    }
    public void verificarCadena(String cadena){
        
        setCadena(cadena);
        automataFinitoNoDeterminista.validarCadena(cadena);
        
    }
    
    public void cargarQuintupla(){
        String [] buffer;
        lectorDeArchivos.abrirArchivo();
        ArrayList<String> parametro = lectorDeArchivos.leerArchivo();
        
        int tamanoParametro = parametro.size();
        int i = 0;
        int posAlfabeto = 0;
        int posEstado = 0;

        for (Iterator<String> iterator = parametro.iterator(); iterator.hasNext();) {
           
            String next = iterator.next();
            buffer = next.split(",");
            
            if(i<4){
             
                switch(i){
                    
                    case 0:{
                        
                        int tamanoBuffer = buffer.length;
                        int [] estados = new int[tamanoBuffer];
                        for(String estado : buffer){
                            
                            
                           tamanoBuffer--; 
                           estados[tamanoBuffer] = Integer.parseInt(estado);
                        }
                        automataFinitoNoDeterminista.getConjuntoDeEstados().setEstados(estados);
                        break;
                    }
                    case 1:{
                        
                        int tamanoAlfabeto;
                        int numeroDeEstados;
                        int tamanoBuffer = buffer.length+1;
                        char [] alfabeto = new char[tamanoBuffer];
                        
                        for (String c : buffer) {
                            tamanoBuffer--;
                            alfabeto[tamanoBuffer] = c.charAt(0);
                        }
                        

                        alfabeto[0] = 'e';
                       

                        
                        automataFinitoNoDeterminista.getAlfabeto().setCaracteres(alfabeto);
                        tamanoAlfabeto = automataFinitoNoDeterminista.getAlfabeto().getCaracteres().length;
                        numeroDeEstados = automataFinitoNoDeterminista.getConjuntoDeEstados().getEstados().length;
                        TablaDeTransiciones unaTablaDeTransiciones = new TablaDeTransiciones(numeroDeEstados, tamanoAlfabeto);
                        automataFinitoNoDeterminista.setTablaDeTransiciones(unaTablaDeTransiciones);
                        break;
                    }
                    
                    case 2:{
                        
                        automataFinitoNoDeterminista.getConjuntoDeEstados().setEstadoInicial(Integer.parseInt(buffer[0]));
                        break;
                        
                    }
                    
                    case 3:{
                                         
                        int tamanoBuffer = buffer.length;
                        int [] estadosFinales = new int[tamanoBuffer];
                        for(String estadoFinal : buffer){
                           tamanoBuffer--; 
                           estadosFinales[tamanoBuffer] = Integer.parseInt(estadoFinal);
                        }
                        automataFinitoNoDeterminista.getConjuntoDeEstados().setEstadosFinales(estadosFinales);
                        break; 
                    }
                }
                i++;
            }else{

                
                FuncionDeTransicion unaFuncionDeTransicion = new FuncionDeTransicion(
                    Integer.parseInt(buffer[0]),buffer[1].charAt(0),Integer.parseInt(buffer[2]));
                
                posAlfabeto = automataFinitoNoDeterminista.getAlfabeto().getIndexAlfabeto(buffer[1].charAt(0));
                posEstado = automataFinitoNoDeterminista.getConjuntoDeEstados().getIndexEstado(Integer.parseInt(buffer[0]));
                
                automataFinitoNoDeterminista.getTablaDeTransiciones().anadirElementoALaTabla(posEstado, posAlfabeto,
                        Integer.parseInt(buffer[2]));
                
                automataFinitoNoDeterminista.getFuncionesDeTransicion().add(unaFuncionDeTransicion);
            }   
        }
    }

    @Override
    public String toString() {
    
        
        return automataFinitoNoDeterminista.toString();
        
        
    }
    
    
    
    
    
    
    
}
