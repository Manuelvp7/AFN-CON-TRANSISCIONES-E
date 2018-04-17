/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo;

import automatafinitonodeterminista.modelo.interfaces.InterfazAutomataCamino;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class AutomataFinitoNoDeterminista implements InterfazAutomataCamino{
    
    private ArrayList<FuncionDeTransicion> funcionesDeTransicion;
    private Alfabeto alfabeto;
    private ConjuntoDeEstados conjuntoDeEstados;
    private TablaDeTransiciones tablaDeTransiciones;
    private BuscadorDeCaminos unBuscadorDeCaminos;
    private int i;
    private ArrayList<Runnable> listaDeBuscadores;
    private BuscadorDeCaminos[] buscadores;
    //private Runnable[] runnables;

    public AutomataFinitoNoDeterminista() {
    
            funcionesDeTransicion = new ArrayList<>();
            alfabeto = new Alfabeto();
            conjuntoDeEstados = new ConjuntoDeEstados();
            
            
            
            i=0;
    
    }
    
    

    public Alfabeto getAlfabeto() {
        return alfabeto;
    }

    public ConjuntoDeEstados getConjuntoDeEstados() {
        return conjuntoDeEstados;
    }

    public ArrayList<FuncionDeTransicion> getFuncionesDeTransicion() {
        return funcionesDeTransicion;
    }

    public void setAlfabeto(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
    }

    public void setConjuntoDeEstados(ConjuntoDeEstados conjuntoDeEstados) {
        this.conjuntoDeEstados = conjuntoDeEstados;
    }

    public void setFuncionesDeTransicion(ArrayList<FuncionDeTransicion> funcionesDeTransicion) {
        this.funcionesDeTransicion = funcionesDeTransicion;
    }

    public TablaDeTransiciones getTablaDeTransiciones() {
        return tablaDeTransiciones;
    }

    public void setTablaDeTransiciones(TablaDeTransiciones tablaDeTransiciones) {
        this.tablaDeTransiciones = tablaDeTransiciones;
    }

    
    @Override
    public String toString() {
    
        int filas = conjuntoDeEstados.getEstados().length;
        int tamanoAlfabeto = alfabeto.getCaracteres().length;
        StringBuilder sb = new StringBuilder();
        sb.append(conjuntoDeEstados.toString()+"\n"+alfabeto.toString()+"\nFUNCIONES DE TRANSICION\n");
        for (Iterator<FuncionDeTransicion> iterator = funcionesDeTransicion.iterator(); iterator.hasNext();) {
            FuncionDeTransicion next = iterator.next();
            sb.append(next.toString()+"\n");  
        }
        sb.append("\n \tTABLA DE TRANSICIONES\n\n");
        sb.append(alfabeto.getCabezeraTblaAlfabeto()+"\n");
                    
        for (int j = 0; j <tamanoAlfabeto +1; j++) {
            sb.append("|--------------");    
        }
        
        for (int i = conjuntoDeEstados.getEstados().length-1; i > -1; i--) {
            sb.append("\n       "+conjuntoDeEstados.getEstados()[i]+"      "+tablaDeTransiciones.imprimirFila(i));
        
        }        
        System.out.println(sb);
        return sb.toString();
    }
    
    public void validarCadena(String cadena){
        
        //listaDeBuscadores = new ArrayList<>();

        
        //listaDeBuscadores.add(r);
        buscadores = new BuscadorDeCaminos[1];
        buscadores[0] = new BuscadorDeCaminos(this, cadena);
        //Thread t = new Thread(r);
        buscadores[0].buscar();
        /*try {
            buscadores[0].join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AutomataFinitoNoDeterminista.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    public void bifurcar(ArrayList<FuncionDeTransicion> f, String cadena,String cadenaCondicionParo) {
        
//        System.out.println("LAS FUNCIONES DE TRANSICION EN LA BIFURCACION "+unBuscadorDeCaminos.getFuncionesDeTransicion().toString());
        
      //  System.out.println("HILO CREADO CADENA "+cadena);
        
        

        //listaDeBuscadores.add(run);
        int tamanoHilos = buscadores.length;
        BuscadorDeCaminos []aux = buscadores;
        buscadores = new BuscadorDeCaminos[tamanoHilos+1];
        for (int j = 0; j < tamanoHilos; j++) {
            buscadores[j]=aux[j];
        }
        buscadores[tamanoHilos]=new BuscadorDeCaminos( this, 
                cadena,f,cadenaCondicionParo);
        buscadores[tamanoHilos].buscar();
         
        /*try {
            buscadores[tamanoHilos].join();
        } catch (InterruptedException ex) {
            Logger.getLogger(AutomataFinitoNoDeterminista.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    //    System.out.println("FUNCIONES DE TRANSICION DEL RUNNABLE " );
        
      //  Thread t = new Thread(run);
        
        //t.start();
        
        
        
    }

    @Override
    public TablaDeTransiciones consultarTablaDeTrancisiones() {
        return tablaDeTransiciones;
    }

    @Override
    public ConjuntoDeEstados consultarEstados() {
        return conjuntoDeEstados;
    }

    @Override
    public Alfabeto consultarAlfabeto() {
        return alfabeto;
    }

    @Override
    public void imprimirCamino(String camino) {
        System.out.println(camino);
    }
    
    public void imprimirCaminos(){
        System.out.println("---------------------------------------------------------------------------"
                + "\n\t CAMINOS VALIDOS\n");
        for (BuscadorDeCaminos buscadore : buscadores) {
            if (buscadore.getCaminoValido()) {
                System.out.println(buscadore.toString());
            }
        }
                
        System.out.println("-----------------------------------------------------------------------------"
                + "\n\t CAMINOS NO VALIDOS\n");
        for (BuscadorDeCaminos buscadore : buscadores) {
            if (!buscadore.getCaminoValido()) {
                System.out.println(buscadore.toString());
            }
        }
    }

    

    



    
    
    

    
}
