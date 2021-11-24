package sorting;

import java.util.*;

public class EightPointSets {


    private static String solution(List<List<Integer>> points) {
        List<Integer> Xs = new ArrayList<>();
        List<Integer> Ys = new ArrayList<>();

        // lấy tất cả toạ độ X và toạ độ Y
        for (List<Integer> point : points) {
            if (!Xs.contains(point.get(0))) {
                Xs.add(point.get(0));
            }
            if (!Ys.contains(point.get(1))) {
                Ys.add(point.get(1));
            }
        }

        if (Xs.size() != 3 | Ys.size() != 3) {
            return "ugly";
        }

        Collections.sort(Xs);
        Collections.sort(Ys);

        List<List<Integer>> generatedPoints = new ArrayList<>();
        for (Integer x : Xs) {
            for (Integer y : Ys) {
                if (Xs.indexOf(x) == 1 && Ys.indexOf(y) == 1) {
                    continue;
                }
                else {
                    List<Integer> point = new ArrayList<>();
                    point.add(x);
                    point.add(y);
                    generatedPoints.add(point);
                }
            }
        }

        Collections.sort(points, (o1, o2) -> {
            if (!o1.get(0).equals(o2.get(0))) {
                return o1.get(0).compareTo(o2.get(0));
            }
            return o1.get(1).compareTo(o2.get(1));
        });

        for (int i = 0; i < points.size(); i++) {
            if (!points.get(i).get(0).equals(generatedPoints.get(i).get(0)) | !points.get(i).get(1).equals(generatedPoints.get(i).get(1))) {
                return "ugly";
            }
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