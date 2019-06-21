import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Main {
    private static final int CANT_PRODUCTORES = 5;  //5
    private static final int CANT_A_PRODUCIR = 96;  //10000
    private static final int CANT_CONSUMIDORES = 8;  //8
    private static final int CANT_A_CONSUMIR = 60;

    public static void main(String[] args) throws InterruptedException {
        Calendar today = Calendar.getInstance();
        System.out.print("Hora de comienzo: ");     //horas:minutos:segundos:milisegundos de inicio del programa
        System.out.println(today.get(Calendar.HOUR_OF_DAY)+":"+today.get(Calendar.MINUTE)+":"+today.get(Calendar.SECOND)+":"+today.get(Calendar.MILLISECOND));
        int duracionSeg, duracionMin;
        Archivo archivo = new Archivo();

        int[] marcaInicial = {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1};

        int[][] matrizDeIncidencia = {                 //T0  T1  T2  T3  T4  T5  T6  T7  T8  T9  T10
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

        Politicas politica = new Politicas();
        //SETEAR PRIORIDADES A CADA TRANSICION
        for(int i = 0; i < matrizDeIncidencia[0].length; i++){
            politica.addPrioridad(i , 0);
        }

        Productor productores[]=new Productor[CANT_PRODUCTORES];
        Consumidor consumidores[]=new Consumidor[CANT_CONSUMIDORES];
        Monitor monitor = new Monitor(new RedDePetri(marcaInicial, matrizDeIncidencia), politica);
        ArrayList<LinkedList<Dato>> buffers = new ArrayList<>();
        buffers.add(new LinkedList<Dato>());
        buffers.add(new LinkedList<Dato>());

        for (int i = 0; i < CANT_PRODUCTORES; i++) {
            productores[i] = new Productor(monitor,CANT_A_PRODUCIR, buffers);      // productores
            productores[i].start();
        }

        for (int i = 0; i < CANT_CONSUMIDORES; i++) {
            consumidores[i] = new Consumidor(monitor,CANT_A_CONSUMIR, buffers);      //consumidores
            consumidores[i].start();
        }

        Temporizador temp = new Temporizador(buffers, consumidores, archivo); //empieza el temporizador(cada 30 seg) a escribir el log

        for (int i = 0; i < CANT_PRODUCTORES; i++) {
            productores[i].join();
        }
        for (int i = 0; i < CANT_CONSUMIDORES; i++) {
            consumidores[i].join();
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

        try {
            archivo.escribirArchivo("Duracion total del programa: "+ duracionMin + " min y " + duracionSeg + " seg." );
            //  archivo.escribirArchivo("Tamaño máx del buffer de 10: "+ Productor.getMaxDeBuffer10());
            //  archivo.escribirArchivo("Tamaño máx del buffer de 15: "+ Productor.getMaxDeBuffer15());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Fin del hilo Main");

    }


}
