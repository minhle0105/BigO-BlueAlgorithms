package stack_queue;

import java.util.*;

class Car {
    private final int id;
    private final int timeOfArrival;
    private final String side;

    public int getId() {
        return id;
    }

    public Car(int id , int timeOfArrival, String side) {
        this.id = id;
        this.timeOfArrival = timeOfArrival;
        this.side = side;
    }

    public int getTimeOfArrival() {
        return timeOfArrival;
    }

    public String getSide() {
        return side;
    }

}

public class FerryLoading {

    private static List<Integer> solution(List<Car> cars, int numberOfCarsOnFerry, int minutes) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            result.add(-1);
        }
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
        String side = "left";
        int count = 0;
        while (count < cars.size()) {
            // kiểm tra xe tiếp theo cần chuyển nằm ở bờ nào, cập nhật thời gian bắt đầu tính

            if (!carsOnTheLeft.isEmpty() && !carsOnTheRight.isEmpty()) {
//                side = carsOnTheLeft.peek().getTimeOfArrival() < carsOnTheRight.peek().getTimeOfArrival() ?
//                        carsOnTheLeft.peek().getSide() : carsOnTheRight.peek().getSide();
                currentTime = Math.max(currentTime, Math.min(carsOnTheLeft.peek().getTimeOfArrival(), carsOnTheRight.peek().getTimeOfArrival()));
            }
            else if (carsOnTheLeft.isEmpty()) {
                //side = "right";
                currentTime = Math.max(currentTime, carsOnTheRight.peek().getTimeOfArrival());
            }
            else {
                //side = "left";
                currentTime = Math.max(currentTime, carsOnTheLeft.peek().getTimeOfArrival());
            }

            // Ý tưởng: Nếu xe đang bờ trái thì cứ lấy càng nhiều càng tốt xong chở
            // qua phải, còn xe ở phait thì ngược
            int carsOnFerry = 0;
            if(side.equals("left")) {
                while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheLeft.isEmpty()) {
                    if (carsOnTheLeft.peek().getTimeOfArrival() <= currentTime) {
                        Car carMoved = carsOnTheLeft.remove();
                        result.set(carMoved.getId(), currentTime + minutes);
                        carsOnFerry++;
                    }
                    else {
                        break;
                    }
                }
                currentTime += minutes;
                side = "right";
            }
            else {
                while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheRight.isEmpty()) {
                    if (carsOnTheRight.peek().getTimeOfArrival() <= currentTime) {
                        Car carMoved = carsOnTheRight.remove();
                        result.set(carMoved.getId(), minutes + currentTime);
                        carsOnFerry++;
                    }
                    else {
                        break;
                    }
                }

                currentTime += minutes;
                side = "left";
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
                Car car = new Car(i, time, side);
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