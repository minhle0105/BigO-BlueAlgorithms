package Disjoint_Union_Set;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Forests {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input1.txt"));
        int numberOfTest = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        int[] results = new int[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            String[] PT = sc.nextLine().split(" ");
            int P = Integer.parseInt(PT[0]);
            int T = Integer.parseInt(PT[1]);
            Map<Integer, List<Integer>> map = new HashMap<>();
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                if (line[0].length() == 0) break;
                int i = Integer.parseInt(line[0]);
                int j = Integer.parseInt(line[1]);
                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }
                map.get(i).add(j);
            }
            for (List<Integer> values : map.values()) {
                Collections.sort(values);
            }
            Set<List<Integer>> opinions = new HashSet<>(map.values());
            results[test] = opinions.size();
        }
        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
