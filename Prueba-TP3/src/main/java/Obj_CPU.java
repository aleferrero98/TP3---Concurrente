import java.util.ArrayList;

public class Obj_CPU extends Thread {
    private Monitor monitor;
    private static boolean finalizar;
    private ArrayList<Integer> T1T6;




    public Obj_CPU(Monitor monitor){
        this.monitor=monitor;
        T1T6 = new ArrayList<>();
        T1T6.add(1);
        T1T6.add(6);
        finalizar=false;
        this.setName("Obj_CPU");
    }
    @Override
    public void run(){
        while(!finalizar){
            try{
                monitor.disparar(7);
                monitor.disparar(2);
                monitor.disparar(T1T6);
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
