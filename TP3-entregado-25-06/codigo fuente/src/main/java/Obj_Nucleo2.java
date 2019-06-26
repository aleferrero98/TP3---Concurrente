
public class Obj_Nucleo2 extends Thread {
    private Monitor monitor;
    private static boolean finalizar;

    public Obj_Nucleo2(Monitor monitor){
        this.monitor=monitor;
        finalizar=false;
        this.setName("Obj_Nucleo2");
    }
    @Override
    public void run(){
        while(!finalizar) {
            try {
                monitor.disparar(13);
                monitor.disparar(12);
            } catch (Exception e) {
                System.out.println("ocurrio: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public static void finalizar(){
        finalizar=true;
    }
}