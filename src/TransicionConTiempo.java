import java.util.Calendar;

public class TransicionConTiempo extends TransicionComun {

    private int LimiteInf;
    private int LimiteSup;
    private Calendar inicioSensibilizado;

    public TransicionConTiempo(int ID,int LimiteInf,int LimiteSup){
        super(ID);
        this.LimiteInf=LimiteInf;
        this.LimiteSup=LimiteSup;
        super.setEsTemporizada(true);
    }

    public int getLimiteInf() {
        return LimiteInf;
    }

    public int getLimiteSup() {
        return LimiteSup;
    }

    public boolean estaAdentroDeVentana(int LimiteInf, int LimiteSup){
        Calendar tiempoSensibilizado = Calendar.getInstance();
        long diferencia = tiempoSensibilizado.getTimeInMillis()-inicioSensibilizado.getTimeInMillis();
        if (diferencia>LimiteInf && diferencia<LimiteSup) {
            return true;
        }
        else{return false;}
    }
    @Override
    public void setInicioSensibilizado(Calendar inicioSensibilizado) {
        this.inicioSensibilizado = inicioSensibilizado;
    }

}
