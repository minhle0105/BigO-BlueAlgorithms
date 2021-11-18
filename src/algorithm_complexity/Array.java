package algorithm_complexity;

import java.util.*;

public class Array {
    private static int[] solution(List<Integer> list, int k) {

        int[] result = new int[]{-1, -1};
        int firstPointer;
        int secondPointer;
        int maxLength = list.size();

        HashMap<Integer, Integer> hashMap;

        for (firstPointer = 0; firstPointer < list.size(); firstPointer++) {
            hashMap = new HashMap<>();
            hashMap.put(list.get(firstPointer), 1);
            secondPointer = firstPointer + 1;
            while (secondPointer < list.size() - k + 1) {
                if (!hashMap.containsKey(list.get(secondPointer))) {
                    hashMap.put(list.get(secondPointer), 1);
                }
                if (hashMap.size() == k) {
                    if (secondPointer - firstPointer < maxLength) {
                        result[0] = firstPointer + 1;
                        result[1] = secondPointer + 1;
                    }
                    break;
                }
                secondPointer++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
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
