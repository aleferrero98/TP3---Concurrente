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

    public void disparar(int transicion) throws IllegalDisparoException {
        { //dispara una transicion
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(!(RdP.esSensibilizada(transicion))){  //si la transicion no es sensibilizada (extendida) se bloquea
                VariablesDeCondicion[transicion].Delay();       //bloqueo
            }
            if(RdP.esTemporizada(transicion)) dispararTemporizada(transicion);
            else RdP.disparar(transicion); //dispara la transicion actualizando asi el estado
            actualizarTareasFinalizadas(transicion);
            desbloquearUno();          //desbloqueo otro hilo
            mutex.release();        //devuelve mutex
        }
    }


    public void disparar(ArrayList<Integer> transiciones) throws IllegalDisparoException {
        { //elige y dispara una transicion
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int transicion = this.politica.decidir(transiciones); //la politica elige una de las transiciones que le piden que dispare
                                                                  //es decir realiza la resolucion del conflicto
            while(!(RdP.esSensibilizada(transicion))){  //si la transicion no es sensibilizada (extendida) se bloquea
                VariablesDeCondicion[transicion].Delay();       //bloqueo
            }
            if(RdP.esTemporizada(transicion)) dispararTemporizada(transicion);

            else RdP.disparar(transicion); //dispara la transicion actualizando asi el estado

            actualizarTareasFinalizadas(transicion);
            desbloquearUno();          //desbloqueo otro hilo
            mutex.release();        //devuelve mutex
        }
    }

    private void dispararTemporizada(int transicion) throws IllegalDisparoException {
        Transicion trans = RdP.getTransiciones().get(transicion);
        if(trans instanceof TransicionConTiempo){
            TransicionConTiempo t = (TransicionConTiempo) trans; //casteo para usar metodos de transicion con tiempo
            if(t.estaAdentroDeVentana()){
                RdP.disparar(transicion);
            }
            else if(t.estaAntes()){
                mutex.release();
                try {
                    Thread.sleep(t.cuantoDormir()); //duerme lo necesario para que al despertarse pueda disparar la temporizada
                    mutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dispararTemporizada(transicion); //llamada recursiba, en la mas interna deberia de entrar en el primer if y disparar la transicion
            }
            else throw new IllegalDisparoException("llego despues no se va a disparar nunca");
        }
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



    private void actualizarTareasFinalizadas(int transicion) {//lleva la cuenta de las tareas que se hacen en cada nucleo(service_rateN1 y N2)
        System.out.println("N1: "+tareasN1+"      N2: "+tareasN2);
        if(transicion == 3) this.tareasN1++;
        if(transicion == 4) this.tareasN2++;
        if(tareasN1+tareasN2 >= 100) this.condicionDeFinalizacion.setCondicion(true); //si se superan las 1000 tareas, se setea la condicion para que finalice el programa
    }//CAMBIAR 100 POR 1000 TAREAS EN TOTAL
}
