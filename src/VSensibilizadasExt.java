public class VSensibilizadasExt {
    private int [] Ex;
    private int [] B;
    private int [] Z;
    private int [] E;
    private OperadorConMatrices op;

    public VSensibilizadasExt(int size, int[] E,int[] B,int[] Z){
        this.Ex = new int[size];
        this.E = E;
        this.B = B;
        this.Z = Z;
        this.op = new OperadorConMatrices();

    }
    public void actualizar() {
        E.actualizar();
        B.actualizar();
        Z.actualizar();
        actualizarDentro();
    }
    private void actualizarDentro(){
        Ex = op.and(op.and(E,B),Z);
    }
}
