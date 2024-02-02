import java.util.Arrays;

public class Utils {
    static boolean contains(int[][] array, int[] value){
        for (int[] fila : array){
            if (Arrays.equals(fila, value)){
                return true;
            }
        }
        return false;
    }
}
