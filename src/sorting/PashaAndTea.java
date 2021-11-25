package sorting;

import java.util.*;

public class PashaAndTea {
    private static float solution (List<Integer> cups, int n, int maxTea) {
        Collections.sort(cups);
        List<Integer> cupsForGirls = new ArrayList<>();
        List<Integer> cupsForBoys = new ArrayList<>();
        cupsForGirls = cups.subList(0, n);
        cupsForBoys = cups.subList(n, cups.size());
        float x = 0;
        int minCupGirl = cupsForGirls.get(0);
        int minCupBoys = cupsForBoys.get(0);
        x = minCupBoys < 2 * minCupGirl ? (float) minCupBoys / 2 : minCupGirl;
        x = Math.min(x, ((float) maxTea) / (3 * n));
        return x * 3 * n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int maxTea = Integer.parseInt(sc.next());
        List<Integer> cups = new ArrayList<>();
        for (int i = 0; i < 2*n; i++) {
            cups.add(Integer.parseInt(sc.next()));
        }
        System.out.println(solution(cups, n, maxTea));
        sc.close();
    }
}
