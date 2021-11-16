package algorithm_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConstantRange {

    private static int solution (List<Integer> list) {
        int maxCanGo = 0;
        int minValInRange;
        int maxValInRange;
        int distance;
        for (int i = 0; i < list.size(); i++) {
            minValInRange = list.get(i);
            maxValInRange = list.get(i);
            distance = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) > maxValInRange) {
                    maxValInRange = list.get(j);
                }
                if (list.get(j) < minValInRange) {
                    minValInRange = list.get(j);
                }
                if (maxValInRange - minValInRange > 1) {
                    if (distance > maxCanGo) {
                        maxCanGo = distance;
                    }
                    break;
                }
                else {
                    distance++;
                }
                if (j == list.size() - 1) {
                    if (distance > maxCanGo) {
                        maxCanGo = distance;
                    }
                }
            }
        }
        return maxCanGo;
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
