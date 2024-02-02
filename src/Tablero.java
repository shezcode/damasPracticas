import java.util.ArrayList;
import java.util.Arrays;

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

   public int turno = 0;
   public char letraJugador;

   private boolean gameOver = false;

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

   int[][] devolverMovimientos(int[] posicionInicial){
      ArrayList<int[]> result = new ArrayList<>();

      //comprobar si posicion inicial es de Blancas
      if (tablero[posicionInicial[0]][posicionInicial[1]] == 'B'){
         int i = posicionInicial[0] - 1;
         int j = posicionInicial[1] - 1;

         if (i >= 0 && j >= 0){

            if (tablero[i][j] == 'L'){
               int[] posicionValida = {i, j};
               result.add(posicionValida);
            }

         }

         if (tablero[i][posicionInicial[1] + 1] == 'L'){
            int[] posicionValida = {i, posicionInicial[1] + 1};
            result.add(posicionValida);
         }

      }

      //comprobar si posicion inicial es de Negras
      if (tablero[posicionInicial[0]][posicionInicial[1]] == 'N'){
         int i = posicionInicial[0] + 1;
         int j = posicionInicial[1] - 1;

         //evitamos borde tablero
         if (i < DIMENSION && j >= 0){

            if (tablero[i][j] == 'L'){
               int[] posicionValida = {i, j};
               result.add(posicionValida);
            }

            if (tablero[posicionInicial[0] + 1][posicionInicial[1] - 1] == 'B' && tablero[posicionInicial[0] + 2][posicionInicial[1] - 2] == 'L'){
               // si se llega aqui hay que eliminar la pieza Blanca y registrar el movimiento de la pieza Negra
               // AQUI FALTA PASAR LOS PARAMETROS CORRECTOS AL METODO Y COMPROBAR QUE FUNCIONE
               //comerPieza();
            }
         }

         if (j + 2 < DIMENSION){

            if (tablero[i][posicionInicial[1] + 1] == 'L'){
               int[] posicionValida = {i, posicionInicial[1] + 1};
               result.add(posicionValida);
            }

         }
      }

      //pasar el ArrayList a int[][] y asi devolver las posiciones validas
      int[][] resultArray = new int[result.size()][2];
      for (int i = 0; i < result.size(); i++) {
         resultArray[i] = result.get(i);
      }

      return resultArray;
   }


   public void hacerMovimiento(int[] posicionInicial, int[] posicionFinal, int[][] movimientosPosibles) {
      boolean estaDentro = false;
      for (int[] movimiento : movimientosPosibles) {
         int columnaMovimiento = movimiento[1];
         int filaMovimiento = movimiento[0];
         if (columnaMovimiento==posicionFinal[1] && filaMovimiento==posicionFinal[0]) {
            estaDentro = true;
            break;
         }
      }

      if (!estaDentro) {
         return;
      }

      int filaInicial = posicionInicial[0];
      int columnaInicial = posicionInicial[1];

      char fichaAMover = tablero[filaInicial][columnaInicial];

      int filaFinal = posicionFinal[0];
      int columnaFinal = posicionFinal[1];

      tablero[filaInicial][columnaInicial] = 'L';
      tablero[filaFinal][columnaFinal] = fichaAMover;

      for(int i = 0; i < posNegras.length; i++){
         if (Arrays.equals(posNegras[i], posicionInicial)){
            posNegras[i] = posicionFinal;
         }
      }

      for(int i = 0; i < posBlancas.length; i++){
         if (Arrays.equals(posBlancas[i], posicionInicial)){
            posBlancas[i] = posicionFinal;
         }
      }


   }

   public void comerPieza(int[] posicionInicial, int[] posicionFinal, int[][] movimientosPosibles){
      // COMER PIEZA

      // si hay una diferencia de dos filas y dos columnas, entonces ha pasado por encima
      // de una pieza

      int filaInicial = posicionInicial[0];
      int columnaInicial = posicionInicial[1];

      int filaFinal = posicionFinal[0];
      int columnaFinal = posicionFinal[1];

      if (columnaFinal - columnaInicial > 1) {

         int filaVictima = 0;
         int columnaVictima = 0;

         // ver si son negras (i.e., si la fila aumenta al moverse)
         if (filaInicial < filaFinal) {
            // si ha ido hacia la dcha, la columna aumenta
            filaVictima = filaInicial + 1;
         }
         // si es blanca, la columna disminuye una
         else if (filaInicial > filaFinal) {
            filaVictima = filaInicial - 1;
         }

         // si va a la derecha, la columna aumenta
         if (columnaInicial < columnaFinal) {
            columnaVictima = columnaFinal +1;
         }

         // si ha ido hacia la izq, la columna disminuye
         else if (columnaInicial > columnaFinal) {
            columnaVictima = columnaInicial -1;
         }

         int[] posicionVictima = new int[] {filaVictima, columnaVictima};

         // eliminar del tablero y de las posiciones la victima

         tablero[filaVictima][columnaVictima] = 'L';

         for(int i = 0; i < posBlancas.length; i++){
            if (Arrays.equals(posBlancas[i], posicionVictima)){
               posBlancas[i] = new int[] {9, 9};
            }
         }

         for(int i = 0; i < posNegras.length; i++){
            if (Arrays.equals(posNegras[i], posicionVictima)){
               posNegras[i] = new int[] {9, 9};
            }
         }
      }
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

   public int getTurno() {
      return turno;
   }

   public void increaseTurno() {
      this.turno++;
   }

   public boolean isGameOver() {
      return gameOver;
   }

   public void setGameOver() {
      this.gameOver = !this.gameOver;
   }

   public char getLetraJugador() {
      return letraJugador;
   }

   public void setLetraJugador(char letraJugador) {
      this.letraJugador = letraJugador;
   }

}
