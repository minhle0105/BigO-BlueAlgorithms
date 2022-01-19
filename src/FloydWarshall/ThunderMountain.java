package FloydWarshall;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ThunderMountain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        int numberOfTest = Integer.parseInt(sc.next());
        for (int test = 0; test < numberOfTest; test ++){
            int numberOfTowns = Integer.parseInt(sc.next());
            int[][] graph = new int[numberOfTowns][numberOfTowns];
            int[][] distance = new int[numberOfTowns][numberOfTowns];
            int[][] path = new int[numberOfTowns][numberOfTowns];
            for (int i = 0; i < numberOfTowns; i++) {
                int src = Integer.parseInt(sc.next());
                int dst = Integer.parseInt(sc.next());

            }
        }
        sc.close();
    }
}
