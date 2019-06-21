public class VSensibilizadas {

    int[] E;
    int[] marcaActual;
    int[][] matrizDeIncidencia;

    public VSensibilizadas(int[][] matrizDeIncidencia, int[] marcaInicial){
        this.matrizDeIncidencia=matrizDeIncidencia;
        marcaActual=marcaInicial;
        crearE();
    }

    public void actualizarSensibilizadas() {       //actualiza el vector de las transiciones que estan sensibilizadas
        for (int i = 0; i < E.length ; i++) {
            this.E[i] = esSensibilizadoInterno(i) ? 1 : 0;
        }
    }

    private boolean esSensibilizadoInterno(int transicion){ //devuelve true si la transicion esta sensibilizada
        for(int i = 0; i < marcaActual.length; i++){
            if((marcaActual[i] + matrizDeIncidencia[i][transicion])  < 0){
                return false;
            }
        }
        return true;
    }
    private void crearE(){
        actualizarSensibilizadas();
    }
    public int[] getE() {
        return E;
    }

    public void sensibilizadasTemporales(){
        if(Transicion.esTemporal){}
    }
}
