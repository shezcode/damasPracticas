import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        //Posicion Inicial
        tablero.printTablero();

        while (!tablero.isGameOver()){
            turnoJugador(tablero);
            //evaluarPartida();
            tablero.increaseTurno();
            tablero.printTablero();
        }

    }

    public static void turnoJugador(Tablero tablero){

        if (tablero.getTurno() % 2 == 0){
            System.out.println("TURNO BLANCAS");
            tablero.setLetraJugador('B');
        } else {
            System.out.println("TURNO NEGRAS");
            tablero.setLetraJugador('N');
        }

        int[] posInicial;
        do {
            System.out.print("Introduce la pieza a mover (Eje Y): ");
            String inputX = scanner.nextLine();
            System.out.print("Introduce la pieza a mover (Eje X): ");
            String inputY = scanner.nextLine();
            posInicial = new int[] {Integer.parseInt(inputX), Integer.parseInt(inputY)};
            if (tablero.getTablero()[posInicial[0]][posInicial[1]] != tablero.getLetraJugador()){
                System.out.println("Input incorrecto, esa pieza no te corresponde! :(");
                System.out.println("-------------------------------------------------");
            }
        } while (tablero.getTablero()[posInicial[0]][posInicial[1]] != tablero.getLetraJugador());

        int[][] posValidas = tablero.devolverMovimientos(posInicial);
        if (posValidas.length == 0){
            tablero.setGameOver();
            return;
        }

        System.out.println("POSICIONES VALIDAS: ");
        System.out.println(Arrays.deepToString(posValidas));

        for (int i = 0; i < posValidas.length; i++){
            System.out.print("  " + (i + 1) + "      ");
        }

        System.out.println();

        //pedir movimiento
        System.out.println("Introduce tu movimiento: ");
        String mov = scanner.nextLine();
        int movNumero = Integer.parseInt(mov);
        int[] posFinal = posValidas[movNumero - 1];

        //registrar movimiento
        tablero.hacerMovimiento(posInicial, posFinal, posValidas);
        //System.out.println(Arrays.deepToString(tablero.posBlancas));

    }
}