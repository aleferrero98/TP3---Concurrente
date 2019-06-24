public class VectorQ {

    private int[] Q;


    public VectorQ(int size){
        Q = new int[size];
    }

    public void actualizar(int[] marcaActual){       //Q es 0 si la plaza tiene 1 o mas tokens sino es 1
        for(int i=0;i<marcaActual.length;i++){
            Q[i]=(marcaActual[i]!=0)? 1 : 0;
        }
        imprimir();
    }

    public int[] getQ() {
        return Q;
    }

    public void imprimir(){
        System.out.print("Q: ");
        for(int i=0; i< getQ().length;i++){
            System.out.print(Q[i]+" ");
        }
        System.out.println();
        System.out.println("---------------------");
    }
}
