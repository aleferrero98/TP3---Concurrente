import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class Main {

    private static final int CANT_EXECUTORS = 1;

    public static void main(String[] args) throws InterruptedException {
        Calendar today = Calendar.getInstance();
        System.out.print("Hora de comienzo: ");     //horas:minutos:segundos:milisegundos de inicio del programa
        System.out.println(today.get(Calendar.HOUR_OF_DAY)+":"+today.get(Calendar.MINUTE)+":"+today.get(Calendar.SECOND)+":"+today.get(Calendar.MILLISECOND));
        int duracionSeg, duracionMin;

       // Archivo archivo = new Archivo();

        int[] marcaInicial = {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1};

        int[][] matrizDeIncidencia = {     //T0  T1  T2  T3  T4  T5  T6  T7  T8  T9  T10
                                            { 0, 0, 0,-1, 0, 0, 0, 0, 1, 0, 0},
                                            { 0, 0, 0, 0,-1, 0, 0, 0, 0, 1, 0},
                                            { 0, 0, 0, 0, 0, 0, 1, 0,-1, 0, 0},
                                            { 0, 0, 0, 0, 0, 0, 0, 1, 0,-1, 0},
                                            { 0,-1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                            { 0, 0, 0, 1, 0, 0, 0, 0,-1, 0, 0},
                                            { 0, 0, 0, 0, 1, 0, 0, 0, 0,-1, 0},
                                            {-1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                                            { 1, 0, 0, 0, 0, 0,-1,-1, 0, 0, 0},
                                            { 0, 0,-1, 0, 0,-1, 1, 1, 0, 0, 0},
                                            { 0, 0,-1, 0, 0, 0, 0, 0, 0, 0, 1},
                                            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,-1}
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

        politica.addPrioridad(5 , 0);  // T5 y T6 tienen mayor prioridad, para descongestionar el buffer
        politica.addPrioridad(6 , 0);
        politica.addPrioridad(7 , 0);   //OPCIONAL: T7 y T8 mayor prioridad para que los nucleos esten mayor tiempo trabajando
        politica.addPrioridad(8 , 0);

        politica.addPrioridad(9 , 0);
        politica.addPrioridad(10, 0);

        Condicion condicionDeFinalizacion = new Condicion(false);
        Monitor monitor = new Monitor(new RedDePetri(marcaInicial, matrizDeIncidencia), politica,condicionDeFinalizacion);

        Executor[] executors = new Executor[CANT_EXECUTORS];
        for(int i =0; i<CANT_EXECUTORS; i++){
            executors[i] = new Executor(monitor);
            executors[i].start();
        }

        for (int i = 0; i < CANT_EXECUTORS; i++) {
            executors[i].join();
        }

        Calendar today2 = Calendar.getInstance();
        System.out.print("Hora de fin: ");   //horas:minutos:segundos:milisegundos de fin del programa
        System.out.println(today2.get(Calendar.HOUR_OF_DAY)+":"+today2.get(Calendar.MINUTE)+":"+today2.get(Calendar.SECOND)+":"+today2.get(Calendar.MILLISECOND));
        duracionSeg=today2.get(Calendar.SECOND)-today.get(Calendar.SECOND);  //resta de los segundos de la hora final menos la hora inicial
        duracionMin=today2.get(Calendar.MINUTE)-today.get(Calendar.MINUTE);  //resta de los minutos de la hora final menos la hora inicial
        if(duracionSeg<0){
            duracionSeg=60+duracionSeg;     //verifica que la resta no sea negativa
            duracionMin--;
        }
        if(duracionMin<0){
            duracionMin=60+duracionMin;
        }
        System.out.println("Duracion total del programa: "+ duracionMin + " min y " + duracionSeg + " seg." );


        System.out.println("Fin del hilo Main");

    }
}
