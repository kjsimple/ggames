import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arrays = Integer.parseInt(br.readLine());
        for (int i = 1; i <= arrays; i++) {
            int length = Integer.parseInt(br.readLine());
            int[] numbers = new int[length];
            for (int k = 0; k < length; k++) {
                numbers[k] = Integer.parseInt(br.readLine());
            }
            for (int m = 0; m < numbers.length; m++) {
                for (int m2 = 0; m2 < numbers.length; m2++) {

                }
            }
        }
    }
}
