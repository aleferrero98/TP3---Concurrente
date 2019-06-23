public class VSensibilizadasArcInhib {

    private static final int[][] matrizInhibicionH = {  //T0  T1  T2  T3  T4  T5  T6  T7  T8  T9  T10
                                                        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }};
    private VectorQ Q;
    private int[] B;
    private OperadorConMatrices op;
    private int[] marcaActual;

    public VSensibilizadasArcInhib(VectorQ Q,int[] marcaActual){
        this.Q=Q;
        this.B = new int[matrizInhibicionH[0].length];
        this.op = new OperadorConMatrices();
        this.marcaActual=marcaActual;
    }
    public void actualizar(){ //Actualiza B a partir de Q y la matriz de inhibicion
        Q.actualizar();
        actualizarDentro();
    }
    private void actualizarDentro(){

        op.multiplicar(op.traspuesta(matrizInhibicionH),Q.getQ(),B);  //B = H' * Q
    }

    public int[] getB() {
        return B;
    }

}
