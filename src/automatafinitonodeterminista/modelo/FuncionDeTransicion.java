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
public class FuncionDeTransicion {
    
    private int desde;
    private int hacia;
    private char caracter;

    public FuncionDeTransicion() {
    }

    public FuncionDeTransicion(int desde, char caracter, int hacia) {
        this.caracter = caracter;
        this.desde = desde;
        this.hacia = hacia;
    }

    public char getCaracter() {
        return caracter;
    }

    public int getDesde() {
        return desde;
    }

    public int getHacia() {
        return hacia;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public void setDesde(int de) {
        this.desde = de;
    }

    public void setHacia(int hacia) {
        this.hacia = hacia;
    }

    public String getPath(){
        
        StringBuilder sb = new StringBuilder();
        sb.append(getDesde()+"("+getCaracter()+")-");
        
        return sb.toString();
    }
    
    public void removeCaracter(){
        caracter = '\0';
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(getDesde()+","+getCaracter()+","+getHacia());
        
        return sb.toString();
    }
    
    
    
   
    
    
}
