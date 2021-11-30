package stack_queue;

import java.util.*;

public class YourQueue {
    private static void solution(List<String> information, int numberOfQueries, int numberOfPatients) {
        LinkedList<Integer> patients = new LinkedList<>();
        for (int i = 1; i <= numberOfPatients; i++) {
            patients.add(i);
        }
        List<Integer> orders = new ArrayList<>();
        int query = 0;
        while (query < numberOfQueries) {
            for (String s : information) {
                if (s.equals("N")) {
                    Integer firstPatient = patients.remove();
                    patients.add(firstPatient);
                    orders.add(firstPatient);
                }
                else if (s.charAt(0) == 'E') {
                    String patientToBePrioritizedString = s.substring(2);
                    Integer patientToBePrioritized = Integer.parseInt(patientToBePrioritizedString);
                    patients.remove(patientToBePrioritized);
                    patients.add(0, patientToBePrioritized);
                }
                query++;
            }
        }
        for (Integer i : orders) {
            System.out.println(i);
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
