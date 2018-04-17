/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo.interfaces;

import automatafinitonodeterminista.modelo.Alfabeto;
import automatafinitonodeterminista.modelo.BuscadorDeCaminos;
import automatafinitonodeterminista.modelo.ConjuntoDeEstados;
import automatafinitonodeterminista.modelo.FuncionDeTransicion;
import automatafinitonodeterminista.modelo.TablaDeTransiciones;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public interface InterfazAutomataCamino {
    
    public void bifurcar(ArrayList<FuncionDeTransicion> f ,String cadena,String cadenaCondicionParo);
    public TablaDeTransiciones consultarTablaDeTrancisiones();
    public ConjuntoDeEstados consultarEstados();
    public Alfabeto consultarAlfabeto();
    public void imprimirCamino(String camino);
    
}
