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

    private static void solution(List<Car> cars, int numberOfCarsOnFerry, int minutes) {
        List<Integer> result = new ArrayList<>();
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

        Car carToBeMovedFirst;
        if (!carsOnTheLeft.isEmpty() && !carsOnTheRight.isEmpty()) {
            carToBeMovedFirst = carsOnTheLeft.peek().getTimeOfArrival() < carsOnTheRight.peek().getTimeOfArrival() ?
                    carsOnTheLeft.peek() : carsOnTheRight.peek();
        }
        else if (carsOnTheLeft.isEmpty()) {
            carToBeMovedFirst = carsOnTheRight.peek();
        }
        else {
            carToBeMovedFirst = carsOnTheLeft.peek();
        }

        String firstSide = carToBeMovedFirst.getSide();
        int currentTime = carToBeMovedFirst.getTimeOfArrival();

        while (count < cars.size()) {
            Car nextCarToBeMoved;

            if (carsOnTheLeft.isEmpty()) {
                nextCarToBeMoved = carsOnTheRight.remove();
            }

            else if (carsOnTheRight.isEmpty()) {
                nextCarToBeMoved = carsOnTheLeft.remove();
            }

            else {
                nextCarToBeMoved = carsOnTheLeft.peek().getTimeOfArrival() < carsOnTheRight.peek().getTimeOfArrival() ?
                        carsOnTheLeft.remove() : carsOnTheRight.remove();
            }

            if (ferryOnTheLeft) {
                if (nextCarToBeMoved.getSide().equals("left")) {
                    result.add(Math.max(currentTime, nextCarToBeMoved.getTimeOfArrival()) + minutes);
                }
                else {
                    currentTime = currentTime + minutes;
                    result.add(Math.max(currentTime, nextCarToBeMoved.getTimeOfArrival()) + minutes);
                }
                ferryOnTheLeft = false;
            }
            else {
                if (nextCarToBeMoved.getSide().equals("right")) {
                    result.add(Math.max(currentTime, nextCarToBeMoved.getTimeOfArrival()) + minutes);
                }
                else {
                    currentTime = currentTime + minutes;
                    result.add(Math.max(currentTime, nextCarToBeMoved.getTimeOfArrival()) + minutes * 2);
                }
                ferryOnTheLeft = !ferryOnTheLeft;
            }
            count++;

        }

        for (Integer i : result) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
        solution(cars, n, minutes);
        sc.close();
    }
}