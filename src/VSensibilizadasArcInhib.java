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


    public VSensibilizadasArcInhib(VectorQ Q){
        this.Q=Q;
        this.B = new int[matrizInhibicionH[0].length];
        this.op = new OperadorConMatrices();

    }
    public void actualizar(int[] marcaActual){ //Actualiza B a partir de Q y la matriz de inhibicion
        Q.actualizar(marcaActual);
        actualizarDentro();
        imprimir();
    }
    private void actualizarDentro(){

        B = op.complementar(op.multiplicar(op.traspuesta(matrizInhibicionH),Q.getQ()));  //B = H' * Q
    }

    public int[] getB() {
        return B;
    }

    public void imprimir(){
        System.out.print("B: ");
        for(int i=0; i< getB().length;i++){
            System.out.print(B[i]+" ");
        }
        System.out.println();
        System.out.println("---------------------");
    }

}
