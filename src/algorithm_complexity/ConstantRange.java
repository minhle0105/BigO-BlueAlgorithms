package algorithm_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConstantRange {

    private static int findMax(List<Integer> nums) {
        int maxVal = nums.get(0);
        for (Integer i : nums) {
            if (i > maxVal) {
                maxVal = i;
            }
        }
        return maxVal;
    }

    private static int solution (List<Integer> list) {
        List<Integer> maxCanGoFromHere = new ArrayList<>();
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
                    maxCanGoFromHere.add(distance);
                    break;
                }
                else {
                    distance++;
                }
                if (j == list.size() - 1) {
                    maxCanGoFromHere.add(distance);
                }
            }
        }
        return findMax(maxCanGoFromHere);
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
