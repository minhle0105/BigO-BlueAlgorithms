package algorithm_complexity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Wrath {
    private static int solution(List<Integer> list) {
        int firstPointer = list.size() - 1;
        int secondPointer = firstPointer - 1;
        List<Integer> isKilled = new ArrayList<>();
        while (firstPointer >= 0) {
            while (secondPointer >= 0) {
                int j = secondPointer + 1;
                int i = firstPointer + 1;
                int Li = list.get(firstPointer);
                boolean canKill1 = j < i;
                boolean canKill2 = j >= i - Li;
                if (canKill1 && canKill2) {
                    isKilled.add(secondPointer);
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

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = Integer.parseInt(sc.next());
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            list.add(Integer.parseInt(sc.next()));
//        }
//        System.out.println(solution(list));
//        sc.close();
//    }
    @Test
    void firstTest() {
        List<Integer> list = Arrays.asList(0, 1, 0, 10);
        int expected = 1;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void secondTest() {
        List<Integer> list = Arrays.asList(0, 0);
        int expected = 2;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void thirdTest() {
        List<Integer> list = Arrays.asList(1,1,3,0,0,0,2,1,0,3);
        int expected = 3;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test4() {
        List<Integer> list = Arrays.asList(0,0,2,0,0,3,3,2,2,0);
        int expected = 2;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }
}
