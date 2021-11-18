package algorithm_complexity;

import java.util.*;

public class ConstantRange {

    private static boolean count(List<Integer> list) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer i : list) {
            if (!hashMap.containsKey(i)) {
                hashMap.put(i, 1);
            }
        }
        return hashMap.size() <= 2;
    }

    private static int solution (List<Integer> list) {
        int maxCanGo = 1;
        int leftPointer = 0;
        int rightPointer = 1;

        HashMap<Integer, Integer> range = new HashMap<>();
        range.put(list.get(leftPointer), 1);

//        int countDistinct = range.size();

        while (leftPointer < list.size()) {
            if (range.containsKey(list.get(rightPointer))) {
                range.put(list.get(rightPointer), range.get(list.get(rightPointer)) + 1);
            }
            else {
                range.put(list.get(rightPointer), 1);
//                countDistinct++;
            }
            if (range.size() < 3) {
                if (rightPointer < list.size()) {
                    rightPointer++;
                }
                else {
                    if (rightPointer - leftPointer > maxCanGo) {
                        maxCanGo = rightPointer - leftPointer;
                    }
                    if (range.get(list.get(leftPointer)) == 1) {
                        range.remove(list.get(leftPointer));
//                    countDistinct--;
                    }
                    else {
                        range.put(list.get(leftPointer), range.get(list.get(leftPointer)) - 1);
                    }
                    leftPointer++;

                }
            }
            else {
                if (range.get(list.get(leftPointer)) == 1) {
                    range.remove(list.get(leftPointer));
//                    countDistinct--;
                }
                else {
                    range.put(list.get(leftPointer), range.get(list.get(leftPointer)) - 1);
                }
                leftPointer++;
            }
            // 1 2 3 3 2
            // 1 -> 1
            //

        }
        return maxCanGo;
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
