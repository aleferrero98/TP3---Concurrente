import java.util.ArrayList;
import java.util.List;

public class VSensibilizadasTiempo {

    private int[] Z;
    private ArrayList<TransicionConTiempo> transicionesConTiempo; //lista de todas las transiciones de la RdP

    public VSensibilizadasTiempo(List<Transicion> transiciones){
        transicionesConTiempo=new ArrayList<>();
        crearTransicionesConTiempo(transiciones);
        Z = new int[transiciones.size()];   //contiene un arreglo de todas las transiciones: 1 cuando la transicion es temporal y esta dentro de su ventana de tiempo, y 0 en caso contrario
    }
    private void setearZ(){  // se pone en 1 para luego poner en 0 aquellas transiciones que estan fuera de su ventana de tiempo
        for(int i=0;i<Z.length;i++){    //las transiciones comunes tienen 1
            Z[i]=1;
        }
    }
    private void crearTransicionesConTiempo(List<Transicion> transiciones) { //crea un arreglo de transiciones con tiempo, a partir de todas las transiciones
        for(Transicion recorrer : transiciones){
            if(recorrer.esTemporizada()){
                transicionesConTiempo.add((TransicionConTiempo) recorrer);
            }
        }
    }

    public void actualizar(){ //pone en cero las transiciones temporales que estan fuera de su ventana de tiempo
        setearZ();
        for(TransicionConTiempo recorrer : transicionesConTiempo){
            if(!recorrer.estaAdentroDeVentana()){
                Z[recorrer.getID()]=0;
            }
        }
    }

    public int[] getZ() {
        return Z;
    }

}
