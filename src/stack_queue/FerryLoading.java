package stack_queue;

import java.util.*;

class Car {
    private final int timeOfArrival;
    private final String side;

    public Car(int timeOfArrival, String side) {
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
            // kiểm tra xe tiếp theo cần chuyển nằm ở bờ nào, cập nhật thời gian bắt đầu tính
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

            // nếu cần chuyển từ bờ trái qua bờ phải
            if (side.equals("left")) {
                if (ferryOnTheLeft) {
                    // nếu phà đang ở bên trái thì đổi biến thành false, tức là sau lần này phà đã sang phải
                    // vì cùng hướng nên chỉ add currentTime + minutes (là thời gian sang sông)
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
                    ferryOnTheLeft = false;
                }
                else {
                    // nếu phà đang bên phải thì phải + minutes để phà qua bờ trái chuyển xe
                    currentTime += minutes;
                    while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheLeft.isEmpty()) {
                        if (carsOnTheLeft.peek().getTimeOfArrival() <= currentTime) {
                            Car carMoved = carsOnTheLeft.remove();
                            result.add(minutes + currentTime);
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
                // nếu xe cần chuyển ở bên phải
                if (ferryOnTheLeft) {
                    // nếu phà đang bên trái thì cần thời gian minutes để phà qua bờ trái đón xe
                    currentTime += minutes;
                    while (carsOnFerry < numberOfCarsOnFerry && !carsOnTheRight.isEmpty()) {
                        if (carsOnTheRight.peek().getTimeOfArrival() <= currentTime) {
                            Car carMoved = carsOnTheRight.remove();
                            result.add(minutes + currentTime);
                            carsOnFerry++;
                        }
                        else {
                            break;
                        }
                    }
                    currentTime += minutes;
                }
                else {
                    // nếu phà đang ở bên phải sẵn rồi thì không cần mất thời gian qua bờ nữa, sau lần này đổi
                    // biến boolean thành true biểu thị phà đã sang trái
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
                    currentTime += minutes;
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
//500
//600
//700
//900
//900
//1100
//1200
//1300
//1500
//1600
//1800
//1900
//2000
//2200
//2400
//2600
//2700