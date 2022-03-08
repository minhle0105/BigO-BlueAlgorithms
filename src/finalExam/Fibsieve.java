package finalExam;

import java.util.Scanner;

public class Fibsieve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = Integer.parseInt(sc.next());
        long[][] results = new long[test][2];
        for (int t = 0; t < test; t++) {
            long time = Long.parseLong(sc.next());
            long root = (long) Math.ceil(Math.sqrt(time * 1.0));
            long lack = (root * root) - time;
            long row;
            long column;
            if (lack < root) {
                row = root;
                column = lack + 1;
            }
            else {
                column = root;
                row = time - (root - 1) * (root - 1);
            }
            if (root % 2 == 0) {
                results[t] = new long[]{row, column};
            }
            else {
                results[t] = new long[]{column, row};
            }
        }
        for (int i = 0; i < results.length; i++) {
            System.out.println("Case " + (i + 1) + ": " + results[i][0] + " " + results[i][1]);
        }
        sc.close();
    }
}
