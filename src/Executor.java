public class Executor extends Thread {
    private Monitor monitor;
    private static boolean finalizar;

    public Executor(Monitor monitor){
        this.monitor = monitor;
        finalizar = false;
    }

    public static void finalizar(){
        finalizar = true;
    }

    @Override
    public void run() {
        while (!finalizar){
            try{
                monitor.dispararAlguno();
            }catch (Exception e){
                System.out.println("ocurrio: "+ e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
