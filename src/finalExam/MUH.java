package finalExam;

import java.util.Scanner;
import java.util.*;

public class MUH {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int[] tasks = new int[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = Integer.parseInt(sc.next());
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(tasks[i])) {
                map.put(tasks[i], new ArrayList<>());
            }
            map.get(tasks[i]).add(i);
        }
        Arrays.sort(tasks);
        long ways = 1;
        for (Integer key : map.keySet()) {
            ways *= map.get(key).size();
        }
        if (ways >= 3) {
            System.out.println("YES");
            List<List<Integer>> options = new ArrayList<>();
            options.add(new ArrayList<>());
            options.add(new ArrayList<>());
            options.add(new ArrayList<>());
            int i = 0;
            for (Integer key : map.keySet()) {
                for (Integer index : map.get(key)) {
                    options.get(i).add((index + 1));
                }
            }
            i += 1;
            boolean swap = false;
            int hasSeenKey = -1;
            for (Integer key : map.keySet()) {
                List<Integer> indices = map.get(key);
                if (indices.size() >= 2 && !swap) {
                    options.get(i).add(indices.get(1) + 1);
                    options.get(i).add(indices.get(0) + 1);
                    hasSeenKey = key;
                    swap = true;
                    for (int j = 2; j < indices.size(); j++) {
                        options.get(i).add(indices.get(j) + 1);
                    }
                }
                else {
                    for (Integer index : indices) {
                        options.get(i).add((index + 1));
                    }
                }
            }

            i += 1;
            if (map.get(hasSeenKey).size() == 3) {
                options.get(i).add(map.get(hasSeenKey).get(0) + 1);
                options.get(i).add(map.get(hasSeenKey).get(2) + 1);
                options.get(i).add(map.get(hasSeenKey).get(1) + 1);
                for (Integer key : map.keySet()) {
                    if (key != hasSeenKey) {
                        for (Integer index : map.get(key)) {
                            options.get(i).add(index + 1);
                        }
                    }
                }
            }

            else {
                boolean swap2 = false;
                for (Integer key : map.keySet()) {
                    List<Integer> indices = map.get(key);
                    if (key != hasSeenKey) {
                        if (indices.size() >= 2 && !swap2) {
                            options.get(i).add(indices.get(1) + 1);
                            options.get(i).add(indices.get(0) + 1);
                            hasSeenKey = key;
                            swap2 = true;
                            for (int j = 2; j < indices.size(); j++) {
                                options.get(i).add(indices.get(j) + 1);
                            }
                        }
                        else {
                            for (Integer index : indices) {
                                options.get(i).add((index + 1));
                            }
                        }
                    }
                    else {
                        for (Integer index : indices) {
                            options.get(i).add((index + 1));
                        }
                    }
                }
            }
            for (List<Integer> option : options) {
                for (Integer o : option) {
                    System.out.print(o + " ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("NO");
        }
        sc.close();
    }
}
