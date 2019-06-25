import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class Main {


    public static void main(String[] args) throws InterruptedException {
        Calendar today = Calendar.getInstance();
        System.out.print("Hora de comienzo: ");     //horas:minutos:segundos:milisegundos de inicio del programa
        System.out.println(today.get(Calendar.HOUR_OF_DAY)+":"+today.get(Calendar.MINUTE)+":"+today.get(Calendar.SECOND)+":"+today.get(Calendar.MILLISECOND));
        int duracionSeg, duracionMin;

       // Archivo archivo = new Archivo();

        int[] marcaInicial = {0,0,0,1,1,0,0,0,1,0,0,1,0,0,1,0};

        int[][] Imas = {    //T0 T1 T2 T3 T4 T5 T6 T7 T8 T9 T10 T11 T12 T13 T14 T15
                            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            { 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                            { 0, 0,	0, 1, 0, 0,	0, 0, 0, 0,	0, 0, 0, 0,	0},
                            { 0, 0,	0, 0, 1, 0,	0, 0, 0, 0, 0, 0, 0, 0,	1},
                            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	0},
                            { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,	0},
                            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	0},
                            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1,	0},
                            { 0, 0,	0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,	1},
                            { 0, 0,	0, 0, 0, 0,	0,	0,	1,	0,	0,	0,	0,	0,	0},
                            { 0, 0,	0, 0, 0, 0,	0,	0,	0,	0,	0,	1,	0,	0,	0},
                            { 0, 0,	0, 0, 0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0},
                            { 0, 0,	0, 0, 0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0},
                            { 0, 0,	0, 0, 0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
        };

        int[][] Imenos = {     //T0  T1  T2  T3  T4  T5  T6  T7  T8  T9  T10
                {0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	1,	0,	0,	0,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
                {0,	0,	1,	0,	0,	0,	1,	1,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	1,	0,	0,	1,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	1,	0,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0},

        };

        RedDePetri rdp = new RedDePetri(marcaInicial, Imenos, Imas);
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
        politica.addPrioridad(5 , 0);
        politica.addPrioridad(6 , 0);
        politica.addPrioridad(7 , 0);
        politica.addPrioridad(8 , 0); // T8 y T9 tienen mayor prioridad, para descongestionar el buffer
        politica.addPrioridad(9 , 0);
        politica.addPrioridad(10, 0);
        politica.addPrioridad(11, 0);
        politica.addPrioridad(12, 0);
        politica.addPrioridad(13, 0);
        politica.addPrioridad(14, 0);

        Condicion condicionDeFinalizacion = new Condicion(false);
        Monitor monitor = new Monitor(rdp, politica,condicionDeFinalizacion);

        Obj_CPU cpu = new Obj_CPU(monitor);
        Obj_CPU2 cpu2 = new Obj_CPU2(monitor);
        Obj_Creador creador = new Obj_Creador(monitor);
        Obj_Nucleo1 nucleo1 = new Obj_Nucleo1(monitor);
        Obj_Nucleo2 nucleo2 = new Obj_Nucleo2(monitor);

        cpu.start();
        cpu2.start();
        creador.start();
        nucleo1.start();
        nucleo2.start();

        boolean condicion=true;
        while (condicion){
            if(condicionDeFinalizacion.getCondicion()){
                cpu.suspend();
                cpu2.suspend();
                creador.suspend();
                nucleo1.suspend();
                nucleo2.suspend();
                /*cpu.stop();
                cpu2.stop();
                creador.stop();
                nucleo1.stop();
                nucleo2.stop();*/
                condicion=false;
            }
        }


        /*cpu.join();
        cpu2.join();
        creador.join();
        nucleo1.join();
        nucleo2.join();*/



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
        System.out.println(Thread.currentThread());

    }
}
