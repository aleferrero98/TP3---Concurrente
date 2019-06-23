import java.util.Calendar;

public class TransicionConTiempo extends TransicionComun {

    private int LimiteInf;
    private int LimiteSup;
    private long inicioSensibilizado;

    public TransicionConTiempo(int ID,int LimiteInf,int LimiteSup){
        super(ID);
        this.LimiteInf=LimiteInf;
        this.LimiteSup=LimiteSup;
        super.setEsTemporizada(true);
        setInicioSensibilizado(0);
    }

    public int getLimiteInf() {
        return LimiteInf;
    }

    public int getLimiteSup() {
        return LimiteSup;
    }

    public boolean estaAdentroDeVentana(){
        long tiempoSensibilizado = Calendar.getInstance().getTimeInMillis();
        if(inicioSensibilizado==0) return false;
        long diferencia = tiempoSensibilizado - inicioSensibilizado;
        if (diferencia>LimiteInf && diferencia<LimiteSup) {
            return true;
        }
        else{return false;}
    }
    @Override
    public void setInicioSensibilizado(long inicioSensibilizado) {
        this.inicioSensibilizado = inicioSensibilizado;
    }

}
