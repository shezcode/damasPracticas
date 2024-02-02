import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.printTablero();
        System.out.print("Introduce la pieza a mover (Eje Y): ");
        String inputX = scanner.nextLine();
        System.out.print("Introduce la pieza a mover (Eje X): ");
        String inputY = scanner.nextLine();
        int[] posInicial = {Integer.parseInt(inputX), Integer.parseInt(inputY)};
        int[][] posValidas = tablero.devolverMovimientos(posInicial);
        System.out.println(Arrays.deepToString(posValidas));
    }
}