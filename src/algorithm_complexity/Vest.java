package algorithm_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vest {
    // 2 2
    // 1 5 9
    // 3 5 7
    private static List<List<Integer>> solution (List<Integer> human, List<Integer> vest, int a, int b) {
        List<List<Integer>> result = new ArrayList<>();
        int humanPointer = 0;
        int vestPointer = 0;
        while (humanPointer < human.size() && vestPointer < vest.size()) {
            while (vestPointer < vest.size() && human.get(humanPointer) - a > vest.get(vestPointer)) {
                vestPointer ++;
            }
            if (vestPointer < vest.size() && vest.get(vestPointer) <= human.get(humanPointer) + b ) {
                List<Integer> list = new ArrayList<>();
                list.add(humanPointer+1);
                list.add(vestPointer+1);
                result.add(list);
                vestPointer++;
            }
            humanPointer++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.next());
        int n = Integer.parseInt(sc.next());
        int a = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list1.add(Integer.parseInt(sc.next()));
        }
        for (int i = 0; i < n; i++) {
            list2.add(Integer.parseInt(sc.next()));
        }
        List<List<Integer>> result = solution(list1, list2, a, b);
        System.out.println(result.size());
        for (List<Integer> list : result) {
            StringBuilder s = new StringBuilder();
            for (Integer i : list) {
                s.append(i).append(" ");
            }
            s = new StringBuilder(s.substring(0, s.length() - 1));
            System.out.println(s);
        }



        sc.close();
    }

}
