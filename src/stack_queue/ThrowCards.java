package stack_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ThrowCards {

    private static void solution (int cardLimit) {
        if (cardLimit == 1) {
            System.out.println("Discarded cards: ");
            System.out.println("Remaining card: 1");
            return;
        }
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        List<Integer> poppedNumbers = new ArrayList<>();
        for (int i = cardLimit; i >= 1; i--) {
            stack1.push(i);
        }
        int count = 0;
        boolean keepLooping = true;
        while (keepLooping) {
            while (!stack1.isEmpty()) {
                boolean moveToLast = count % 2 != 0;
                if (!moveToLast) {
                    poppedNumbers.add(stack1.pop());
                }
                else {
                    stack2.push(stack1.pop());
                }
                count++;
            }
            if (stack2.size() == 1) {
                keepLooping = false;
            }
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        int lastCard = stack1.pop();
        StringBuilder s = new StringBuilder();
        for (Integer i : poppedNumbers) {
            s.append(i).append(", ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 2));
        System.out.println("Discarded cards: " + s);
        System.out.println("Remaining card: " + lastCard);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cards;
        while (true) {
            cards = Integer.parseInt(sc.next());
            if (cards == 0) {
                break;
            }
            solution(cards);
        }

        sc.close();
    }
}
