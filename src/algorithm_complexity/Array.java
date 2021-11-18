package algorithm_complexity;

import java.util.*;

public class Array {
    // 4 2
    // 1 2 2 3 -> 1 2

    // 8 3
    // 1 1 2 2 3 3 4 5 -> 2 5

    // 7 4
    // 4 7 7 4 7 4 7

    private static int containsKElement(List<Integer> list) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer i : list) {
            hashMap.put(i, hashMap.getOrDefault(i, 1));
        }
        return hashMap.size();
    }
// 1 2 2 3      2
    private static int[] solution(List<Integer> list, int k) {

        int[] result = new int[]{-1, -1};
        int firstPointer = 0;
        int secondPointer = firstPointer + k - 1;
        while (firstPointer < list.size() - k + 1) {
            List<Integer> range = list.subList(firstPointer, secondPointer);
            int numberOfDistinctElements = containsKElement(range);
            if (numberOfDistinctElements < k) {
                secondPointer++;
            }
            else if (numberOfDistinctElements > k) {
                firstPointer++;
            }
            else {
                result[0] = firstPointer + 1;
                result[1] = secondPointer;
                firstPointer++;
            }
            if (secondPointer == list.size()) {
                firstPointer++;
                secondPointer = firstPointer + k - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            list.add(Integer.parseInt(sc.next()));
        }
        int[] result = solution(list, k);
        StringBuilder s = new StringBuilder();
        for (int i : result) {
            s.append(i).append(" ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        System.out.println(s);
        sc.close();
    }
}
