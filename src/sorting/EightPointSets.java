package sorting;

import java.util.*;

public class EightPointSets {

    private static HashMap<Integer, Integer> countPoint(List<Integer> points) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer point : points) {
            if (hashMap.containsKey(point)) {
                hashMap.put(point, hashMap.get(point) + 1);
            }
            else {
                hashMap.put(point, 1);
            }
        }
        return hashMap;
    }

    private static String solution(List<List<Integer>> points) {
        List<Integer> Xs = new ArrayList<>();
        List<Integer> Ys = new ArrayList<>();

        // lấy tất cả toạ độ X và toạ độ Y
        for (List<Integer> point : points) {
            Xs.add(point.get(0));
            Ys.add(point.get(1));
        }

        HashMap<Integer, Integer> countX = countPoint(Xs);
        HashMap<Integer, Integer> countY = countPoint(Ys);

        // nếu không có 3 toạ độ X hoặc 3 toạ độ Y phân biệt thì chắc chắn sai
        if (countX.size() != 3 || countY.size() != 3) {
            return "ugly";
        }

        // sort toạ độ X và Y
        List<Integer> uniqueX = new ArrayList<>(countX.keySet());
        Collections.sort(uniqueX);
        List<Integer> uniqueY = new ArrayList<>(countY.keySet());
        Collections.sort(uniqueY);

        // sort rồi thì điểm ở giữa chắc chắn có index 1 1
        List<Integer> middlePoint = Arrays.asList(uniqueX.get(1), uniqueY.get(1));

        // nếu trong list các điểm ban đầu có điểm ở giữa thì sai
        if (points.contains(middlePoint)) {
            return "ugly";
        }
        return "respectable";
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> points = new ArrayList<>();
        int count = 0;
        while (count < 8) {
            List<Integer> newPoint = new ArrayList<>();
            newPoint.add(Integer.parseInt(sc.next()));
            newPoint.add(Integer.parseInt(sc.next()));
            points.add(newPoint);
            count++;
        }
        System.out.println(solution(points));
        sc.close();
    }
}
