package algorithm_complexity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AliceBobChoc {
    private static int[] solution(List<Integer> list) {
        int[] result = new int [2];
        int aliceCount = 0;
        int alicePointer = 0;
        int alice = 0;

        int bobPointer = list.size() - 1;
        int bob = 0;
        int bobCount = 0;

        while (alicePointer < bobPointer) {
            alice += list.get(alicePointer);
            bob += list.get(bobPointer);
            aliceCount++;
            bobCount++;
            alicePointer++;
            bobPointer--;
            if (alicePointer == bobPointer) {
                if (alice <= bob) {
                    aliceCount++;
                }
                else {
                    bobCount++;
                }
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


    @Test
    void test1() {
        List<Integer> list = Arrays.asList(2,9,8,2,7);
        int[] expected = solution(list);
        int[] actual = {2,3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void test2() {
        List<Integer> list = Arrays.asList(4, 3, 2, 8);
        int[] expected = solution(list);
        int[] actual = {3,1};
        Assertions.assertArrayEquals(expected, actual);
    }
}
