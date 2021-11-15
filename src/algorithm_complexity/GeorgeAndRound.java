package algorithm_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeorgeAndRound {

    private static int solution (List<Integer> exams, List<Integer> prepared) {
        int preparedPointer = 0;
        int examPointer = 0;
        while (preparedPointer < prepared.size() && examPointer < exams.size()) {
            int preparedAtPointer = prepared.get(preparedPointer);
            int examAtPointer = exams.get(examPointer);
            if (preparedAtPointer >= examAtPointer) {
                examPointer++;
            }
            preparedPointer++;
        }
        return exams.size() - examPointer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length1 = Integer.parseInt(sc.next());
        int length2 = Integer.parseInt(sc.next());
        List<Integer> exams = new ArrayList<>();
        List<Integer> prepared = new ArrayList<>();
        for (int i = 0; i < length1; i++) {
            exams.add(Integer.parseInt(sc.next()));
        }
        for (int i = 0; i < length2; i++) {
            prepared.add(Integer.parseInt(sc.next()));
        }

        System.out.println(solution(exams, prepared));
        sc.close();
    }
}
