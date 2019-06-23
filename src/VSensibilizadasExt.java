public class VSensibilizadasExt {

    private int[] Ex;
    private VSensibilizadasArcInhib B;
    private VSensibilizadasTiempo Z;
    private VSensibilizadas E;
    private OperadorConMatrices op;

    public VSensibilizadasExt(VSensibilizadas E, VSensibilizadasArcInhib B, VSensibilizadasTiempo Z) {
        this.Ex = new int[E.getE().length];
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
        //Ex = E and complemento(B) and Z
    private void actualizarDentro() {
        Ex = op.and(op.and(E.getE(), op.complementar(B.getB())), Z.getZ());
    }

    public int[] getEx() {
        return Ex;
    }
}