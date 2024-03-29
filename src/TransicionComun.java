import java.util.Calendar;

public class TransicionComun implements Transicion {
    private int ID;
    private boolean esTemporizada;
    private boolean esSensibilizada;

    public TransicionComun(int ID){
        this.ID=ID;         //nro entero que identifica a la transicion(se relaciona con la posicion de la transicion en los array)
        this.esTemporizada=false; //Si es true es temporal, si es false es comun
        this.esSensibilizada=false;
    }
    @Override
    public int getID(){
        return ID;
    }
    @Override
    public boolean esTemporizada(){
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
    public void setSensibilizada(boolean sensibilizar) {
        this.esSensibilizada = sensibilizar;
    }

    @Override
    public void setInicioSensibilizado(long inicioSensibilizado){
        throw new IllegalStateException("Esta transicion: "+this.ID+" no es temporizada");
    }
}
