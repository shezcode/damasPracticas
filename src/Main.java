import com.diogonunes.jcolor.Attribute;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Tablero tablero = new Tablero();

        System.out.println(colorize("PULSA F PARA RENDIRTE", Attribute.BRIGHT_RED_TEXT()));

        while (!tablero.isGameOver()){
            tablero.pintarTablero();
            turnoJugador(tablero);
            if (tablero.turnosSinComer > 3){
                System.out.println("MAS DE 3 TURNOS SIN COMER PIEZAS. JUEGO TERMINA.");
                tablero.setGameOver();
            }

            if (tablero.isGameOver()){
                break;
            }

            tablero.increaseTurno();
        }

    }

    public static void turnoJugador(Tablero tablero){

        if (tablero.getTurno() % 2 == 0){
            System.out.println("TURNO BLANCAS");
            tablero.setLetraJugador('B');
            tablero.hayMovimientos(tablero.posBlancas);
        } else {
            System.out.println("TURNO NEGRAS");
            tablero.setLetraJugador('N');
            tablero.hayMovimientos(tablero.posNegras);
        }


        int[] posInicial;
        do {
            System.out.print("Introduce la pieza a mover (Eje Y): ");
            String inputX = scanner.nextLine();
            System.out.print("Introduce la pieza a mover (Eje X): ");
            String inputY = scanner.nextLine();
            if (Objects.equals(inputX, "F") || Objects.equals(inputY, "F")){
                System.out.println("TE RINDES. PARTIDA TERMINA.");
                tablero.setGameOver();
                return;
            }
            posInicial = new int[] {Integer.parseInt(inputX), Integer.parseInt(inputY)};
            if (tablero.getTablero()[posInicial[0]][posInicial[1]] != tablero.getLetraJugador()){
                System.out.println("Input incorrecto, esa pieza no te corresponde! :(");
                System.out.println("-------------------------------------------------");
            }
        } while (tablero.getTablero()[posInicial[0]][posInicial[1]] != tablero.getLetraJugador());

        int[][] posValidas = tablero.devolverMovimientos(posInicial);

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
        tablero.evaluarPiezasRestantes();
    }
}