package BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class HardWood {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        List<List<String>> results = new ArrayList<>();
        List<List<Double>> percentages = new ArrayList<>();
        for (int test = 0; test < numberOfTest; test++) {
            List<String> data = new ArrayList<>();
            List<Double> percentage = new ArrayList<>();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (nextLine.length() == 0) {
                    break;
                }
                data.add(nextLine);
            }
            TreeMap<String, Integer> map = new TreeMap<>();
            for (String d : data) {
                if (map.containsKey(d)) {
                    map.put(d, map.get(d) + 1);
                }
                else {
                    map.put(d, 1);
                }
            }
            int total = 0;
            for (Integer value : map.values()) {
                total += value;
            }
            List<String> result = new ArrayList<>(map.keySet());
            for (String d : map.keySet()) {
                percentage.add((((double) map.get(d) / total) * 100));
            }
            results.add(result);
            percentages.add(percentage);
        }

        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < results.get(i).size(); j++) {
                System.out.print(results.get(i).get(j) + " ");
                System.out.printf("%.4f", percentages.get(i).get(j));
                System.out.println();
            }
            System.out.println();
        }

        sc.close();
    }
}
