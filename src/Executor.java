public class Executor extends Thread {
    private Monitor monitor;
    private static boolean finalizar;

    public Executor(Monitor monitor){ //Hilo que ejecuta las transiciones
        this.monitor = monitor;
        finalizar = false;
    }

    public static void finalizar(){
        finalizar = true;
    }

    @Override
    public void run() {     //dispara alguna transicion de acuerdo a la prioridad y si esta sensibilizada
        while (!finalizar){ //finaliza cuando se hayan ejecutado una cierta cantidad de tareas
            try{
                monitor.dispararAlguno();
            }catch (Exception e){
                System.out.println("ocurrio: "+ e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
