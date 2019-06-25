public class Temporizador {
   /* private Archivo file;
    private ArrayList<LinkedList<Dato>> buffers;
    Consumidor consumidores[];

    public Temporizador(ArrayList<LinkedList<Dato>> buf, Consumidor consum[], Archivo archivo){
        file=archivo;
        buffers = buf;
        consumidores=consum;

        Timer timer = new Timer(true);

        TimerTask tarea = new TimerTask(){
            @Override
            public void run() {

                String cadena1 = "Cantidad de lugares ocupados del buffer de 15: " + buffers.get(0).size();
                String cadena2 = "Cantidad de lugares ocupados del buffer de 10: " + buffers.get(1).size();
                try {
                    file.escribirArchivo(cadena1);
                    file.escribirArchivo(cadena2);

                } catch (IOException e) {
                    System.out.println("Falló al escribir el archivo");
                    e.printStackTrace();
                }

                for (int n = 0; n < consumidores.length; n++) {

                    String cadena = "Estado Consumidor " + n + ": " + consumidores[n].getState();

                    try {

                        file.escribirArchivo(cadena);   //escribe el estado de los hilos consumidores en el archivo log


                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());

                    }
                }
            }
        };
        timer.schedule(tarea, 0, 30000);    //imprimo cada 30 seg

    }
*/
}


