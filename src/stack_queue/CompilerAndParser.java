package stack_queue;

import java.util.*;

public class CompilerAndParser {

    private static int solution(String s) {
        if (s.toCharArray()[0] == '>') {
            return 0;
        }
        int result = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '<') {
                stack.push(c);
            }
            if ((!stack.isEmpty()) && (c == '>' && stack.peek() == '<')) {
                stack.pop();
                result += 2;
            }
        }
        if (!stack.isEmpty()) {
            return 0;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.next());
        }
        for (String s : list) {
            System.out.println(solution(s));
        }
        sc.close();
    }
}
