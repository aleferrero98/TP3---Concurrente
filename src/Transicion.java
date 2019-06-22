import java.util.Calendar;

public interface Transicion {
    public int getID();

    public boolean esTemporal();

    public boolean esSensibilizada();

    public void setSensibilizadas(boolean sensibilizar);

    public void setInicioSensibilizado(Calendar inicioSensibilizado);
}
