package sorting;

import java.util.*;

public class BusinessTrips {

    private static int solution(List<Integer> months, int k) {
        int result = 0;
        Collections.sort(months);
        int total = 0;
        for (int i = months.size() - 1; i >= 0; i--) {
            if (total >= k) {
                break;
            }
            else {
                total += months.get(i);
                result++;
            }
        }
        return total < k ? -1 : result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> months = new ArrayList<>();
        int k = Integer.parseInt(sc.next());
        for (int i = 0; i < 12; i++) {
            months.add(Integer.parseInt(sc.next()));
        }
        System.out.println(solution(months, k));
        sc.close();
    }
}
