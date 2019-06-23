import java.util.Calendar;

public interface Transicion {
    public int getID();

    public boolean esTemporizada();

    public boolean esSensibilizada();

    public void setSensibilizada(boolean sensibilizar);

    public void setInicioSensibilizado(long inicioSensibilizado);
}
