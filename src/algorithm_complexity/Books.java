package algorithm_complexity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Books {

    private static int solution(int[] list, int timeLimit) {
        // numberOfBooksCanBeReadFromHere stores the data to tell you
        // if you start from the i-th book, then numberOfBooksCanBeReadFromHere[i] is the
        // number of books you can finish
        // --> the max value of numberOfBooksCanBeReadFromHere will be the answer
        List<Integer> numberOfBooksCanBeReadFromHere = new ArrayList<>();

        int firstPointer = 0; // the first book we start on
        int secondPointer = 0; // the last book we can read (trying to extend from firstPointer)
        // timeConsumedSoFar starts counting from reading book 1
        int timeConsumedSoFar = list[firstPointer];
        // while loop
        while (firstPointer < list.length) {
            // if we still have time && if we haven't reached the last book
            if (timeConsumedSoFar <= timeLimit && secondPointer < list.length - 1) {
                // extend secondPointer
                secondPointer++;
                // we can read this book --> add the time to timeConsumedSoFar
                timeConsumedSoFar += list[secondPointer];
            }
            // else (we either run out of time, or we have reached the last book
            else {
                // have not reached the last book or running out of time
                if (secondPointer < list.length - 1 || timeConsumedSoFar > timeLimit) {
                    // timeConsumedSoFar is already > time limit, which means we cannot read
                    // this current book at second pointer
                    // --> we have to subtract one from second pointer when adding to result
                    numberOfBooksCanBeReadFromHere.add((secondPointer - 1) - firstPointer + 1);
                }
                else {
                    // reached the last book
                    numberOfBooksCanBeReadFromHere.add((secondPointer) - firstPointer + 1);
                }
                // if the book we start is actually the last book already --> break
                if (firstPointer == list.length - 1) {
                    break;
                }
                // otherwise, we try start from the next book --> set timeConsumedSoFar to be the next book
                timeConsumedSoFar = list[firstPointer + 1];
                // moving to the next book
                firstPointer++;
                // start extending from the next book
                secondPointer = firstPointer;
            }
        }
        // max here is the max number of books that can be read
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
}
