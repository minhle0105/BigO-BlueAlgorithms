package stack_queue;

import java.util.*;

public class CompilerAndParser {
    private int length;

    public void setLength(int length) {
        this.length = length;
    }

    private void validR(String originalS, Stack<Character> stack, int endIndex, int pointer) {
        // nhích lên từng chút tới khi kiểm tra hết chuỗi gốc
        String s = originalS.substring(0, endIndex);
        char[] sToChar = s.toCharArray();

        // nếu stack trống và kí tự tiếp theo là dấu > thì chắc chắn là dừng vì không có dấu < tương ứng ở trước
        if (stack.isEmpty() && sToChar[sToChar.length - 1] == '>') {
            return;
        }

        // chỉ đi từ pointer, tiếp tục nhét vào stack để kiểm tra
        for (int i = pointer; i < s.length(); i++) {
            if (sToChar[i] == '<') {
                stack.push(sToChar[i]);
            }
            else if ((!stack.isEmpty()) && (sToChar[i] == '>' && stack.peek() == '<')) {
                stack.pop();
            }
            else {
                stack.push(sToChar[i]);
            }
        }

        // nếu stack trống thì chuỗi đang xử lý là ok, cập nhật length
        if (stack.isEmpty()) {
            length = s.length();
        }
        // nếu đã xử lý hết chuỗi thì dừng đệ quy
        if (s.length() == originalS.length()) {
            return;
        }
        // nếu không thì tiếp tục đệ quy, đẩy pointer lên 1
        else {
            validR(originalS, stack,endIndex+1, pointer+1);
        }
    }

    private int solution(String s) {
        if (s.toCharArray()[0] == '>') {
            return 0;
        }
        int endIndex = 1;
        validR(s, new Stack<>(), endIndex, 0);
        return length;
    }

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
