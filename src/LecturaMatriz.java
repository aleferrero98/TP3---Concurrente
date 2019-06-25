import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LecturaMatriz {


    public LecturaMatriz(){

    }

    public void readTxt(int[][] matriz, String link){
        try {
            BufferedReader br = getBuffered(link);
            //leemos la primera linea
            String linea =  br.readLine(); //lee el texto hasta un salto de linea
            //contador
            int contador = 0;
            while(linea != null){
                String[] values = linea.split(" ");
                //recorremos el arrar de string
                for (int i = 0; i<values.length; i++) {
                    //se obtiene el primer entero en el arreglo de enteros
                    matriz[contador][i] = Integer.parseInt(values[i]);
                }
                contador++;
                linea = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getBuffered(String link){

        FileReader lector  = null;
        BufferedReader br = null;
        try {
            File Arch=new File(link);
            if(!Arch.exists()){
                System.out.println("No existe el archivo");
            }else{
                lector = new FileReader(link);
                br = new BufferedReader(lector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }
}
