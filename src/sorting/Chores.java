package sorting;

import java.util.Arrays;
import java.util.Scanner;

public class Chores {

    private static int solution (int[] list, int a, int b) {
        Arrays.sort(list);
        return list[b] - list[b-1];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = Integer.parseInt(sc.next());
        int a = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());
        int[] list = new int[total];
        int count = 0;
        for (int i = 0; i < total; i++) {
            list[count++] = Integer.parseInt(sc.next());
        }
        System.out.println(solution(list, a, b));
        sc.close();
    }


}
