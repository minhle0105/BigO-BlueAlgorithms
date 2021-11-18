package algorithm_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AliceBobChoc {
    private static int[] solution(List<Integer> list) {
        if (list.size() == 1) {
            return new int[]{1,0};
        }
        if (list.size() == 0) {
            return new int[] {0,0};
        }
        int[] result = new int [2];
        int aliceCount = 0;
        int alicePointer = 0;
        int alice = list.get(alicePointer);

        int bobPointer = list.size() - 1;
        int bob = list.get(bobPointer);
        int bobCount = 0;

        while (alicePointer <= bobPointer) {
            alice--;
            bob--;
            if (alicePointer == bobPointer & (alice == 0 & bob == 0)) {
                aliceCount++;
                break;
            }
            if (alice == 0) {
                alicePointer++;
                aliceCount++;
                alice = list.get(alicePointer);
            }
            if (bob == 0) {
                bobPointer--;
                bobCount++;
                bob = list.get(bobPointer);
            }

        }
        result[0] = aliceCount;
        result[1] = bobCount;
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            list.add(Integer.parseInt(sc.next()));
        }
        int[] result = solution(list);
        StringBuilder s = new StringBuilder();
        for (int i : result) {
            s.append(i).append(" ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        System.out.println(s);
        sc.close();
    }
}