import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Main {

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

        Politicas politica = new Politicas();
        //SETEAR PRIORIDADES A CADA TRANSICION
        for(int i = 0; i < matrizDeIncidencia[0].length; i++){
            politica.addPrioridad(i , 0);
        }
        politica.addPrioridad(5 , 1);  // T5 y T6 tienen mayor prioridad, para descongestionar el buffer
        politica.addPrioridad(6 , 1);

        Monitor monitor = new Monitor(new RedDePetri(marcaInicial, matrizDeIncidencia), politica);




        System.out.println("Fin del hilo Main");

    }


}
