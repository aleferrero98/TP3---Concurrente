import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RedDePetri {

    private int[] marcaActual;
    private int[][] Imenos;
    private int[][] Imas;

    private int[][] H;
    private int[] Ext;
    private int[] E;
    private int[] Eaux;
    private int[] B;
    private int[] Q;
    private int[] temp;
    private OperadorConMatrices op;
    private HashMap<Integer, TransicionConTiempo> tConTiempo; //aca se lleva Z en realidad


    public RedDePetri(int[] marcaInicial, int[][] Imenos, int[][] Imas, int[][] H, HashMap<Integer, TransicionConTiempo> tConTiempo) {

        this.marcaActual = marcaInicial;
        this.Imenos = Imenos;
        this.Imas = Imas;
        this.op = new OperadorConMatrices();
        this.H = op.traspuesta(H); //ya la guarda como traspuesta para agilizar los calculos
        this.tConTiempo = tConTiempo;
        crearExtendida();
    }

    private void crearExtendida() { //crea  Q, Z, E, B y el Ex, todos los vectores para ver si esta sensibilizada
        this.Q = op.and(marcaActual,marcaActual); // vector de marcas binario
        crearE();
        crearB();
        crearTemp();
        actualizarExt();
    }

    private void actualizarExt() {
        actualizarE();
        actualizarB();
        actualizarTemporizadas();
        this.Ext =op.and(E,B);
    }


    private void crearE() {
        this.E = new int[Imenos[0].length];
        this.Eaux = new int[E.length];
        for (int i = 0; i < Eaux.length ; i++) {
            Eaux[i] = 0;
        }
        actualizarE();
    }

    private void actualizarE() {
        for (int i = 0; i < E.length ; i++) {
            Eaux[i] = E[i];
            this.E[i] = (sensibilizadaComun(i) ? 1 : 0); //actualiza vector
        }
    }

    private boolean sensibilizadaComun(int i){
        int[] mprueba = op.sumar(marcaActual, op.multiplicarXEscalar(op.multiplicar(Imenos, generarVectorDisparo(i)), -1));
        for(int u = 0 ; u < mprueba.length; u++){
            if(mprueba[u] < 0) return false;
        }
        return true;
    }

    private void crearB() {
        this.B = new int[H.length];
        actualizarB();
    }

    private void actualizarB() {
        this.Q = op.and(marcaActual,marcaActual); // vector de marcas binario
        this.B = op.complementar(op.multiplicar(H,Q));  //B = not(H' * Q)
    }

    private void crearTemp() {
        this.temp = new int[Imenos[0].length]; //crea vector que tiene 1 si la transicion es temporizada y cero sino
        for(int i = 0; i < temp.length; i++){
            if(tConTiempo.containsKey(i)) temp[i] = 1;
            else temp[i] = 0;
        }
        actualizarTemporizadas();
    }

    private void actualizarTemporizadas() {
        for(Integer i : tConTiempo.keySet()){
            if(Eaux[i] == 0 && E[i] == 1){ //se sensibilizo
                tConTiempo.get(i).setInicioSensibilizado();
            }
        }
    }


    public int getCantTransiciones(){
        return Imenos[0].length;
    }

    public TransicionConTiempo getConTiempo(int transicion){
        return tConTiempo.get(transicion);
    }

    public void disparar(int transicion) throws IllegalDisparoException {  //dispara una transicion modificando la marcaActual con la ec. generalizada

        if(esSensibilizada(transicion)){      //verifica si la transicion esta sensibilizada a partir de Ext
            int[] disparoSensibilizado = generarVectorDisparo(transicion); //sigma
            imprimir(disparoSensibilizado, "Sigma");
            imprimir(marcaActual, "Mj");
            marcaActual = op.sumar(marcaActual, op.multiplicarXEscalar(op.multiplicar(Imenos, disparoSensibilizado),-1)); //Ecuacion de estado generalizada: Mj+1 = Mj + Imenos*(sigma and Ex)
            imprimir(marcaActual, "Mj+1");
            marcaActual = op.sumar(marcaActual, op.multiplicar(Imas, disparoSensibilizado)); //Ecuacion de estado generalizada: Mj+2 = Mj+1 + Imas*(sigma and Ex)
            imprimir(marcaActual, "Mj+2");
            imprimir(Ext, "Ext");
            imprimir(E, "E");
            imprimir(B, "B");
            actualizarExt();
        }else{
            throw new IllegalDisparoException();
        }
    }

    private int[] generarVectorDisparo(int transicion){  //A partir del nro de transicion (ID) se genera un vector de disparo con todos ceros menos la transicion a disparar
        int[] vectorDisparo = new int[Imenos[0].length];
        for(int i=0; i<Imenos[0].length; i++){
            vectorDisparo[i]=0;         //reseteo el vector con todos ceros
        }
        vectorDisparo[transicion]=1;        //tiene 1 solo en la transicion que se desea disparar
        return vectorDisparo;
    }

    public boolean esSensibilizada(int transicion){     //le pregunta a la transicion con tal subindice(ID) si esta sensibilizada
        return (Ext[transicion]!=0);
    }

    public int[] getMarcaActual() {
        return marcaActual;
    }

    public ArrayList<Integer> getSensibilizadas(){      //devuelve una lista de todas las transiciones sensibilizadas(comunes y temporales)
        ArrayList<Integer> sensibilizadas = new ArrayList<>();
        for(int i = 0; i < E.length; i++) {
            if(esSensibilizada(i)) sensibilizadas.add(i);
        }
        return sensibilizadas;
    }

    public boolean esTemporizada(int transicion){
        return (temp[transicion] == 1);
    }

    public void imprimir(int[] a, String name){
        System.out.println(name+": ");
        for(int i=0; i< a.length;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        System.out.println("---------------------");
    }

    public void imprimir(int[][] a, String name){
        System.out.print(name+": ");
        for(int i=0; i< a.length;i++){
            imprimir(a[i], "");
        }
        System.out.println();
        System.out.println("---------------------\n");
    }
}