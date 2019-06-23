import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Main {

    static final int CANT_EXECUTORS = 1;

    public static void main(String[] args) throws InterruptedException {

        Archivo archivo = new Archivo();

        int[] marcaInicial = {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1};

        int[][] matrizDeIncidencia = {     //T0  T1  T2  T3  T4  T5  T6  T7  T8  T9  T10
                                            { 0,  0,  0,  0,  0,  1,  0, -1,  0,  0,  0},
                                            { 0,  0,  0,  0,  0,  0,  1,  0, -1,  0,  0},
                                            { 0,  0,  0,  1,  0, -1,  0,  0,  0,  0,  0},
                                            { 0,  0,  0,  0,  1,  0, -1,  0,  0,  0,  0},
                                            { 0, -1,  1,  0,  0,  0,  0,  0,  0,  0,  0},
                                            { 0,  0,  0,  0,  0, -1,  0,  1,  0,  0,  0},
                                            { 0,  0,  0,  0,  0,  0, -1,  0,  1,  0,  0},
                                            {-1,  0,  0,  1,  1,  0,  0,  0,  0,  0,  0},
                                            { 1,  0,  0, -1, -1,  0,  0,  0,  0,  0,  0},
                                            { 0,  0, -1,  1,  1,  0,  0,  0,  0,  0, -1},
                                            { 0,  0, -1,  0,  0,  0,  0,  0,  0,  1,  0},
                                            { 0,  1,  0,  0,  0,  0,  0,  0,  0, -1,  0}
        };

        RedDePetri rdp = new RedDePetri(marcaInicial, matrizDeIncidencia);
        Politicas politica = new Politicas(rdp);

        //SETEAR PRIORIDADES A CADA TRANSICION
       /* for(int i = 0; i < matrizDeIncidencia[0].length; i++){
            politica.addPrioridad(i , 0);
        }
        */
        politica.addPrioridad(0 , 0);
        politica.addPrioridad(1 , 0);
        politica.addPrioridad(2 , 0);
        politica.addPrioridad(3 , 0);
        politica.addPrioridad(4 , 0);

        politica.addPrioridad(5 , 1);  // T5 y T6 tienen mayor prioridad, para descongestionar el buffer
        politica.addPrioridad(6 , 1);
        politica.addPrioridad(7 , 1);   //OPCIONAL: T7 y T8 mayor prioridad para que los nucleos esten mayor tiempo trabajando
        politica.addPrioridad(8 , 1);

        politica.addPrioridad(9 , 0);
        politica.addPrioridad(10, 0);

        Condicion condicionDeFinalizacion = new Condicion(false);
        Monitor monitor = new Monitor(new RedDePetri(marcaInicial, matrizDeIncidencia), politica,condicionDeFinalizacion);

        Executor[] executors = new Executor[CANT_EXECUTORS];
        for(int i =0; i<CANT_EXECUTORS; i++){
            executors[i] = new Executor(monitor);
            executors[i].start();
        }


        System.out.println("Fin del hilo Main");

    }


}
