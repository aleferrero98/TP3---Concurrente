public class VectorQ {

    private int[] Q;
    private int[] marcaActual;

    public VectorQ(int[] marcaActual){
        this.marcaActual = marcaActual;
        Q = new int[marcaActual.length];
    }

    public void actualizar(){
        for(int i=0;i<marcaActual.length;i++){
            Q[i]=(marcaActual[i]==0)? 1 : 0;
        }
    }

    public int[] getQ() {
        return Q;
    }
}
