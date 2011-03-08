
/**
 *
 */
public class Main {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 216 + 1; i++) {
            int m = i * i;
            for (int j = 1; ; j++) {
                int h = j * j * j;
                if (h == m) {
                    System.out.println("i = " + i + "; j = " + j);
                } else if (h > m) {
                    break ;
                }
            }
        }
    }
}
