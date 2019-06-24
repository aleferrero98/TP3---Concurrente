public class Condicion {
    private boolean condicion;

    public Condicion(boolean condicion) {
        this.condicion = condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
        if(condicion){
            Obj_Nucleo1.finalizar();
            Obj_Nucleo2.finalizar();
            Obj_Creador.finalizar();
            Obj_CPU.finalizar();
        }
    }

}
