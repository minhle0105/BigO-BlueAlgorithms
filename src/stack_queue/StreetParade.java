package stack_queue;

import java.util.*;

public class StreetParade {

    // 5 1 2 4 3
    // 4 5
    private static boolean solution(List<Integer> list) {
        Stack<Integer> stack = new Stack<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            stack.push(list.get(i));
        }

        Stack<Integer> subStack = new Stack<>();
        List<Integer> listAfterPop = new ArrayList<>();
        while (!stack.isEmpty()) {
            int topValue = stack.pop();
            if (stack.isEmpty()) {
                listAfterPop.add(topValue);
                break;
            }
            if (!(topValue < stack.peek())) {
                subStack.push(topValue);
            } else {
                listAfterPop.add(topValue);
            }
            while ((!subStack.isEmpty()) && listAfterPop.size() > 0 && subStack.peek() > listAfterPop.get(listAfterPop.size() - 1) && subStack.peek() < stack.peek()) {
                listAfterPop.add(subStack.pop());
            }
        }

        while (!subStack.isEmpty()) {
            listAfterPop.add(subStack.pop());
        }

        Collections.sort(list); // n + nlog(n)
        return listAfterPop.equals(list);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = Integer.parseInt(sc.next());
            if (n == 0) {
                break;
            }
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(sc.next()));
            }
            if (solution(list)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        sc.close();
    }

}
