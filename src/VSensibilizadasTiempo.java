import java.util.Calendar;

public class VSensibilizadasTiempo {
    int[] temporizadas;
    public VSensibilizadasTiempo(){
        temporizadas = new int[]{1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0};
    }

    public void tiempo(){
        Calendar today2 = Calendar.getInstance();
        int horas = today2.get(Calendar.HOUR_OF_DAY);
        int minutos = today2.get(Calendar.MINUTE);
        int segundos = today2.get(Calendar.SECOND);
        int milisegundos = today2.get(Calendar.MILLISECOND);
    }
}
