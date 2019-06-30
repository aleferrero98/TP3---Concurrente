import java.util.Calendar;
import java.util.HashMap;


public class Main {

    //private static String path_matrices = "C:\\Users\\alejandro\\Documents\\GitHub\\TP3---Concurrente\\TP3-entregado-25-06\\Txt_Rdp";
    private static String path_matrices = "src\\main\\java\\matrices";
    private static String path_Imas = path_matrices+"\\Matriz_I+.txt";
    private static String path_Imenos = path_matrices+"\\Matriz_I-.txt";
    private static String path_H = path_matrices+"\\Matriz_H.txt";
    private static String path_marca = path_matrices+"\\Marca_Inicial.txt";

    public static void main(String[] args) throws InterruptedException {
        Calendar today = Calendar.getInstance();
        System.out.print("Hora de comienzo: ");     //horas:minutos:segundos:milisegundos de inicio del programa
        System.out.println(today.get(Calendar.HOUR_OF_DAY)+":"+today.get(Calendar.MINUTE)+":"+today.get(Calendar.SECOND)+":"+today.get(Calendar.MILLISECOND));
        int duracionSeg, duracionMin;
        long duracionMiliseg, milisegToday;
        milisegToday = today.getTimeInMillis();

        Archivo archivo = new Archivo();

        int[] marcaInicial;

        int[][] Imas;

        int[][] Imenos;

        int[][] matrizInhibicionH;

        //------------------  Lectura de matrices desde archivos!
        OperadorConMatrices op = new OperadorConMatrices();
        Archivo f = new Archivo();
        Imas = f.leerMatriz(path_Imas);
        Imenos = f.leerMatriz(path_Imenos);
        matrizInhibicionH = f.leerMatriz(path_H);
        marcaInicial = op.toVector(f.leerMatriz(path_marca));
        //------------------
        HashMap<Integer, TransicionConTiempo> transicionesConTiempo = new HashMap<>();
        TransicionConTiempo t1 = new TransicionConTiempo(0, 5);
        TransicionConTiempo t3 = new TransicionConTiempo(3, 50);
        TransicionConTiempo t12 = new TransicionConTiempo(12, 50);
        transicionesConTiempo.put(t1.getID(),t1);
        transicionesConTiempo.put(t3.getID(),t3);
        transicionesConTiempo.put(t12.getID(),t12);

        RedDePetri rdp = new RedDePetri(marcaInicial, Imenos, Imas,matrizInhibicionH, transicionesConTiempo, archivo);
        Politicas politica = new Politicas(rdp);

        //SETEAR PRIORIDADES A CADA TRANSICION
       /* for(int i = 0; i < matrizDeIncidencia[0].length; i++){
            politica.addPrioridad(i , 0);
        }
        */
        politica.addPrioridad(0 , 0);
        politica.addPrioridad(1 , 0);
        politica.addPrioridad(2 , 0);
        politica.addPrioridad(3 , 2);
        politica.addPrioridad(4 , 0);
        politica.addPrioridad(5 , 3);
        politica.addPrioridad(6 , 1);
        politica.addPrioridad(7 , 0);
        politica.addPrioridad(8 , 0);
        politica.addPrioridad(9 , 0);
        politica.addPrioridad(10, 1);
        politica.addPrioridad(11, 0);
        politica.addPrioridad(12, 2);
        politica.addPrioridad(13, 3);
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


        while (condicionDeFinalizacion.getCondicion() ==false){
                System.out.println("esperando");
                Thread.sleep(1000);
        }
                cpu.interrupt();
                cpu2.interrupt();
                creador.interrupt();
                nucleo1.interrupt();
                nucleo2.interrupt();

                rdp.verificarTinvariantes(monitor.getDisparos());
                System.out.println("Las transiciones sobrantes son: ");
                for(Integer i:monitor.getDisparos()){
                    System.out.print(i + " ");
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
        duracionMiliseg =today2.getTimeInMillis() - milisegToday;
        System.out.println("Duracion total del programa: "+ duracionMin + " min y " + duracionSeg + " seg." );
        System.out.println("Duracion total en miliseg: " + duracionMiliseg);
        System.out.println("Fin del hilo Main");


        System.exit(0);

    }

}
