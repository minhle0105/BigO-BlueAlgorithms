package Midterm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FindTheMedian {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(sc.next());
            numbers.add(next);
        }
        Collections.sort(numbers);
        System.out.println(numbers.get(n/2));
        sc.close();
    }
}
