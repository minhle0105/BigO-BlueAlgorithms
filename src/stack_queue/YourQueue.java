package stack_queue;

import java.util.*;

public class YourQueue {
    private static void solution(List<String> information, int numberOfQueries, int numberOfPatients) {
        int firstPatient = 1;
        int query = 0;
        while (query < numberOfQueries) {
            for (String s : information) {
                if (s.equals("N")) {
                    System.out.println(firstPatient++);
                }
                else if (s.charAt(0) == 'E') {
                    String patientToBePrioritizedString = s.substring(2);
                    Long patientToBePrioritized = Long.parseLong(patientToBePrioritizedString);
                    System.out.println(patientToBePrioritized);
                }
                if (firstPatient == numberOfPatients + 1) {
                    firstPatient = 1;
                }
                query++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseCount = 1;
        while (true) {
            int n = Integer.parseInt(sc.next());
            int numberOfQueries = Integer.parseInt(sc.next());
            if (n == 0 && numberOfQueries == 0) {
                break;
            }
            List<String> information = new ArrayList<>();
            sc.nextLine();
            for (int i = 0; i < numberOfQueries; i++) {
                information.add(sc.nextLine());
            }
            System.out.println("Case " + caseCount + ": ");
            solution(information, numberOfQueries, n);
            caseCount++;
        }

        sc.close();
    }
}
