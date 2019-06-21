public class TransicionComun implements Transicion {
    private int ID;
    private boolean esTemporizada;
    private boolean esSensibilizada;

    public TransicionComun(int ID){
        this.ID=ID;
        this.esTemporizada=false; //Si es true es temporal, si es false es comun
        this.esSensibilizada=false;
    }
    @Override
    public int getID(){
        return ID;
    }
    @Override
    public boolean esTemporal(){
        return esTemporizada;
    }
    @Override
    public boolean esSensibilizada(){
        return esSensibilizada;
    }

    protected void setEsTemporizada(boolean esTemporizada) {
        this.esTemporizada = esTemporizada;
    }

    @Override
    public void setSensibilizadas(boolean sensibilizar) {
        this.esSensibilizada = esSensibilizada;
    }
}
