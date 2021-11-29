package stack_queue;

import java.util.*;

public class CompilerAndParser {
    private static int length;

    private static void validR(String originalS, Stack<Character> stack, int endIndex, int pointer) {

        // nếu stack trống và kí tự tiếp theo là dấu > thì chắc chắn là dừng vì không có dấu < tương ứng ở trước
        if (stack.isEmpty() && originalS.charAt(pointer) == '>') {
            return;
        }

        // nếu kí tự tiếp theo trong chuỗi là dấu mở ngoặc thì nhét vào stack
        if (originalS.charAt(pointer) == '<') {
            stack.push(originalS.charAt(pointer));
        }
        // nếu là dấu đóng ngoặc và top của stack là dấu mở thì là 1 cặp trùng nhau, nên pop ra
        else if ((!stack.isEmpty()) && (originalS.charAt(pointer) == '>' && stack.peek() == '<')) {
            stack.pop();
        }
        // nếu không thì đẩy vào stack
        else {
            stack.push(originalS.charAt(pointer));
        }

        // nếu stack trống thì chuỗi đang xử lý là ok, cập nhật length
        if (stack.isEmpty()) {
            length = pointer +1;
        }
        // nếu đã xử lý hết chuỗi thì dừng đệ quy
        if (pointer == originalS.length() - 1) {
            return;
        }
        // nếu không thì tiếp tục đệ quy, đẩy pointer lên 1
        else {
            validR(originalS, stack,endIndex+1, pointer+1);
        }
    }

    private static int solution(String s) {
        if (s.toCharArray()[0] == '>') {
            return 0;
        }
        int endIndex = 1;
        validR(s, new Stack<>(), endIndex, 0);
        return length;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.next());
        }
        for (String s : list) {
            length = 0;
            System.out.println(solution(s));
        }
        sc.close();
    }
}
