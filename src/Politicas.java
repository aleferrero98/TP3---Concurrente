import java.util.ArrayList;
import java.util.HashMap;

public class Politicas {
    private HashMap<Integer, Integer> prioridades;
    private RedDePetri rdp;

    public Politicas(RedDePetri rdp) {
        this.prioridades = new HashMap<>();
        this.rdp = rdp;

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
        if(eleccion==5 || eleccion==6){                     //si se elige la transicion 5 o 6 se decide entre una u otra de acuerdo a la cantidad de tokens..
            return resolverConflicto(rdp.getMarcaActual()); //...en CPU_buffer1 y CPU_buffer2
        }
        return eleccion;        //sino se retorna la otra transicion elegida
    }

    private int resolverConflicto(int[] marcado){   //resuelve conflicto entre T5 y T6 para mantener iguales la cantidad de tokens en c/u de los dos buffers
        if(marcado[2]<marcado[3]){                  //marcado[2] = CPU_buffer1 es alimentado por T5
            return 5;                               //marcado[3] = CPU_buffer2 es alimentado por T6
        }else return 6;
    }
}