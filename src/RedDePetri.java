import java.util.ArrayList;
import java.util.List;

public class RedDePetri {

    private int[] marcaActual;
    private int[][] matrizDeIncidencia;
   // private boolean[] transiciones;     EN LUGAR DE ESTO SE VE Ex
    private VSensibilizadasExt Ex;
    private List<Transicion> transicionList;


    public RedDePetri(int[] marcaInicial, int[][] matrizDeIncidencia) {

        this.marcaActual = marcaInicial;
        this.matrizDeIncidencia = matrizDeIncidencia;
        crearExtendida();
        actualizarVectoresSensibilizadas();
    }

    private void crearExtendida() {
        VectorQ Q = new VectorQ(marcaActual.length);
        this.transicionList = new ArrayList<Transicion>();
        for(int i = 0; i < matrizDeIncidencia[0].length; i++){   //se crean todas las transiciones(comunes o con tiempo)
            switch(i) {
                case 0:     //transicion temporal Arrival_rate
                    transicionList.add(new TransicionConTiempo(i,5,100000000));  //limite superior muy grande
                    break;
                case 7:     //transicion temporal Service_rateN1
                    transicionList.add(new TransicionConTiempo(i,7,1000000000));
                    break;
                case 8:     //transicion temporal Service_rateN2
                    transicionList.add(new TransicionConTiempo(i,9,1000000000));
                    break;
                default:
                    transicionList.add(new TransicionComun(i));
                    break;
            }
        }
        VSensibilizadas E = new VSensibilizadas(this.matrizDeIncidencia, this.marcaActual, this.transicionList);
        VSensibilizadasTiempo Z = new VSensibilizadasTiempo(transicionList);
    }

    private void actualizarVectoresSensibilizadas() {       //actualiza el vector Ex y todos los vectores que lo componen (E, B y Z)
        Ex.actualizar();
    }


    public void disparar(int transicion) {  //dispara una transicion modificando la marcaActual
        OperadorConMatrices op = new OperadorConMatrices();
        int[] contenedor = new int[marcaActual.length];
        if(Ex.getEx()[transicion]==1){      //si en el lugar de la transicion hay un 1 es porque dicha transicion esta sensibilizada
            op.multiplicar(matrizDeIncidencia, op.and(generarVectorDisparo(transicion),this.Ex.getEx()), contenedor);
            marcaActual = op.sumar(marcaActual, contenedor);
            actualizarVectoresSensibilizadas();
        }
    }

    private int[] generarVectorDisparo(int transicion){  //A partir del nro de transicion (ID) se genera un vector de disparo con todos ceros menos la transicion a disparar
        int[] vectorDisparo = new int[matrizDeIncidencia[0].length];
        for(int i=0; i<matrizDeIncidencia[0].length; i++){
            vectorDisparo[i]=0;         //reseteo el vector con todos ceros
        }
        vectorDisparo[transicion]=1;        //tiene 1 solo en la transicion que se desea disparar
        return vectorDisparo;
    }

    public List<Transicion> getTransiciones(){
        return transicionList;
    }

    public boolean esSensibilizada(int transicion){     //le pregunta a la transicion con tal subindice(ID) si esta sensibilizada
        return transicionList.get(transicion).esSensibilizada();
    }

    public int[] getMarcaActual() {
        return marcaActual;
    }
}