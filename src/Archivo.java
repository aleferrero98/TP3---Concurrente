import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Archivo {

    private FileWriter archivo = null;
    private PrintWriter escritor = null;

    public Archivo() {

        try
        {
            archivo=new FileWriter("C:\\Users\\alejandro\\Desktop\\log.txt");

            escritor=new PrintWriter(archivo);

        } catch(Exception e) {     //Si existe un problema cae aqui

            System.out.println("Error al escribir: "+ e.getMessage());
        }
    }

    public void escribirArchivo(String datos) throws IOException {
        try {
            escritor.println(datos);
            escritor.flush();

        }catch(Exception e){
            throw new IOException("Error al escribir: "+ e.getMessage());
        }
    }

}