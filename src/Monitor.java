import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitor {
    private Semaphore mutex=new Semaphore(1);  //semaforo binario de exclusion mutua
    private CVSemaforo[] VariablesDeCondicion;  //condiciones de sincronizacion de cada transicion
    private RedDePetri RdP;     //red que controla la logica del sistema
    private Politicas politica; //politicas que resuelven los conflictos de la red
    private Condicion condicionDeFinalizacion;
    private int tareasN1;
    private int tareasN2;


    public Monitor(RedDePetri red, Politicas politicas,Condicion condicion){
        this.RdP = red;
        this.VariablesDeCondicion = new CVSemaforo[RdP.getTransiciones().size()];  //se generan tantas variables de condicion como transiciones haya en la RdP
        this.politica = politicas;
        GenerarVarCond();
        this.condicionDeFinalizacion = condicion;
        this.tareasN1 = 0;
        this.tareasN2 = 0;
    }

    public void disparar(int transicion) throws IllegalDisparoException { //dispara una transicion
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!(RdP.esSensibilizada(transicion))){
            VariablesDeCondicion[transicion].Delay();       //bloqueo
        }
        this.RdP.disparar(transicion); //dispara la transicion actualizando asi el estado
        desbloquearUno();          //desbloqueo otro hilo
        mutex.release();        //devuelve mutex
    }


    private void desbloquearUno(){      //desbloquea un hilo de las colas de condicion cuya transicion esté sensibilizada
        ArrayList<Integer> desbloqueables = ColasAndSensibilizadas();
        if(!desbloqueables.isEmpty()){
            VariablesDeCondicion[politica.decidir(desbloqueables)].Resume();
        }
        return;
    }

    private ArrayList<Integer> ColasAndSensibilizadas() { //devuelve una lista de las transiciones sensibilizadas
        // que tienen hilos queriendo dispararlas
        ArrayList<Integer> desbloqueables = new ArrayList<>();
        for(int i = 0; i < this.VariablesDeCondicion.length; i++){
            if( !(VariablesDeCondicion[i].Empty()) && RdP.esSensibilizada(i)){
                desbloqueables.add(i);
            }
        }
        return desbloqueables;
    }

    private void GenerarVarCond(){ //crea tantas variables de condicion como cantidad de transiciones tiene la red de petri
        for(int i = 0; i < this.VariablesDeCondicion.length; i++){
            this.VariablesDeCondicion[i] = new CVSemaforo(this.mutex);
        }
    }

    public void dispararAlguno() throws IllegalDisparoException {
        { //dispara una transicion
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<Integer> transiciones = RdP.getSensibilizadas();
            int transicion = this.politica.decidir(transiciones);
            while(!(RdP.esSensibilizada(transicion))){
                VariablesDeCondicion[transicion].Delay();       //bloqueo
            }
            this.RdP.disparar(transicion); //dispara la transicion actualizando asi el estado
            actualizarTareasFinalizadas(transicion);
            desbloquearUno();          //desbloqueo otro hilo
            mutex.release();        //devuelve mutex
        }
    }

    private void actualizarTareasFinalizadas(int transicion) {
        if(transicion == 3) this.tareasN1++;
        if(transicion == 4) this.tareasN2++;
        if(tareasN1+tareasN2 >= 1000) this.condicionDeFinalizacion.setCondicion(true);
    }
}
