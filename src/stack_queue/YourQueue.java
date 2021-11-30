package stack_queue;

import java.util.*;

public class YourQueue {
    private static void solution(List<String> information, int numberOfQueries, int numberOfPatients) {
        Queue<Integer> patients = new LinkedList<>();
        for (int i = 1; i <= numberOfPatients; i++) {
            patients.add(i);
        }

        int query = 0;
        while (query < numberOfQueries) {
            for (String s : information) {
                if (s.equals("N")) {
                    Integer firstPatient = patients.remove();
                    patients.add(firstPatient);
                    System.out.println(firstPatient);
                }
                else if (s.charAt(0) == 'E') {
                    Integer patientToBePrioritized = Integer.parseInt(String.valueOf(s.charAt(2)));
                    Queue<Integer> backup = new LinkedList<>();
                    while (!patients.isEmpty() && !patients.peek().equals(patientToBePrioritized)) {
                        backup.add(patients.remove());
                    }
                    Integer removedPatient = patients.remove();
                    while (!patients.isEmpty()) {
                        backup.add(patients.remove());
                    }
                    patients.add(removedPatient);
                    while (!backup.isEmpty()) {
                        patients.add(backup.remove());
                    }
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
