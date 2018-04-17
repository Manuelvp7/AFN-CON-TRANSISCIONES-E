/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo;

/**
 *
 * @author manuel
 */
public class TablaDeTransiciones {
    
    
    private Transicion [][] transiciones;
    private int numeroEstados;
    private int tamanoAlfabeto;

    public TablaDeTransiciones(int estados,int alfabeto) {
        
        transiciones = new Transicion[estados][alfabeto];
        numeroEstados = estados;
        tamanoAlfabeto = alfabeto;
    }
    
    public void anadirElementoALaTabla(int posEstado, int posAlfabeto,int elemento){

        
        
        if(transiciones[posEstado][posAlfabeto]!=null){
            
            transiciones[posEstado][posAlfabeto].anadirEstado(elemento);
        }else{
            transiciones[posEstado][posAlfabeto] = new Transicion(elemento);   
        }
    }
    
    public Transicion getTransicion(int posAlfabeto,int posEstado){
        
        return transiciones[posEstado][posAlfabeto];
    }


    public String imprimirFila(int fila){
        
        
        
        StringBuilder sb = new StringBuilder();
        int espacios = 4;
        
        for (int i = tamanoAlfabeto-1; i >-1; i--) {

            if (transiciones[fila][i]!=null){
                sb.append(transiciones[fila][i].toString());
            }else{
                sb.append(" | ");
                for (int j = 0; j < espacios; j++) {
                    sb.append(" ");            
                }                    
                    sb.append("NULL");

                for (int j = 0; j < espacios; j++) {
                    sb.append(" ");
                }        
                
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        
        for (int j = numeroEstados-1; j > -1; j--) {
            for (int i = tamanoAlfabeto-1; i > -1; i--) {
                
                if (transiciones[j][i]!=null) {
                
                    System.out.println(transiciones[j][i].toString());
                }else{
                    System.out.println("Null");
                }
                
            }
        }
    
        return null;
    }
}
