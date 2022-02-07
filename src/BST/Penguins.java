package BST;

import java.util.Scanner;
import java.util.TreeMap;

public class Penguins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        sc.nextLine();
        String[] data = new String[n];
        for (int i = 0; i < n; i++) {
            String[] nextPenguin = sc.nextLine().split(" ");
            data[i] = nextPenguin[0];
        }
        TreeMap<String, Integer> map = new TreeMap<>();
        for (String d : data) {
            if (map.containsKey(d)) {
                map.put(d, map.get(d) + 1);
            }
            else {
                map.put(d, 1);
            }
        }
        String result = "";
        int maxVal = Integer.MIN_VALUE;
        for (String p : map.keySet()) {
            if (map.get(p) > maxVal) {
                maxVal = map.get(p);
                result = p;
            }
        }
        System.out.println(result + " Penguin");
        sc.close();
    }
}
