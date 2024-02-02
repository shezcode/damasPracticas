import java.util.Arrays;

public class Tablero {
   private final int DIMENSION= 8;
   public char[][] tablero = new char[DIMENSION][DIMENSION];
   private int[][] posNegras = {
           {0, 1}, {0, 3}, {0, 5}, {0, 7},
           {1, 0}, {1, 2}, {1, 4}, {1, 6},
           {2, 1}, {2, 3}, {2, 5}, {2, 7}
   };
   private int[][] posBlancas = {
           {5, 0}, {5, 2}, {5, 4}, {5, 6},
           {6, 1}, {6, 3}, {6, 5}, {6, 7},
           {7, 0}, {7, 2}, {7, 4}, {7, 6},
   };
   public Tablero(){
      insertarL();
      insertPiezas();
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

   void printTablero(){
       for (char[] fila : this.tablero){
           System.out.println(Arrays.toString(fila));
       }
       System.out.println();
   }

}
