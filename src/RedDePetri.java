import java.util.ArrayList;
import java.util.List;

public class RedDePetri {

    private int[] marcaActual;
    private int[][] matrizDeIncidencia;
    private VSensibilizadasExt Ex;
    private List<Transicion> transicionList;


    public RedDePetri(int[] marcaInicial, int[][] matrizDeIncidencia) {

        this.marcaActual = marcaInicial;
        this.matrizDeIncidencia = matrizDeIncidencia;
        crearExtendida();
        actualizarVectoresSensibilizadas();
    }

    private void crearExtendida() { //crea  Q, Z, E, B y el Ex, todos los vectores para ver si esta sensibilizada
        VectorQ Q = new VectorQ(marcaActual);
        this.transicionList = new ArrayList<Transicion>();
        for(int i = 0; i < matrizDeIncidencia[0].length; i++){   //se crean todas las transiciones(comunes o con tiempo)
            switch(i) {
                case 0:     //transicion temporal Arrival_rate
                    transicionList.add(new TransicionConTiempo(i,500,1000000000));  //limite superior muy grande
                    break;
                case 3:     //transicion temporal Service_rateN1
                    transicionList.add(new TransicionConTiempo(i,700,1000000000));
                    break;
                case 4:     //transicion temporal Service_rateN2
                    transicionList.add(new TransicionConTiempo(i,900,1000000000));
                    break;
                default:
                    transicionList.add(new TransicionComun(i));
                    break;
            }
        }
        VSensibilizadas E = new VSensibilizadas(this.matrizDeIncidencia, this.marcaActual, this.transicionList);
        VSensibilizadasTiempo Z = new VSensibilizadasTiempo(transicionList);
        VSensibilizadasArcInhib B = new VSensibilizadasArcInhib(Q,marcaActual);
        Ex = new VSensibilizadasExt(E,B,Z);
    }

    private void actualizarVectoresSensibilizadas() {       //actualiza el vector Ex y todos los vectores que lo componen (E, B y Z)
        Ex.actualizar();
    }


    public void disparar(int transicion) throws IllegalDisparoException {  //dispara una transicion modificando la marcaActual con la ec. generalizada
        OperadorConMatrices op = new OperadorConMatrices();
        int[] contenedor;
        if(esSensibilizada(transicion)){      //verifica si la transicion esta sensibilizada a partir de Ex
            contenedor = op.multiplicar(matrizDeIncidencia, op.and(generarVectorDisparo(transicion),this.Ex.getEx()));
            marcaActual = op.sumar(marcaActual, contenedor); //Ecuacion de estado generalizada: Mj+1 = Mj + I*(sigma and Ex)
            actualizarVectoresSensibilizadas();
        }else{
            throw new IllegalDisparoException();
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
        return (Ex.getEx()[transicion]!=0);
    }

    public int[] getMarcaActual() {
        return marcaActual;
    }

    public ArrayList<Integer> getSensibilizadas(){      //devuelve una lista de todas las transiciones sensibilizadas(comunes y temporales)
        ArrayList<Integer> sensibilizadas = new ArrayList<>();
        for(Transicion transicion: transicionList) {
            if(transicion.esSensibilizada()) sensibilizadas.add(transicion.getID());
        }
        return sensibilizadas;
    }
}