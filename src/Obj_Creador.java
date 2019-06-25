import java.lang.reflect.Array;
import java.util.ArrayList;

public class Obj_Creador extends Thread {
    private Monitor monitor;
    private ArrayList<Integer> T4T14;
    private static boolean finalizar;

    public Obj_Creador(Monitor monitor){
        this.monitor = monitor;
        T4T14 = new ArrayList<>();
        T4T14.add(4);
        T4T14.add(14);
        finalizar=false;
    }

    @Override
    public void run(){
        while(!finalizar){
            try{
                monitor.disparar(0);
                monitor.disparar(T4T14);
                //monitor.disparar(5);
            }
            catch (Exception e){
                System.out.println("ocurrio: "+ e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public static void finalizar(){
        finalizar=true;
    }
}
