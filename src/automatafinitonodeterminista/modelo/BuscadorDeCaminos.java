/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatafinitonodeterminista.modelo;

import automatafinitonodeterminista.modelo.interfaces.InterfazAutomataCamino;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class BuscadorDeCaminos extends VerificadorDeCadenas{
    

    private InterfazAutomataCamino unaInterfazAutomataCamino;
    private ArrayList<FuncionDeTransicion> funcionesDeTransicion;
    private String cadena;
    private int tamanoFuncionesTransicion;
    private boolean caminoValido;
    private String identificaor;
    private String cadenaCondicionParo;

    public BuscadorDeCaminos(InterfazAutomataCamino unaInterfazAutomataCamino, String cadena,
            ArrayList<FuncionDeTransicion> funcionesDeTransicions,String cadenaCondicionParo) {
        
        this.cadenaCondicionParo = cadenaCondicionParo;

        this.unaInterfazAutomataCamino = unaInterfazAutomataCamino;
        this.cadena = cadena;
        this.funcionesDeTransicion = funcionesDeTransicions;

        identificaor = "HOLA "+cadena;
              //  System.out.println("LAS FUNCIONES DE TRANSICION EN EL CONSTRUCTOR "+identificaor+" "+this.funcionesDeTransicion.toString());

    }
    
        
    public BuscadorDeCaminos(InterfazAutomataCamino unaInterfazAutomataCamino, String cadena) {
        

        this.cadenaCondicionParo = cadena;
        this.unaInterfazAutomataCamino = unaInterfazAutomataCamino;
        this.cadena = cadena;
        this.funcionesDeTransicion = new ArrayList<>();
        identificaor = "HOLA "+cadena;

    }



    public String getCadena() {
        return cadena;
    }

    public ArrayList<FuncionDeTransicion> getFuncionesDeTransicion() {
        return funcionesDeTransicion;
    }

    public InterfazAutomataCamino getUnaInterfazAutomataCamino() {
        return unaInterfazAutomataCamino;
    }
        
    public boolean getCaminoValido(){
        return caminoValido;
    }
    
    //@Override
    public void buscar() { 
        char aux;
        int posAlfabeto;
        int posEstado;
        int estadoDesde;
        int estadoAux;
        int i;

        tamanoFuncionesTransicion = funcionesDeTransicion.size()-1;//calcula el manao del arreglo de funciones detransicion
        
                
        Transicion unaTransicion;
        Transicion transicionE;
        int tamanoEstadoDeTransicion;
        
        if(tamanoFuncionesTransicion<0){//verifica que el arreglo de funciones de transicion no est[e vacio
            int desde = unaInterfazAutomataCamino.consultarEstados().getEstadoInicial();
            FuncionDeTransicion inicio = new FuncionDeTransicion();
            inicio.setDesde(desde);
            funcionesDeTransicion.add(inicio);//anade el estado inicial al arreglo de funciones de transicion    
            tamanoFuncionesTransicion++;//incrementa la variable que controla es lamano del arreglo de funciones de transicion
            //System.out.println("EMPIEZA LA PRIMERA LISTA TAMANO "+tamanoFuncinesTransicion);//
        }
        
        //System.out.println("TAMANO DE LA CADENA " + (cadena.length()-1));
        for (i = 0; i < cadena.length(); i++) {//ciclo para validar la cadena caracter por caracter

            aux = cadena.charAt(i);//toma el caracter[i] de la cadena
            FuncionDeTransicion unaFuncionDeTransicion = new FuncionDeTransicion();//se crea una funcion de transicion auxiliar para almacenar 
            //futruros estdados en el atributo desde 
            posAlfabeto=unaInterfazAutomataCamino.consultarAlfabeto().getIndexAlfabeto(aux);//calcula la posicion en el arreglo de
            //caracteres del alfabeto segun el caracter[i] del ciclo de recorrido (for) anterior

            estadoDesde = funcionesDeTransicion.get(funcionesDeTransicion.size()-1).getDesde();//toma el valor del atributo "desde" del 
            //ultimo elemento del arreglo de funciones de transicion
            posEstado = unaInterfazAutomataCamino.consultarEstados().getIndexEstado(estadoDesde);//verifica que el estado "desde"
            //tomado de la instruccion pasada exista en el arreglo estados del automata

            if(posEstado>-1){
                
            }else if(posEstado<0){
              //  System.out.println("ESTADO NO ENCONTRADO "+estadoDesde);//estado no valida
                funcionesDeTransicion.get(tamanoFuncionesTransicion).setDesde(-1);
                //break;
            }    
            
            transicionE = unaInterfazAutomataCamino.consultarTablaDeTrancisiones().getTransicion(0, posEstado);
            if(posAlfabeto>-1&&posEstado>-1)
                unaTransicion = unaInterfazAutomataCamino.consultarTablaDeTrancisiones().getTransicion(posAlfabeto, posEstado);
            else
                unaTransicion=null;
            if(transicionE!=null||unaTransicion!=null){
                
                if(transicionE==null){
                    recorrer(unaTransicion, i, false, aux, false);

                }else if(unaTransicion==null){ 
                    recorrer(transicionE, i, true, 'e', false);
                    i--;
                }else if(unaTransicion.getEstados().length==1||transicionE.getEstados().length==1){
                    if(unaTransicion.getEstados().length==1&&transicionE.getEstados().length==1){                        
                        recorrer(transicionE, i, true, 'e', true);
                        recorrer(unaTransicion, i, false, aux, false);
                    }
                    if(unaTransicion.getEstados().length>1){
                        recorrer(transicionE, i, true, 'e', true);
                        recorrer(unaTransicion, i, false, aux, false);
                    }else if(transicionE.getEstados().length>1){
                        recorrer(transicionE, i, true, 'e', true);
                        recorrer(unaTransicion, i, false, aux, false);
                    }
                }else{
                    recorrer(transicionE, i, true, 'e', true);
                    recorrer(unaTransicion, i, false, aux, false);
                } 
                 
                
            }else {
                break;
            }
    
        }
        
        verificarTransicionesE();
        StringBuilder sb = new StringBuilder();
        
        estadoAux=funcionesDeTransicion.get(funcionesDeTransicion.size()-1).getDesde();
        if(unaInterfazAutomataCamino.consultarEstados().validarEtadoFinal(estadoAux)
                &&comprobarRecorrido()){
            caminoValido=true;
            //sb.append("\nCADENA VALIDA ");
        }else{
            caminoValido=false;
            //sb.append("\nCADENA NO VALIDA ");
        }
        /*
        for (FuncionDeTransicion funcionDeTransicion : funcionesDeTransicion) {
            sb.append(funcionDeTransicion.getPath());
        }
        sb.append("\n");*/
        //unaInterfazAutomataCamino.imprimirCamino(sb.toString());
        //unaInterfazAutomataCamino.coprobarCaminos();
        //System.out.println("TERMINAOS");
        //buscadorFinalizado = true;
        

        

        
        
    }
    
    public void verificarTransicionesE(){
        
        Transicion transicionE;
        int estadoDesde,posEstado;
        do{
            //System.out.println("VERIFICANDO CADENA");
            estadoDesde = funcionesDeTransicion.get(funcionesDeTransicion.size()-1).getDesde();//toma el valor del atributo "desde" del 
            //ultimo elemento del arreglo de funciones de transicion
            posEstado = unaInterfazAutomataCamino.consultarEstados().getIndexEstado(estadoDesde);//verifica que el estado "desde"
            transicionE = unaInterfazAutomataCamino.consultarTablaDeTrancisiones().getTransicion(0, posEstado);
            
            if(transicionE!=null){
                if (transicionE.getEstados().length>1) {
                    for (int j = 0; j <=transicionE.getEstados().length-2; j++) {
                        ArrayList<FuncionDeTransicion> f = new ArrayList<>();
                        for (FuncionDeTransicion funcionDeTransicion : funcionesDeTransicion) {
                            f.add(funcionDeTransicion);   
                        }
                        f.get(f.size()-1).setCaracter('e');
                        f.get(f.size()-1).setHacia(transicionE.getEstados()[j]);
                        FuncionDeTransicion ft = new FuncionDeTransicion();
                        ft.setDesde(transicionE.getEstados()[j]);
                        f.add(ft);       
                        unaInterfazAutomataCamino.bifurcar(f,Character.toString('\0'),cadenaCondicionParo);
                    }
                                

                }
                                   
                int tamanoFuncinesTransicion = funcionesDeTransicion.size()-1;
                FuncionDeTransicion fun = new FuncionDeTransicion();
                fun.setDesde(transicionE.getEstados()[transicionE.getEstados().length-1]);
                funcionesDeTransicion.get(tamanoFuncinesTransicion).setCaracter('e');
                funcionesDeTransicion.get(tamanoFuncinesTransicion).setHacia(transicionE.getEstados()[transicionE.getEstados().length-1]);
                funcionesDeTransicion.add(fun);
            }else{
                break;
            }
            
        }while(transicionE!=null);
        
        
    }
    
    
    
    
    public boolean comprobarRecorrido(){
        

        StringBuilder sb = new StringBuilder();
        char aux;
        for (int i = 0; i < funcionesDeTransicion.size(); i++) {
            aux = funcionesDeTransicion.get(i).getCaracter();
            if(aux!='e'&&aux!='\0')
                sb.append(aux);
        }
        if (cadenaCondicionParo.equals(sb.toString())) {
            return true;
        }
        return false;
    }
    
    public void recorrer(Transicion unaTransicion, int i, boolean esTransicionE,char aux,boolean bifurcarTodaTransicion){
        int numeroBifurcaciones;
        
        if(bifurcarTodaTransicion){
            numeroBifurcaciones = unaTransicion.getEstados().length-1;
        }else 
            numeroBifurcaciones = unaTransicion.getEstados().length-2;
        if (unaTransicion.getEstados().length>1) {
            for (int j = 0; j <=numeroBifurcaciones; j++) {
                ArrayList<FuncionDeTransicion> f = new ArrayList<>();
                for (FuncionDeTransicion funcionDeTransicion : funcionesDeTransicion) {
                    f.add(funcionDeTransicion);   
                }
                f.get(f.size()-1).setCaracter(aux);
                f.get(f.size()-1).setHacia(unaTransicion.getEstados()[j]);
                FuncionDeTransicion ft = new FuncionDeTransicion();
                ft.setDesde(unaTransicion.getEstados()[j]);
                f.add(ft);   
                if(esTransicionE){
                    unaInterfazAutomataCamino.bifurcar(f, cadena.substring(i),cadenaCondicionParo);
                    
                }else{
                    unaInterfazAutomataCamino.bifurcar(f, cadena.substring(i+1),cadenaCondicionParo);
                    
                }
                
                            //funcionesDeTransicion.remove(tamanoFuncinesTransicion+1);
            }
                     //   System.out.println("VALORES DESPUES DE BIFURCAR "+identificaor+" "+funcionesDeTransicion.toString());
        }        
            
        if (!bifurcarTodaTransicion) {
            int tamanoFuncinesTransicion = funcionesDeTransicion.size()-1;
            FuncionDeTransicion fun = new FuncionDeTransicion();
            fun.setDesde(unaTransicion.getEstados()[unaTransicion.getEstados().length-1]);
            funcionesDeTransicion.get(tamanoFuncinesTransicion).setCaracter(aux);
            funcionesDeTransicion.get(tamanoFuncinesTransicion).setHacia(unaTransicion.getEstados()[unaTransicion.getEstados().length-1]);
            funcionesDeTransicion.add(fun);
            
        }

        
        //tamanoFuncinesTransicion++;
    }

    @Override
    public String toString() {
    
        StringBuilder sb = new StringBuilder();
                
        for (FuncionDeTransicion funcionDeTransicion : funcionesDeTransicion) {
            sb.append(funcionDeTransicion.getPath());
        }
        sb.append("\n");
        return sb.toString();
    }
    
    
    
    
    
}
