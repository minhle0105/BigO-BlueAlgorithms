package heap;

import java.util.*;

class Task implements Comparable<Task> {
    int power;
    int timeNeeded;
    int deadline;

    public Task(int power, int timeNeeded, int deadline) {
        this.power = power;
        this.timeNeeded = timeNeeded;
        this.deadline = deadline;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(int timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.deadline, o.deadline);
    }
}

public class TheLazyProgrammer {

    private static void solution(List<Task> tasks) {
        int previousTime = 0;
        PriorityQueue<Task> heap = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return Integer.compare(o2.getPower(), o1.getPower());
            }
        });
        double result = 0;
        for (Task task : tasks) {
            previousTime += task.getTimeNeeded();
            heap.add(task);
            if (previousTime > task.getDeadline()) {
                while (!heap.isEmpty() && previousTime - task.getDeadline() > 0) {
                    Task maxTask = heap.remove();
                    if (maxTask.getTimeNeeded() >= previousTime - task.getDeadline()) {
                        result += ((double)(previousTime - task.getDeadline())) / maxTask.getPower();
                        int newTime = maxTask.getTimeNeeded() - (previousTime - task.getDeadline());
                        if (newTime > 0) {
                            heap.add(new Task(maxTask.getPower(), newTime, maxTask.getDeadline()));
                        }
                        previousTime = task.getDeadline();
                    }
                    else {
                        result += (double) maxTask.getTimeNeeded() / maxTask.getPower();
                        previousTime -= maxTask.getTimeNeeded();
                    }
                }
            }
        }

        System.out.printf("%.2f%n", result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        List<Double> results = new ArrayList<>();
        List<Task> tasks;
        for (int test = 0; test < numberOfTest; test++) {
            tasks = new ArrayList<>();
            int numberOfContracts = Integer.parseInt(sc.next());
            for (int i = 0; i < numberOfContracts; i++) {
                int a = Integer.parseInt(sc.next());
                int b = Integer.parseInt(sc.next());
                int c = Integer.parseInt(sc.next());
                Task task = new Task(a,b,c);
                tasks.add(task);
            }
            Collections.sort(tasks);
            solution(tasks);
        }


        sc.close();
    }
}
