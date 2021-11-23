package sorting;

import java.util.*;

public class Contest {

    private static String solution(List<Integer> list) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        Map<Integer, List<Integer>> valueToIndex = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (!valueToIndex.containsKey(list.get(i))) {
                valueToIndex.put(list.get(i), new ArrayList<>());
            }
            valueToIndex.get(list.get(i)).add(i);
        }

        Collections.sort(list);
        Collections.reverse(list);

        for (Integer i : list) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            }
            else {
                map.put(i, 1);
            }
        }

        int rank = 1;
        Map<Integer, Integer> ratingsToRanking = new HashMap<>();
        for (Integer i : map.keySet()) {
            ratingsToRanking.put(i, rank);
            rank += map.get(i);
        }

        int[] results = new int[list.size()];
        for (Integer i : valueToIndex.keySet()) { // 3 5 3 4 5
            int rankOfThis = ratingsToRanking.get(i);
            for (int index : valueToIndex.get(i)) {
                results[index] = rankOfThis;
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i : results) {
            s.append(i).append(" ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        return s.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(sc.next()));
        }
        String result = solution(list);
        System.out.println(result);
        sc.close();
    }
}
