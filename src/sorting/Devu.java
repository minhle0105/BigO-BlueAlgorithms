package sorting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Devu {

    private static BigDecimal solution(List<Integer> subjects, int hour) {
        BigDecimal result = new BigDecimal(0);
        Collections.sort(subjects);
        for (Integer subject : subjects) {
            result = result.add(BigDecimal.valueOf((long) hour * subject));
            if (hour > 1) {
                hour = hour - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int hour = Integer.parseInt(sc.next());
        List<Integer> subjects = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            subjects.add(Integer.parseInt(sc.next()));
        }
        System.out.println(solution(subjects, hour));
        sc.close();
    }
}
