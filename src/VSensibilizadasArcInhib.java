public class VSensibilizadasArcInhib {

    private static final int[][] matrizInhibicionH = {                 //T0  T1  T2  T3  T4  T5  T6  T7  T8  T9  T10
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
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    private int[] Q;
    private int[] B;
    private OperadorConMatrices op;

    public VSensibilizadasArcInhib(int[] Q){
        this.Q=Q;
        this.op = new OperadorConMatrices();
    }
    public void actualizar(){
        Q.actualizar();
        B.actualizarDentro();
    }
    private void actualizarDentro(){
        B=op.multiplicar(matrizInhibicionH,Q);
    }
}
