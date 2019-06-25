import java.util.ArrayList;

public class Obj_CPU2 extends Thread{
    private Monitor monitor;
    private static boolean finalizar;
    private ArrayList<Integer> T10T8;

    public Obj_CPU2(Monitor monitor){
        this.monitor=monitor;
        this.finalizar=false;
        T10T8 = new ArrayList<>();
        T10T8.add(10);
        T10T8.add(8);
    }

    @Override
    public void run(){
        while(!finalizar){
            try{
                monitor.disparar(11);
                monitor.disparar(9);
                monitor.disparar(T10T8);
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
