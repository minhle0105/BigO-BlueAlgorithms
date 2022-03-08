package finalExam;

import java.util.*;

public class MostCommonSuffix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> suffixFrequency;
        int test = Integer.parseInt(sc.next());
        List<Integer> results = new ArrayList<>();
        for (int t = 0; t < test; t++) {
            int n = Integer.parseInt(sc.next());
            int q = Integer.parseInt(sc.next());
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                strings.add(s);
            }
            for (int i = 0; i < q; i++) {
                int query = Integer.parseInt(sc.next());
                List<String> suffix = new ArrayList<>();
                suffixFrequency = new HashMap<>();
                for (String s : strings) {
                    int length = s.length();
                    int startIndex = length - query;
                    if (startIndex < 0) {
                        continue;
                    }
                    suffix.add(s.substring(startIndex));
                }
                for (String suff : suffix) {
                    if (suffixFrequency.containsKey(suff)) {
                        suffixFrequency.put(suff, suffixFrequency.get(suff) + 1);
                    }
                    else {
                        suffixFrequency.put(suff, 1);
                    }
                }
                int result = Integer.MIN_VALUE;
                for (String suff : suffixFrequency.keySet()) {
                    if (suffixFrequency.get(suff) > result) {
                        result = suffixFrequency.get(suff);
                    }
                }
                results.add(result);
            }
        }
        for (Integer result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
