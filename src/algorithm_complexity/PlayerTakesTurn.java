package algorithm_complexity;

import java.util.Scanner;

public class PlayerTakesTurn {

    private static int[] calculatePlayerPoint(int[] cards) {
        int[] points = new int[2];
        int player1Point = 0;
        int player2Point = 0;
        int leftPointer = 0;
        if (cards.length == 1) {
            player1Point += cards[0];
            points[0] = player1Point;
            return points;
        }
        int rightPointer = cards.length - 1;
        while (leftPointer < rightPointer) {
            player1Point += Math.max(cards[leftPointer], cards[rightPointer]);
            if (cards[leftPointer] > cards[rightPointer]) {
                leftPointer++;
            }
            else {
                rightPointer--;
            }
            player2Point += Math.max(cards[leftPointer], cards[rightPointer]);
            if (cards[leftPointer] > cards[rightPointer]) {
                leftPointer++;
            }
            else {
                rightPointer--;
            }
            if (leftPointer == rightPointer && cards.length % 2 != 0) {
                player1Point += cards[rightPointer];
            }
        }

        points[0] = player1Point;
        points[1] = player2Point;
        return points;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int count = 0;
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[count++] = Integer.parseInt(sc.next());
        }
        int[] result = calculatePlayerPoint(cards);
        StringBuilder s = new StringBuilder();
        for (int i : result) {
            s.append(i).append(" ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        System.out.println(s);
        sc.close();
    }
}
