import java.util.Calendar;
import java.util.List;

public class VSensibilizadas {

    private int[] E;
    private int[] marcaActual;
    private int[][] Imenos;
    private List<Transicion> transicionList;
    private int[] Eaux;

    public VSensibilizadas(int[] marcaInicial, List<Transicion> transicionList, int[][] Imenos){

        this.Imenos = Imenos;
        this.transicionList = transicionList;
        crearE();

    }

    public void actualizar(int[] marcaActual) {       //actualiza el vector de las transiciones que estan sensibilizadas por token y LA LISTA DE TRANSICIONES TAMBIEN
       this.marcaActual = marcaActual;
        for (int i = 0; i < E.length ; i++) {
                Eaux[i] = E[i];
            this.E[i] = (esSensibilizadoInterno(i) ? 1 : 0); //actualiza vector
            this.transicionList.get(i).setSensibilizada(E[i]==1);   //setea campo de sensibilizado de transicion

        }
        sensibilizarTemporales(); //se toma la hora de inicio de sensibilizado de las transiciones temporales
        //imprimir(marcaActual,"marca actual");
        //imprimir();
    }

    private boolean esSensibilizadoInterno(int transicion){ //devuelve true si la transicion esta sensibilizada
        for(int i=0; i<Imenos.length; i++){
            if(Imenos[i][transicion]==1 && marcaActual[i]<1) { //se fija una transicion y se busca en la matriz de incidencia de salida a lo largo de toda una columna
                return false;                               //y se van variando las plazas(filas). Si se encuentra un uno es porque esa plaza tiene arco  hacia la transicion
            }                                        //entonces se busca esa plaza y se le pregunta su marca: si es menor que 1, la transicion no esta sensibilizada
        }
        return true;

    }

    private void crearE(){
        this.E = new int[Imenos[0].length];
        this.Eaux = new int[Imenos[0].length];

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

    public void imprimir(){
        System.out.print("E: ");
        for(int i=0; i< getE().length;i++){
            System.out.print(E[i]+" ");
        }
        System.out.println();
        System.out.println("---------------------");
    }
    public void imprimir(int[] a, String name){
        System.out.print(name+": ");
        for(int i=0; i< a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        System.out.println("---------------------");
    }
}
