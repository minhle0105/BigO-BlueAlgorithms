package stack_queue;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class MoleculeMass {
    private static final HashMap<Character, Integer> massesMapping = new HashMap<>();

    private static int getMass(char element) {
        return massesMapping.get(element);
    }

    private static int solution(String molecule) {
        massesMapping.put('H', 1);
        massesMapping.put('C', 12);
        massesMapping.put('O', 16);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i  < molecule.length(); i ++) {
            char c = molecule.charAt(i);
            if (Character.isDigit(c)) {
                int mass = stack.pop();
                stack.push(mass * Integer.parseInt("" + c));
            }
            else if (c == '(') {
                stack.push(0);
            }
            else if (c == ')') {
                int totalMass = 0;
                while (stack.peek() != 0) {
                    totalMass += stack.pop();
                }
                stack.pop();
                stack.push(totalMass);
            }
            else {
                stack.push(getMass(c));
            }
        }
        int totalMolecularMass = 0;

        while (!stack.isEmpty()) {
            totalMolecularMass += stack.pop();
        }
        return totalMolecularMass;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String molecule = sc.nextLine();
        System.out.println(solution(molecule));
        sc.close();
    }
}
