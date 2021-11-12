package list_string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BigSegment {

    private static int solution (List<List<Integer>> numbers) {
        int minNumber = numbers.get(0).get(0);
        int maxNumber = numbers.get(0).get(0);
        for (List<Integer> segment : numbers) {
            if (segment.get(0) < minNumber) {
                minNumber = segment.get(0);
            }
            if (segment.get(1) > maxNumber) {
                maxNumber = segment.get(1);
            }
        }
        int result = -1;
        for (List<Integer> segment : numbers) {
            if (segment.get(0) == minNumber & segment.get(1) == maxNumber) {
                result = numbers.indexOf(segment) + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = Integer.parseInt(sc.nextLine());
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            List<Integer> segments = new ArrayList<>();
            segments.add(Integer.parseInt(sc.next()));
            segments.add(Integer.parseInt(sc.next()));
            list.add(segments);
        }
        System.out.println(solution(list));
        sc.close();
    }
}
