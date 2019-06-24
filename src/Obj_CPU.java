import java.util.ArrayList;

public class Obj_CPU extends Thread {
    private Monitor monitor;
    private static boolean finalizar;
    private ArrayList<Integer> T1T5;




    public Obj_CPU(Monitor monitor){
        this.monitor=monitor;
        T1T5 = new ArrayList<>();
        T1T5.add(1);
        T1T5.add(5);
        finalizar=false;
    }
    @Override
    public void run(){
        while(!finalizar){
            try{
                monitor.disparar(10);
                monitor.disparar(2);
                monitor.disparar(T1T5);
            }catch(Exception e){
                System.out.println("ocurrio: "+ e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public static void finalizar(){
        finalizar=true;
    }
}
