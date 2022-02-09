package BinarySearch;

import java.util.*;

public class Isenbaev {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, List<String>> map = new TreeMap<>();
        Map<String, Integer> visited = new HashMap<>();
        Map<String, Integer> levels = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] nextLine = sc.nextLine().split(" ");
            for (String name : nextLine) {
                if (!map.containsKey(name)) {
                    map.put(name, new ArrayList<>());
                }
                if (!visited.containsKey(name)) {
                    visited.put(name, 0);
                }
                if (!levels.containsKey(name)) {
                    levels.put(name, 0);
                }
            }
            for (int j = 0; j < nextLine.length - 1; j++) {
                for (int k = j + 1; k < nextLine.length; k++) {
                    map.get(nextLine[j]).add(nextLine[k]);
                    map.get(nextLine[k]).add(nextLine[j]);
                }
            }
        }
        boolean mapContainsName = map.containsKey("Isenbaev");
        if (!map.containsKey("Isenbaev")) {
            map.put("Isenbaev", new ArrayList<>());
        }
        if (!levels.containsKey("Isenbaev")) {
            levels.put("Isenbaev", 0);
        }
        visited.put("Isenbaev", 1);
        Queue<String> queue = new LinkedList<>();
        queue.add("Isenbaev");
        while (!queue.isEmpty()) {
            String thisName = queue.remove();
            for (int i = 0; i < map.get(thisName).size(); i++) {
                String nextName = map.get(thisName).get(i);
                if (visited.get(nextName) == 0) {
                    visited.put(nextName, 1);
                    queue.add(nextName);
                    levels.put(nextName, levels.get(thisName) + 1);
                }
            }
        }
        for (String name : map.keySet()) {
            if (name.equals("Isenbaev") && !mapContainsName) {
                continue;
            }
            if (levels.get(name) == 0 && !name.equals("Isenbaev")) {
                System.out.println(name + " undefined");
            }
            else {
                System.out.println(name + " " + levels.get(name));
            }
        }

        sc.close();
    }
}
