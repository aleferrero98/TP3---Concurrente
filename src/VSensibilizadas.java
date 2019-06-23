import java.util.Calendar;
import java.util.List;

public class VSensibilizadas {

    private int[] E;
    private int[] marcaActual;
    private int[][] matrizDeIncidencia;
    private List<Transicion> transicionList;
    private int[] Eaux;

    public VSensibilizadas(int[][] matrizDeIncidencia, int[] marcaInicial, List<Transicion> transicionList){
        this.matrizDeIncidencia=matrizDeIncidencia;
        this.marcaActual=marcaInicial;
        this.transicionList = transicionList;
        crearE();

    }

    public void actualizar() {       //actualiza el vector de las transiciones que estan sensibilizadas por token y LA LISTA DE TRANSICIONES TAMBIEN
        for (int i = 0; i < E.length ; i++) {
                Eaux[i] = E[i];
            this.E[i] = (esSensibilizadoInterno(transicionList.get(i).getID())) ? 1 : 0; //actualiza vector
            this.transicionList.get(i).setSensibilizada(E[i]==1);   //setea campo de sensibilizado de transicion
        }
        sensibilizarTemporales(); //se toma la hora de inicio de sensibilizado de las transiciones temporales
    }

    private boolean esSensibilizadoInterno(int transicion){ //devuelve true si la transicion esta sensibilizada
        for(int i = 0; i < marcaActual.length; i++){
            if((marcaActual[i] + matrizDeIncidencia[i][transicion])  < 0){
                return false;
            }
        }
        return true;
    }
    private void crearE(){
        this.E = new int[matrizDeIncidencia[0].length];
        this.Eaux = new int[matrizDeIncidencia[0].length];
        actualizar();
    }
    public int[] getE() {
        return E;
    }

    private void sensibilizarTemporales(){
        for (int i = 0; i < E.length ; i++) {
            if (this.E[i] == 1 && transicionList.get(i).esTemporizada() && Eaux[i]==0) {     //si el vector de sensibilizadas por tokens tiene 1 y la transicion es temporal se toma el inicio
                transicionList.get(i).setInicioSensibilizado(Calendar.getInstance().getTimeInMillis());  //si la transicion estaba desensibilizada(por token), luego se sensibiliza y es temporal
            }                                                                               //tomo la hora de inicio en milisegundos
        }
    }
}
