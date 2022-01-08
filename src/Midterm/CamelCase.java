package Midterm;

import java.util.Scanner;

public class CamelCase {

    private static int countWord(String s) {
        int word = 0;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                word++;
            }
        }
        return word + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        int result = countWord(userInput);
        System.out.println(result);
        sc.close();
    }
}
