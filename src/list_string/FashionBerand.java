package list_string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FashionBerand {

    private static boolean checkInput(List<Integer> list, int k) {
        if (k == 1) {
            return list.get(0) == 1;
        }
        int count = 0;
        for (Integer i : list) {
            if (i == 0) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        }
        else return count == 1;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        int k = sc.nextInt();
        int i = 0;
        while (i < k) {
            list.add(sc.nextInt());
            i++;
        }
        boolean result = checkInput(list, k);
        if (result) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
        sc.close();
    }
}
