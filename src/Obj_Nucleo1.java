
public class Obj_Nucleo1 extends Thread {
    private Monitor monitor;
    private static boolean finalizar;

    public Obj_Nucleo1(Monitor monitor){
        this.monitor=monitor;
        finalizar=false;
    }
    @Override
    public void run(){
        while(!finalizar) {
            try {
                monitor.disparar(5);
                monitor.disparar(3);
            } catch (Exception e) {
                System.out.println("ocurrio: " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.exit(1);
    }
    public static void finalizar(){
        finalizar=true;
    }
}
