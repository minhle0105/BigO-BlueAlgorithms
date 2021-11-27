package stack_queue;

import java.util.*;

public class CompilerAndParser {
    private int length;

    public void setLength(int length) {
        this.length = length;
    }

    private void validR(String s) {
        if (s.length() <= 1) {
            this.length = 0;
            return;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '<') {
                stack.push(c);
            }
            else if ((!stack.isEmpty()) && (c == '>' && stack.peek() == '<')) {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
        if (stack.isEmpty()) {
            length = s.length();
        }
        else {
            validR(s.substring(0, s.length() - 1));
        }
    }

    private int solution(String s) {
        if (s.toCharArray()[0] == '>') {
            return 0;
        }
        validR(s);
        return length;
    }// <<><<>><<

    public static void main(String[] args) {
        CompilerAndParser compilerAndParser = new CompilerAndParser();
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.next());
        }
        for (String s : list) {
            compilerAndParser.setLength(0);
            System.out.println(compilerAndParser.solution(s));
        }
        sc.close();
    }
}
