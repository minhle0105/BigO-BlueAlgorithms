package stack_queue;

import java.util.*;

public class YourQueue {
    private static void solution(List<String> information, int numberOfQueries, int numberOfPatients) {
        Queue<Long> patients = new LinkedList<>();
        for (long i = 1; i <= numberOfPatients; i++) {
            patients.add(i);
        }
        List<Long> orders = new ArrayList<>();
        int query = 0;
        while (query < numberOfQueries) {
            for (String s : information) {
                if (s.equals("N")) {
                    Long firstPatient = patients.remove();
                    patients.add(firstPatient);
                    orders.add(firstPatient);
                }
                else if (s.charAt(0) == 'E') {
                    String patientToBePrioritizedString = s.substring(2);
                    Long patientToBePrioritized = Long.parseLong(patientToBePrioritizedString);
                    Queue<Long> backup = new LinkedList<>();
                    while (!patients.isEmpty() && !patients.peek().equals(patientToBePrioritized)) {
                        backup.add(patients.remove());
                    }
                    patients.remove();
                    while (!patients.isEmpty()) {
                        backup.add(patients.remove());
                    }
                    patients.add(patientToBePrioritized);
                    while (!backup.isEmpty()) {
                        patients.add(backup.remove());
                    }
                }
                query++;
            }
        }
        for (Long i : orders) {
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
        int l = 1000000000;
        System.out.println(l);

        sc.close();
    }
}
