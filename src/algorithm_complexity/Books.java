package algorithm_complexity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Books {

    private static int solution(int[] list, int time) {
        List<Integer> numberOfBooksCanBeReadFromHere = new ArrayList<>();
        int firstPointer = 0;
        int secondPointer = 0;
        int sumOfSub = list[firstPointer];
        while (firstPointer < list.length) {
            if (sumOfSub <= time && secondPointer < list.length - 1) {
                secondPointer++;
                sumOfSub += list[secondPointer];
            }
            else {
                if (secondPointer < list.length - 1 || sumOfSub >= time) {
                    numberOfBooksCanBeReadFromHere.add((secondPointer - 1) - firstPointer + 1);
                }
                else {
                    numberOfBooksCanBeReadFromHere.add((secondPointer) - firstPointer + 1);
                }
                if (firstPointer == list.length - 1) {
                    break;
                }
                sumOfSub = list[firstPointer+1];
                firstPointer++;
                secondPointer = firstPointer;
            }
        }
        int maxNumberOfBooks = numberOfBooksCanBeReadFromHere.get(0);
        for (Integer i : numberOfBooksCanBeReadFromHere) {
            if (i > maxNumberOfBooks) {
                maxNumberOfBooks = i;
            }
        }
        return maxNumberOfBooks;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfBooks = Integer.parseInt(sc.next());
        int totalTimeAvailable = Integer.parseInt(sc.next());
        int[] list = new int[numberOfBooks];
        int counter = 0;
        sc.nextLine();
        for (int i = 0; i < numberOfBooks; i++) {
            list[counter++] = Integer.parseInt(sc.next());
        }
        System.out.println(solution(list, totalTimeAvailable));
        sc.close();
    }

    @Test
    void firstTest() {
        int[] list = {3,1,2,1};
        int time = 5;
        int expected = 3;
        int actual = solution(list, time);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void secondTest() {
        int[] list = {2,2,3};
        int time = 3;
        int expected = 1;
        int actual = solution(list, time);
        Assertions.assertEquals(expected, actual);
    }


}
