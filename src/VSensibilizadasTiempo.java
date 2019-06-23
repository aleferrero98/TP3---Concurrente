import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VSensibilizadasTiempo {

    private int[] Z;
    private ArrayList<TransicionConTiempo> transicionesConTiempo;

    public VSensibilizadasTiempo(List<Transicion> transiciones){
        transicionesConTiempo=new ArrayList<>();
        crearTransicionesConTiempo(transiciones);
        Z = new int[transiciones.size()];
    }
    private void setearZ(){
        for(int i=0;i<Z.length;i++){
            Z[i]=1;
        }
    }
    private void crearTransicionesConTiempo(List<Transicion> transiciones) {
        for(Transicion recorrer : transiciones){
            if(recorrer.esTemporal()){
                transicionesConTiempo.add((TransicionConTiempo) recorrer);
            }
        }
    }

    public void actualizar(){
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
