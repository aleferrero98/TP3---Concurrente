import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperadorConMatricesTest{
    int[][] matA = {{1,2,3},{3,2,1},{1,1,1},{0,0,0},{0,1,0}};
    int[][] matB = {{1,2,3,4,5},{0,0,0,0,0},{1,1,1,-1,-1}};

    int[] vectA = {1, 2, 3, 1, 2, 3};
    int[] vectB = {2, 1, 2, 1, 2, 1};
    int[] v1 = {1,0,1,0};
    int[] v2 = {1,1,0,0};
    int[][] m1 = {{1,1,1,1},{0,1,0,1},{0,0,0,0}};
    int[][] m2 = {{0,1,1,0},{0,0,1,1},{1,1,0,1}};
    int[] vectC = {1, 2, 3, 1, 2};
    private OperadorConMatrices op;

    @Before
    public void setUp() throws Exception {
       op = new OperadorConMatrices();
}

    @Test
    public void andVectores() {
        int[] esperado = {1,0,0,0};
        assertArrayEquals(esperado,op.and(v1,v2));
    }

    @Test
    public void andMatrices() {
        int[][] esperado = {{0,1,1,0},{0,0,0,1},{0,0,0,0}};
        assertArrayEquals(esperado,op.and(m1,m2));
    }

    @Test
    public void multiplicarVectores() {
        int esperado = 18;
        assertEquals(esperado,op.multiplicar(vectA,vectB));
    }

    @Test
    public void multiplicarMatrices() {
        int[][] esperado = {{4,5,6,1,2},{4,7,10,11,14},{2,3,4,3,4},{0,0,0,0,0},{0,0,0,0,0}};
        assertArrayEquals(esperado,op.multiplicar(matA,matB));

    }

    @Test
    public void sumarVectores(){
        int[] esperado = {3,3,5,2,4,4};
        assertArrayEquals(esperado,op.sumar(vectA,vectB));


    }

    @Test
    public void trasponerMatriz(){
        int[][] esperado = {{1,3,1,0,0},
                {2,2,1,0,1},
                {3,1,1,0,0}};
        assertArrayEquals(esperado,op.traspuesta(matA));

    }

    @Test
    public void trasponerVector(){
        int[][] esperado = {{1},{2},{3},{1},{2}};
        assertArrayEquals(esperado,op.traspuesta(vectC));
    }

    @Test
    public void toVector(){
        int[] esperado = vectC;
        int[][] matrizColumna = {{1},{2},{3},{1},{2}};
        assertArrayEquals(esperado,op.toVector(matrizColumna));
    }

    @Test
    public void toVector2(){
        int[] esperado = vectC;
        int[][] matrizFila = {{1,2,3,1,2}};
        assertArrayEquals(esperado,op.toVector(matrizFila));
    }

    @Test
    public void multiplicarMatrizVector(){
        int[] esperado = {10,11,8};
        int[] contenedor;
       contenedor= op.multiplicar(op.traspuesta(matA),vectC);
        assertArrayEquals(esperado,contenedor);
    }
}