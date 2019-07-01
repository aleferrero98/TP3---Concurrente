import java.util.ArrayList;
import java.util.HashMap;

public class Politicas {
    private HashMap<Integer, Integer> prioridades;
    private RedDePetri RdP;

    public Politicas(RedDePetri RdP) {
        this.prioridades = new HashMap<>();
        this.RdP = RdP;

    }

    public void addPrioridad(int transicion, int prioridad){
        this.prioridades.put(transicion, prioridad);
    }

    public int decidir(ArrayList<Integer> sensibilizadas) {//devuelve la transicion de mayor prioridad entre las sensibilizadas
        int eleccion = sensibilizadas.get(0);
        for (int transicion : sensibilizadas) {
            if (prioridades.get(transicion) > prioridades.get(eleccion)) {
                eleccion = transicion;
            }
        }
        if(eleccion==4 || eleccion==14){ //si se elige la T4 y T14 se decide entre una u otra de acuerdo a la cantidad de tokens..
            return resolverConflictoBuffer(RdP.getMarcaActual()); //...en CPU_buffer1 y CPU_buffer2
        }
        else if(eleccion == 1 || eleccion==6){
            //return resolverConflictoCPU1(RdP.getMarcaActual());
            if(Math.random()>0.5){
                return 6;
            }else return 1;
        }
        else if(eleccion == 8 || eleccion==10){
            //return resolverConflictoCPU2(RdP.getMarcaActual());
            if(Math.random()>0.5){
                return 10;
            }else return 8;
        }
        else{
            return eleccion;            //sino se retorna la otra transicion elegida
        }
    }

    private int resolverConflictoCPU1(int[] marcaActual) {//elige entre T6 y T1 segun el marcado de P6
        if(marcaActual[6]>0) return 6;
        else return 1;
    }
    private int resolverConflictoCPU2(int[] marcaActual) {//elige entre T10 y T8 segun el marcado de P10
        if(marcaActual[10]>0) return 10;
        else return 8;
    }

    private int resolverConflictoBuffer(int[] marcaActual){   //resuelve conflicto entre T4 y T14 para mantener iguales la cantidad de tokens en c/u de los dos buffers
        if(marcaActual[1]<marcaActual[15]){                   //marcado[1] = CPU_buffer1 es alimentado por T4
            return 4;                                         //marcado[15] = CPU_buffer2 es alimentado por T14
        }else if(marcaActual[1]>marcaActual[15]){
            return 14;
        }else if(Math.random()<0.5){        //si llegan a ser iguales se resuleve mediante un nro aleatorio
            return 4;
        }else return 14;
    }
}