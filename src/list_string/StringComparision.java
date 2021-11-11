package list_string;

import java.util.Scanner;

public class StringComparision {


    private static String compareString(String s1, String s2) {
        for (int i = s1.length() - 1; i >= 0; i--) {
            if (s1.charAt(i) == 'z') {
                s1 = s1.substring(0, i) + 'a' + s1.substring(i+1);
            }
            else {
                s1 = s1.substring(0, i) + (char) (s1.charAt(i) + 1) + s1.substring(i+1);
                break;
            }
        }
        if (s1.compareTo(s2) < 0) {
            return s1;
        }
        return "No such string";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        System.out.println(compareString(s1, s2));
        sc.close();
    }
}
