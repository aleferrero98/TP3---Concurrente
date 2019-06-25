public class VSensibilizadasExt {

    /*
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

    public void actualizar(int[] marcaActual) {
        E.actualizar(marcaActual);
        B.actualizar(marcaActual);
        Z.actualizar();
        actualizarDentro();
        //imprimir();
    }
        //Ex = E and complemento(B) and Z
    private void actualizarDentro() {
        Ex = op.and(op.and(E.getE(),B.getB()), Z.getZ());
    }

    public int[] getEx() {
        return Ex;
    }

    public void imprimir(){
        System.out.print("Ex: ");
        for(int i=0; i< getEx().length;i++){
            System.out.print(Ex[i]+" ");
        }
        System.out.println();
        System.out.println("---------------------");
    }

     */
}