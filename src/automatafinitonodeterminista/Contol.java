/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista;

import automatafinitonodeterminista.modelo.VerificadorDeCadenas;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author manuel
 */
public class Contol {
    
    public static void main(String[] args) {
        
        String cadena = new String();
        
        
        VerificadorDeCadenas unVerificadorDeCadenas = new VerificadorDeCadenas();
        unVerificadorDeCadenas.cargarQuintupla();
        unVerificadorDeCadenas.toString();
        cadena = JOptionPane.showInputDialog("Ingrese una cadena");
        String [] cadenas = cadena.split(",");
        for (String cadena1 : cadenas) {
                    
            System.out.println("#########################################################################################");
            System.out.println("\n\n\tCADENA "+cadena1+"\n");
            unVerificadorDeCadenas.verificarCadena(cadena1);
            unVerificadorDeCadenas.getAutomataFinitoNoDeterminista().imprimirCaminos();
            
        }

        //unVerificadorDeCadenas.getAutomataFinitoNoDeterminista().validarCadena("abaabbbcbabaaaab");
    }
    
}
