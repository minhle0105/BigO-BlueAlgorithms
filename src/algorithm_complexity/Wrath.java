package algorithm_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wrath {
    private static int solution (List<Integer> list) {
        int firstPointer = list.size() - 1;
        List<Integer> isKilled = new ArrayList<>();
        while (firstPointer >= 0) {
            int secondPointer = firstPointer - 1;
            while (secondPointer >= 0) {
                if (isKilled.contains(secondPointer)) {
                    secondPointer --;
                    continue;
                }
                int j = secondPointer + 1;
                int i = firstPointer + 1;
                int Li = list.get(firstPointer);
                boolean canKill1 = j < i;
                boolean canKill2 = j >= i - Li;
                if (canKill1 && canKill2) {
                    if (!isKilled.contains(secondPointer)) {
                        isKilled.add(secondPointer);
                    }
                }
                if (!canKill2) {
                    break;
                }
                secondPointer--;
            }
            firstPointer--;
        }
        return list.size() - isKilled.size();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(sc.next()));
        }
        System.out.println(solution(list));
        sc.close();
    }
}
