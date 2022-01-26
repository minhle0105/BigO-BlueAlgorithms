package BinarySearch;

import java.util.Scanner;

public class PlayboyChimp {

    private static int searchInsertPosition(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int middle = (right + left) / 2;
            if (numbers[middle] == target) {
                return middle;
            }
            else if (numbers[middle] < target) {
                left = middle + 1;
            }
            else {
                right = middle - 1;
            }
        }
        return left;
    }

    private static int[][] getSolution(int[] numbers, int[] queries) {
        int[][] results = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            int target = queries[i];
            int position = searchInsertPosition(numbers, target);
            boolean foundLeft = false;
            boolean foundRight = false;
            if (position <= 0) {
                if (target > numbers[0]) {
                    results[i][0] = numbers[0];
                }
                else {
                    results[i][0] = -1;
                }
                for (int j = position; j < numbers.length; j++) {
                    if (numbers[j] > target) {
                        results[i][1] = numbers[j];
                        foundRight = true;
                        break;
                    }
                }
                if (!foundRight) {
                    results[i][1] = -1;
                }
            }
            else if (position >= numbers.length - 1) {
                for (int j = position - 1; j >= 0; j--) {
                    if (numbers[j] < target) {
                        results[i][0] = numbers[j];
                        foundLeft = true;
                        break;
                    }
                }
                if (!foundLeft) {
                    results[i][0] = -1;
                }
                if (target < numbers[numbers.length - 1]) {
                    results[i][1] = numbers[numbers.length - 1];
                }
                else {
                    results[i][1] = -1;
                }
            }
            else {
                for (int j = position; j >= 0; j--) {
                    if (numbers[j] < target) {
                        results[i][0] = numbers[j];
                        foundLeft = true;
                        break;
                    }
                }
                for (int j = position; j < numbers.length; j++) {
                    if (numbers[j] > target) {
                        results[i][1] = numbers[j];
                        foundRight = true;
                        break;
                    }
                }
                if (!foundLeft) {
                    results[i][0] = -1;
                }
                if (!foundRight) {
                    results[i][1] = -1;
                }
            }
            // 1 4 5 7
            // 6 -> 3
            // 6 > numbers[position - 1] -> 5

        }
        return results;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(sc.next());
        }
        int q = Integer.parseInt(sc.next());
        int[] queries = new int[q];
        for (int i = 0; i < q; i++) {
            queries[i] = Integer.parseInt(sc.next());
        }
        int[][] results = getSolution(numbers, queries);
        for (int[] result : results) {
            for (int j = 0; j < result.length; j++) {
                if (result[j] == -1) {
                    if (j == 0) {
                        System.out.print("X ");
                    } else {
                        System.out.print("X");
                    }
                } else {
                    if (j == 0) {
                        System.out.print(result[j] + " ");
                    } else {
                        System.out.print(result[j]);
                    }

                }
            }
            System.out.println();
        }
        sc.close();
    }
}
