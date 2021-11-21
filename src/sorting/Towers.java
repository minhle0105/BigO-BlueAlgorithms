package sorting;

import java.util.*;

public class Towers {

    private static void solution(List<Integer> list) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer i : list) {
            if (hashMap.containsKey(i)) {
                hashMap.put(i, hashMap.get(i) + 1);
            }
            else {
                hashMap.put(i, 1);
            }
        }

        int numberOfTowers = hashMap.size();

        int tallestTower = 1;
        for (Integer i : hashMap.keySet()) {
            if (hashMap.get(i)> tallestTower) {
                tallestTower = hashMap.get(i);
            }

        }
        System.out.println(tallestTower + " " + numberOfTowers);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<Integer> lengths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lengths.add(Integer.parseInt(sc.next()));
        }
        solution(lengths);
        sc.close();
    }
}
