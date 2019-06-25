import java.util.ArrayList;
import java.util.List;

public class RedDePetri {

    private int[] marcaActual;
    private int[][] Imenos;
    private int[][] Imas;
    private VSensibilizadasExt Ex;
    private List<Transicion> transicionList;


    public RedDePetri(int[] marcaInicial, int[][] Imenos, int[][] Imas) {

        this.marcaActual = marcaInicial;
        this.Imenos = Imenos;
        this.Imas = Imas;
        crearExtendida();
        actualizarVectoresSensibilizadas();
    }

    private void crearExtendida() { //crea  Q, Z, E, B y el Ex, todos los vectores para ver si esta sensibilizada
        VectorQ Q = new VectorQ(marcaActual.length);
        this.transicionList = new ArrayList<Transicion>();
        for(int i = 0; i < Imenos[0].length; i++){   //se crean todas las transiciones(comunes o con tiempo)
            /*switch(i) {
                case 0:     //transicion temporal Arrival_rate
                    transicionList.add(new TransicionConTiempo(i,500,1000000000));  //limite superior muy grande
                    break;
                case 3:     //transicion temporal Service_rateN1
                    transicionList.add(new TransicionConTiempo(i,700,1000000000));
                    break;
                case 12:     //transicion temporal Service_rateN2
                    transicionList.add(new TransicionConTiempo(i,900,1000000000));
                    break;
                default:
                    transicionList.add(new TransicionComun(i));
                    break;
            }*/
            transicionList.add(new TransicionComun(i));   //SACAR ACA SI SE AGREGAN LAS TEMPORALES
        }
        VSensibilizadas E = new VSensibilizadas(this.marcaActual, this.transicionList, this.Imenos);
        VSensibilizadasTiempo Z = new VSensibilizadasTiempo(transicionList);
        VSensibilizadasArcInhib B = new VSensibilizadasArcInhib(Q);
        Ex = new VSensibilizadasExt(E,B,Z);
    }

    private void actualizarVectoresSensibilizadas() {       //actualiza el vector Ex y todos los vectores que lo componen (E, B y Z)
        Ex.actualizar(marcaActual);
    }


    public void disparar(int transicion) throws IllegalDisparoException {  //dispara una transicion modificando la marcaActual con la ec. generalizada
        OperadorConMatrices op = new OperadorConMatrices();

        if(esSensibilizada(transicion)){      //verifica si la transicion esta sensibilizada a partir de Ex
            int[] disparoSensibilizado;
            disparoSensibilizado = op.and(generarVectorDisparo(transicion),this.Ex.getEx());  //sigma and Ex
            imprimir(disparoSensibilizado, "Sigma");
         //   imprimir(marcaActual, "Mj");
            marcaActual = op.sumar(marcaActual, op.multiplicarXEscalar(op.multiplicar(Imenos, disparoSensibilizado),-1)); //Ecuacion de estado generalizada: Mj+1 = Mj + Imenos*(sigma and Ex)
          //  imprimir(marcaActual, "Mj+1");
            marcaActual = op.sumar(marcaActual, op.multiplicar(Imas, disparoSensibilizado)); //Ecuacion de estado generalizada: Mj+2 = Mj+1 + Imas*(sigma and Ex)
            imprimir(marcaActual, "Mj+2");
            actualizarVectoresSensibilizadas();
        }else{
            throw new IllegalDisparoException();
        }
        System.out.println("se dispara: "+ transicion);

    }

    private int[] generarVectorDisparo(int transicion){  //A partir del nro de transicion (ID) se genera un vector de disparo con todos ceros menos la transicion a disparar
        int[] vectorDisparo = new int[Imenos[0].length];
        for(int i=0; i<Imenos[0].length; i++){
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

    public boolean esTemporizada(int transicion){
        return transicionList.get(transicion).esTemporizada();
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