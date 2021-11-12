package list_string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Password {

    private static int[] getCases(List<String> enteredPasswords, int k, String correctPassword) {
        // sap xep theo do dai
        int correctLength = correctPassword.length();
        int[] result = new int[2];
        int numberOfStringsShorterThan = 0;
        for (String password : enteredPasswords) {
            if (password.length() < correctLength) {
                numberOfStringsShorterThan ++;
            }
        }
        result[0] = ((numberOfStringsShorterThan / k) * 5) + (numberOfStringsShorterThan + 1);

        int numberOfStringEqualsLength = numberOfStringsShorterThan;
        for (String password : enteredPasswords) {
            if (password.length() == correctLength) {
                numberOfStringEqualsLength++;
            }
        }

        numberOfStringEqualsLength += ((numberOfStringEqualsLength - 1) / k) * 5;
        result[1] = numberOfStringEqualsLength;
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTrials = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        sc.nextLine();
        List<String> enteredPasswords = new ArrayList<>();
        for (int i = 0; i < numberOfTrials; i++) {
            enteredPasswords.add(sc.nextLine());
        }
        String correctPassword = sc.nextLine();
        int[] results = getCases(enteredPasswords, k, correctPassword);

        for (int i : results) {
            System.out.print(i + " ");
        }
        sc.close();
    }
}
