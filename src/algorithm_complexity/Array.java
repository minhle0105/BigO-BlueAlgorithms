package algorithm_complexity;

import java.util.*;

public class Array {
    private static int[] solution(List<Integer> list, int k) {

        // nếu k = 1 thì khoảng ngắn nhất có k số phân biệt là [1,1]
        if (k == 1) {
            return new int[]{1,1};
        }

        // tạo array chứa kết quả, tạm thời để là [-1,-1]
        int[] result = new int[]{-1, -1};
        int firstPointer = -1;
        int secondPointer = -1;
        int maxLength = list.size();

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(list.get(0), 1);

        for (int i = 1; i < list.size(); i++) {
            if (!hashMap.containsKey(list.get(i))) {
                hashMap.put(list.get(i), 1);
            }
            if (hashMap.size() < k) {
                hashMap.put(list.get(i), hashMap.get(list.get(i)) + 1);
            }
            else {
                secondPointer = i;
                break;
            }
        }

        // nếu đi hết 1 vòng vẫn mà secondPointer vẫn là -1 thì
        // tức là else block trong vòng lặp không được execute
        // tức là không có đoạn nào đạt tới k phần tử phân biệt (điều kiện của if hashmap.size() >= k)
        // tức là không có đáp án
        if (secondPointer == -1) {
            return new int[] {-1, -1};
        }
        hashMap = new HashMap<>();
        hashMap.put(list.get(secondPointer), 1);
        for (int i = secondPointer - 1; i >= 0; i--) {
            if (!hashMap.containsKey(list.get(i))) {
                hashMap.put(list.get(i), 1);
            }
            if (hashMap.size() < k) {
                hashMap.put(list.get(i), hashMap.get(list.get(i)) + 1);
            }
            else {
                firstPointer = i;
                break;
            }
        }
        result[0] = firstPointer+1;
        result[1] = secondPointer + 1;

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int k = Integer.parseInt(sc.next());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(sc.next()));
        }
        int[] result = solution(list, k);
        StringBuilder s = new StringBuilder();
        for (int i : result) {
            s.append(i).append(" ");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        System.out.println(s);
        sc.close();
    }
}
