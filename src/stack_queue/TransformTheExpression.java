package stack_queue;

import java.util.*;

public class TransformTheExpression {

    // (a + (b*c)) -> a b c * +

    private static void solution (String expression) {
        Stack<Character> stack = new Stack<>();
        StringBuilder s = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                continue;
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                stack.push(c);
            }
            else if (c == ')') {
                s.append(stack.pop());
            }
            else {
                s.append(c);
            }
        }
        while (!stack.isEmpty()) {
            s.append(stack.pop());
        }
        System.out.println(s);
    }

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.next());
        }
        for (String s : list) {
            solution(s);
        }
        sc.close();
    }
}
