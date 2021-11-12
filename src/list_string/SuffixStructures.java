package list_string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SuffixStructures {

    private static Map<Character, Integer> countFrequency(String s) {
        Map<Character, Integer> hashMap = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if (hashMap.containsKey(c)) {
                hashMap.put(c, hashMap.get(c) + 1);
            }
            else {
                hashMap.put(c,1);
            }
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (!hashMap.containsKey(c)) {
                hashMap.put(c, 0);
            }
        }
        return hashMap;
    }

    private static String solution (String s1, String s2) {
        Map<Character, Integer> hashMapS1 = countFrequency(s1);
        Map<Character, Integer> hashMapS2 = countFrequency(s2);
        int count = 0;
        boolean isArray = true;
        for (Character c : s1.toCharArray()) {
            if (!(hashMapS1.get(c).equals(hashMapS2.get(c)))) {
                isArray = false;
            }
        }
        if (isArray) {
            return "array";
        }

        for (Character c : s2.toCharArray()) {
            if (hashMapS1.get(c) < hashMapS2.get(c)) {
                return "need tree";
            }
        }
        // both
        // hot -> array
        // oth -> automaton
        boolean isBoth = false;
        int index = 0;
        for (int i = 0; i< s2.length(); i++) {
            String s1Clone = s1.substring(index); // both oth th h
            if (s1Clone.indexOf(s2.charAt(i)) != -1) {
                index = s1Clone.indexOf(s2.charAt(i)) + 1;
            }
            else {
                isBoth = true;
            }
        }


        if (isBoth) {
            return "both";
        }
        return "automaton";

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(solution(s1, s2));
        sc.close();
    }
}
