public class VectorQ {

    int [] Q;

    public VectorQ(int size){
        Q = new int[size];
    }

    public void actualizar(int[] marcaActual){
        for(int i=0;i<marcaActual.length;i++){
            if(marcaActual[i]!=0){
            Q[i]=0;
            }
        }
    }

    public int[] getQ() {
        return Q;
    }
}
