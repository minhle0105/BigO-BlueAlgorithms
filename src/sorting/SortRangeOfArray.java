package sorting;

import java.util.*;

public class SortRangeOfArray {

    private static void solution(List<Integer> list) {
        boolean isOriginallySorted = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                isOriginallySorted = false;
                break;
            }
        }
        if (isOriginallySorted) {
            System.out.println("yes");
            System.out.println(1 + " " + 1);
            return;
        }
        List<Integer> subListToSort = new ArrayList<>();
        int firstPointer = 0;
        int secondPointer = 1;
        int anchor = 0;
        while (firstPointer < list.size() - 1) {
            if (list.get(firstPointer) < list.get(secondPointer)) {
                firstPointer++;
                secondPointer++;
            } else {
                anchor = firstPointer + 1;
                subListToSort.add(list.get(firstPointer++));
                subListToSort.add(list.get(secondPointer++));
                while (list.get(firstPointer) > list.get(secondPointer)) {
                    subListToSort.add(list.get(secondPointer));
                    firstPointer++;
                    secondPointer++;
                    if (secondPointer == list.size()) {
                        break;
                    }
                }
                break;
            }
        }
        Collections.sort(subListToSort);
        // 1 3 2 5 6
        // 1
        int count = 0;
        for (int i = anchor - 1; i < secondPointer; i++) {
            list.set(i, subListToSort.get(count++));
        }
        boolean isSorted = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            System.out.println("yes");
            System.out.print(anchor + " " + secondPointer);
        } else {
            System.out.println("no");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        int n = Integer.parseInt(sc.next());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(sc.next()));
        }
        solution(list);
        sc.close();
    }
}
