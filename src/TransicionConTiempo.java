import java.util.Calendar;

public class TransicionConTiempo extends TransicionComun {

    private int limiteInf;
    private int limiteSup;
    private long inicioSensibilizado;

    public TransicionConTiempo(int ID,int LimiteInf,int LimiteSup){
        super(ID);
        this.limiteInf =LimiteInf;
        this.limiteSup =LimiteSup;
        super.setEsTemporizada(true);
        setInicioSensibilizado(0);
    }

    public int getLimiteInf() {
        return limiteInf;
    }

    public int getLimiteSup() {
        return limiteSup;
    }

    public boolean estaAdentroDeVentana(){
        long tiempoSensibilizado = Calendar.getInstance().getTimeInMillis();
        if(inicioSensibilizado==0) return false;
        long diferencia = tiempoSensibilizado - inicioSensibilizado;
        if (diferencia> limiteInf && diferencia< limiteSup) {
            return true;
        }
        else{return false;}
    }
    @Override
    public void setInicioSensibilizado(long inicioSensibilizado) {
        this.inicioSensibilizado = inicioSensibilizado;
    }

}
