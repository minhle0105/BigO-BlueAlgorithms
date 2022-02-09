package BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class City implements Comparable<City> {
    int x;
    int y;
    int population;
    double distance;

    public City(int x, int y, int population) {
        this.x = x;
        this.y = y;
        this.population = population;
        this.distance = this.getDistance();
    }

    public double getDistance() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }


    @Override
    public int compareTo(City o) {
        if (this.distance == o.distance) {
            return Integer.compare(this.population, o.population);
        }
        return Double.compare(this.distance, o.distance);
    }
}

public class Megacity {

    final static int threshold = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int s = Integer.parseInt(sc.next());
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(sc.next());
            int y = Integer.parseInt(sc.next());
            int population = Integer.parseInt(sc.next());
            City city = new City(x, y, population);
            cities.add(city);
        }
        Collections.sort(cities);
        int index = 0;
        while (s < threshold && index < cities.size()) {
            s += cities.get(index).population;
            if (s < threshold) {
                index++;
            }
        }
        double radius;
        if (s < threshold) {
            radius = -1;
        }
        else {
            radius = cities.get(index).getDistance();
        }
        System.out.println(radius);
        sc.close();
    }
}
