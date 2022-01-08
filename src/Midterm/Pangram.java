package Midterm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pangram {

    private static boolean isPangram(String s) {
        Set<Character> hasSeen = new HashSet<>();
        for (char c : s.toCharArray()) {
            hasSeen.add(Character.toLowerCase(c));
        }
        return hasSeen.size() == 26;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        String userInput = sc.next();
        boolean result = isPangram(userInput);
        if (result) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
        sc.close();
    }
}
