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
        marcaActual=marcaInicial;
        this.transicionList = transicionList;
        crearE();

    }

    public void actualizar() {       //actualiza el vector de las transiciones que estan sensibilizadas
        for (int i = 0; i < E.length ; i++) {

            this.E[i] = esSensibilizadoInterno(transicionList.get(i).getID()) ? 1 : 0;
        }
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

    public void sensibilizarTemporales(){
        for (int i = 0; i < E.length ; i++) {
            if (this.E[i] == 1 && transicionList.get(i).esTemporal()) {     //si el vector de sensibilizadas por tokens tiene 1 y la transicion es temporal se toma el inicio
                transicionList.get(i).setInicioSensibilizado(Calendar.getInstance());
            }
        }
    }
}
