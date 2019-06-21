public class VSensibilizadasExt {
    private int [] Ex;
    private VSensibilizadasArcInhib B;
    private VSensibilizadasTiempo Z;
    private VSensibilizadas E;
    private OperadorConMatrices op;

    public VSensibilizadasExt(int size, VSensibilizadas E,VSensibilizadasArcInhib B,VSensibilizadasTiempo Z){
        this.Ex = new int[size];
        this.E = E;
        this.B = B;
        this.Z = Z;
        this.op = new OperadorConMatrices();

    }
    public void actualizar() {
        actualizar();
        B.actualizar();
        Z.actualizar();
        actualizarDentro();
    }
    private void actualizarDentro(){
        Ex = op.and(op.and(E.getE(),B.getB()),Z.getZ());
    }
}
