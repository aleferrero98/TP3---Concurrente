public class Condicion {
    private boolean condicion;

    public Condicion(boolean condicion) {
        this.condicion = condicion;
    }

    public void setCondicion(boolean condicion) {
        this.condicion = condicion;
        if(condicion) Executor.finalizar();
    }

}
