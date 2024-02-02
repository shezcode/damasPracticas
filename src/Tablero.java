import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tablero {
   private final int DIMENSION= 8;
   public char[][] tablero = new char[DIMENSION][DIMENSION];
   public int[][] posNegras = {
           {0, 1}, {0, 3}, {0, 5}, {0, 7},
           {1, 0}, {1, 2}, {1, 4}, {1, 6},
           {2, 1}, {2, 3}, {2, 5}, {2, 7}
   };

   public int[][] posBlancas = {
           {5, 0}, {5, 2}, {5, 4}, {5, 6},
           {6, 1}, {6, 3}, {6, 5}, {6, 7},
           {7, 0}, {7, 2}, {7, 4}, {7, 6},
   };

   public Tablero(){
      insertarL();
      rellenarPiezas();
   }

   public void insertarL(){
      for(int i = 0; i < DIMENSION; i++){
         for (int j = 0; j < DIMENSION; j++){
            tablero[i][j] = 'L';
         }
      }
   }

   public void insertPiezas(){
      for(int i = 0; i < DIMENSION; i++){
         for (int j = 0; j < DIMENSION; j++){
            int[] arr = {i, j};
            if (Utils.contains(posNegras, arr)){
               tablero[i][j] = 'N';
            }
            if (Utils.contains(posBlancas, arr)){
               tablero[i][j] = 'B';
            }
         }
      }
   }
   private void rellenarPiezas() {
      for (int i=0; i<DIMENSION; i++) {

         for (int j=0; j<DIMENSION; j++) {
            if (i%2 == 0 && j%2==1 && i<3) {
               tablero[i][j] = 'N';
            }

            if (i%2 == 1 && j%2 == 0 && i<3) {
               tablero[i][j] = 'N';
            }

            if (i%2 == 0 && j%2==1 && i>4) {
               tablero[i][j] = 'B';
            }

            if (i%2 == 1 && j%2 == 0 && i>4) {
               tablero[i][j] = 'B';
            }

         }
      }
   }

   void printTablero(){
       for (char[] fila : this.tablero){
           System.out.println(Arrays.toString(fila));
       }
       System.out.println();
   }

   int[][] devolverMovimientos(int[] posicionInicial){
      ArrayList<int[]> result = new ArrayList<>();

      //comprobar si posicion inicial es de Blancas
      if (tablero[posicionInicial[0]][posicionInicial[1]] == 'B'){
         if (tablero[posicionInicial[0] - 1][posicionInicial[1] - 1] == 'L'){
            int[] posicionValida = {posicionInicial[0] - 1, posicionInicial[1] - 1};
            result.add(posicionValida);
         }
         if (tablero[posicionInicial[0] - 1][posicionInicial[1] + 1] == 'L'){
            int[] posicionValida = {posicionInicial[0] - 1, posicionInicial[1] + 1};
            result.add(posicionValida);
         }
      }

      //comprobar si posicion inicial es de Negras
      if (tablero[posicionInicial[0]][posicionInicial[1]] == 'N'){
         if (tablero[posicionInicial[0] + 1][posicionInicial[1] - 1] == 'L'){
            int[] posicionValida = {posicionInicial[0] + 1, posicionInicial[1] - 1};
            result.add(posicionValida);
         }
         if (tablero[posicionInicial[0] + 1][posicionInicial[1] + 1] == 'L'){
            int[] posicionValida = {posicionInicial[0] + 1, posicionInicial[1] + 1};
            result.add(posicionValida);
         }
      }

      //pasar el ArrayList a int[][] y asi devolver las posiciones validas
      int[][] resultArray = new int[result.size()][2];
      for (int i = 0; i < result.size(); i++) {
         resultArray[i] = result.get(i);
      }

      return resultArray;
   }

   public char[][] getTablero() {
      return tablero;
   }

   public void setTablero(char[][] tablero) {
      this.tablero = tablero;
   }

   public int[][] getPosNegras() {
      return posNegras;
   }

   public void setPosNegras(int[][] posNegras) {
      this.posNegras = posNegras;
   }

   public int[][] getPosBlancas() {
      return posBlancas;
   }

   public void setPosBlancas(int[][] posBlancas) {
      this.posBlancas = posBlancas;
   }
}
