package stack_queue;

import java.util.*;

class Car {
    private int timeOfArrival;
    private String side;

    public Car(int timeOfArrival, String side) {
        this.timeOfArrival = timeOfArrival;
        this.side = side;
    }

    public Car() {
    }

    public int getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(int timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}

public class FerryLoading {

    private static List<Integer> solution(List<Car> cars, int numberOfCarsOnFerry, int minutes) {
        List<Integer> result = new ArrayList<>();
        int currentTime = 0;
        boolean ferryOnTheLeft = true;
        Queue<Car> carsOnTheLeft = new LinkedList<>();
        Queue<Car> carsOnTheRight = new LinkedList<>();
        for (Car car : cars) {
            if (car.getSide().equals("left")) {
                carsOnTheLeft.add(car);
            }
            else {
                carsOnTheRight.add(car);
            }
        }
        int count = 0;
        while (count < cars.size()) {
            String side = "";
            if (!carsOnTheLeft.isEmpty() && !carsOnTheRight.isEmpty()) {
                side = carsOnTheLeft.peek().getTimeOfArrival() < carsOnTheRight.peek().getTimeOfArrival() ?
                        carsOnTheLeft.peek().getSide() : carsOnTheRight.peek().getSide();
                currentTime = Math.max(currentTime, Math.min(carsOnTheLeft.peek().getTimeOfArrival(), carsOnTheRight.peek().getTimeOfArrival()));
            }
            else if (carsOnTheLeft.isEmpty()) {
                side = "right";
                currentTime = Math.max(currentTime, carsOnTheRight.peek().getTimeOfArrival());
            }
            else {
                side = "left";
                currentTime = Math.max(currentTime, carsOnTheLeft.peek().getTimeOfArrival());
            }
            int carsOnFerry = 0;

            if (side.equals("left")) {
                if (ferryOnTheLeft) {
                    ferryOnTheLeft = false;
                    while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheLeft.isEmpty()) {
                        if (carsOnTheLeft.peek().getTimeOfArrival() <= currentTime) {
                            Car carMoved = carsOnTheLeft.remove();
                            result.add(currentTime + minutes);
                            carsOnFerry++;
                        }
                        else {
                            break;
                        }
                    }
                    currentTime += minutes;
                }
                else {
                    ferryOnTheLeft = true;
                    while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheLeft.isEmpty()) {
                        if (carsOnTheLeft.peek().getTimeOfArrival() <= currentTime) {
                            Car carMoved = carsOnTheLeft.remove();
                            result.add(minutes + currentTime + minutes);
                            carsOnFerry++;
                        }
                        else {
                            break;
                        }
                    }
                    currentTime += minutes;
                }
            }

            else {
                if (ferryOnTheLeft) {
                    while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheRight.isEmpty()) {
                        if (carsOnTheRight.peek().getTimeOfArrival() <= currentTime) {
                            Car carMoved = carsOnTheRight.remove();
                            result.add(minutes + currentTime + minutes);
                            carsOnFerry++;
                        }
                        else {
                            break;
                        }
                    }
                    currentTime += minutes * 2;
                }
                else {
                    while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheRight.isEmpty()) {
                        if (carsOnTheRight.peek().getTimeOfArrival() <= currentTime) {
                            Car carMoved = carsOnTheRight.remove();
                            result.add(currentTime + minutes);
                            carsOnFerry++;
                        }
                        else {
                            break;
                        }
                    }
                    currentTime += minutes * 2;
                    ferryOnTheLeft = true;
                }
            }

            count += carsOnFerry;
        }
        return result;
    }

    private static void print(List<Integer> result, int count, int limit) {
        for (Integer i : result) {
            System.out.println(i);
        }
        if (count < limit) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        List<List<Integer>> results = new ArrayList<>();
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            int minutes = Integer.parseInt(sc.next());
            int numberOfCars = Integer.parseInt(sc.next());
            List<Car> cars = new ArrayList<>();
            for (int i = 0; i < numberOfCars; i++) {
                int time = Integer.parseInt(sc.next());
                String side = sc.next();
                Car car = new Car(time, side);
                cars.add(car);
            }
            List<Integer> result = solution(cars, n, minutes);
            results.add(result);
        }

        int limit = results.size();
        int count = 1;

        for (List<Integer> result : results) {
            print(result, count, limit);
            count += 1;
        }
        sc.close();
    }
}

//200
//300
//400
//600
//700
//900