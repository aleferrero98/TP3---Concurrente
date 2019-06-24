public class Obj_CPU extends Thread {
    private Monitor monitor;
    private static boolean finalizar;
    public Obj_CPU(Monitor monitor){
        this.monitor=monitor;
        finalizar=false;
    }
    @Override
    public void run(){
        while(!finalizar){
            try{
                monitor.disparar(10);
                monitor.disparar(2);
                monitor.disparar(5);
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
