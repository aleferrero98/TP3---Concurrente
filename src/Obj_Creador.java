import java.lang.reflect.Array;
import java.util.ArrayList;

public class Obj_Creador extends Thread {
    private Monitor monitor;
    private ArrayList<Integer> T5T6;
    private static boolean finalizar;

    public Obj_Creador(Monitor monitor){
        this.monitor = monitor;
        T5T6 = new ArrayList<>();
        T5T6.add(6);
        T5T6.add(7);
        finalizar=false;
    }

    @Override
    public void run(){
        while(!finalizar){
            try{
                monitor.disparar(0);
                monitor.disparar(T5T6);
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
