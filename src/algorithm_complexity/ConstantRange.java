package algorithm_complexity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ConstantRange {

    private static int solution (List<Integer> list) {
        int maxCanGo = 1;
        int firstPointer = 0;
        int secondPointer = 1;

        HashMap<Integer, Integer> hashSet = new HashMap<>();
        hashSet.put(list.get(firstPointer), 1);

        while (firstPointer < list.size()) {
            if (hashSet.containsKey(list.get(secondPointer))) {
                if (hashSet.size() == 1) {
                    hashSet.put(list.get(secondPointer), 1);
                }
                if (secondPointer < list.size() - 1) {
                    secondPointer++;
                }
            }
            else {
                if (hashSet.size() == 1) {
                    hashSet.put(list.get(secondPointer), 1);
                    secondPointer++;
                }
                else {
                    hashSet.remove(list.get(firstPointer));
                    hashSet.put(list.get(secondPointer), 1);
                    firstPointer++;

                    while (Math.abs(list.get(firstPointer) - list.get(secondPointer)) > 1) {
                        hashSet.remove(list.get(firstPointer));
                        firstPointer++;
                        hashSet.put(list.get(firstPointer), 1);
                    }
                }
            }
            if (secondPointer - firstPointer > maxCanGo) {
                maxCanGo = secondPointer - firstPointer;
            }
            if (secondPointer == list.size() - 1) {
                break;
            }
        }
        return maxCanGo + 1;
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

    @Test
    void test1() {
        List<Integer> list = Arrays.asList(1,2,3,3,2);
        int expected = 4;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2() {
        List<Integer> list = Arrays.asList(5,4,5,5,6,7,8,8,8,7,6);
        int expected = 5;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test3() {
        List<Integer> list = Arrays.asList(2,3,3);
        int expected = 3;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }
}
