public class OperadorConMatrices {

    public OperadorConMatrices() {
    }

    public int[][] and(int[][] a, int[][] b){
        if(a==null || b==null) throw new NullPointerException("matriz nula!");
        else if(a.length != b.length || a[0].length != b[0].length) throw new IndexOutOfBoundsException("diferente formato operacion imposible!");

        int[][] resultado = new int[a.length][a.length];
        for(int i = 0; i < a.length ; i++){
            resultado[i] = and(a[i], b[i]);
        }
        return resultado;
    }

    public int[] and(int[] a, int[] b){
        if(a==null || b==null) throw new NullPointerException("vector nulo!");
        else if(a.length != b.length) throw new IndexOutOfBoundsException("diferente formato operacion imposible!");

        int[] resultado = new int[a.length];
        for(int i = 0; i < a.length ; i++){
            resultado[i] = (a[i] != 0 && b[i] != 0)? 1 : 0;
        }
        return resultado;
    }

    public int multiplicar(int[] a, int[] b){
        if(a==null || b==null) throw new NullPointerException("vector nulo!");
        else if(a.length != b.length) throw new IndexOutOfBoundsException("diferente formato operacion imposible!");

        int resultado = 0;
        for(int i = 0; i < a.length ; i++){
            resultado+= a[i]*b[i];
        }
        return resultado;
    }

    public int[][] multiplicar(int[][] a, int[][] b){ //dim(a)=nxm dim(b)=mxk dim(r)=nxk
        if(a==null || b==null) throw new NullPointerException("matriz nula!");
        if(a[0].length != b.length) throw new IndexOutOfBoundsException("diferente formato operacion imposible!");

        int[][] resultado = new int[a.length][b[0].length];
        int[][] bt = traspuesta(b);
        for(int i = 0; i < a.length ; i++){
            for(int j = 0; j < bt.length ; j++){
                resultado[i][j] = multiplicar(a[i], bt[j]);
            }
        }
        return resultado;
    }

    private int[][] traspuesta(int[][] mat) {
        if(mat==null) throw new NullPointerException("matriz nula!");
        int[][] traspuesta = new int[mat[0].length][mat.length];
        for(int i = 0; i < mat.length ; i++){
            for(int j = 0; j < mat[0].length ; j++){
                traspuesta[i][j] = mat[j][i];
            }
        }
        return traspuesta;
    }


}
