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
        if(eleccion==6 || eleccion==7){                     //si se elige la T6 y T7 se decide entre una u otra de acuerdo a la cantidad de tokens..
            return resolverConflictoBuffer(RdP.getMarcaActual()); //...en CPU_buffer1 y CPU_buffer2
        }
        else if(eleccion == 1 || eleccion == 5){
            return resolverConflictoCPU(RdP.getMarcaActual());
        }
        return eleccion;        //sino se retorna la otra transicion elegida
    }

    private int resolverConflictoCPU(int[] marcaActual) {//elige entre T1 y T5 segun el marcado de P10
        if(marcaActual[9]>0) return 5;
        else return 1;
    }

    private int resolverConflictoBuffer(int[] marcaActual){   //resuelve conflicto entre T5 y T6 para mantener iguales la cantidad de tokens en c/u de los dos buffers
        if(marcaActual[2]<marcaActual[3]){                  //marcado[2] = CPU_buffer1 es alimentado por T6
            return 6;                               //marcado[3] = CPU_buffer2 es alimentado por T7
        }else return 7;
    }
}