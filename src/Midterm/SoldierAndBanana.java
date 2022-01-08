package Midterm;

import java.util.Scanner;

public class SoldierAndBanana {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = Integer.parseInt(sc.next());
        int amountOfMoney = Integer.parseInt(sc.next());
        int numberOfBananas = Integer.parseInt(sc.next());
        int[] prices = new int[numberOfBananas];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = k * (i+1);
        }
        int totalAmount = 0;
        for (int price : prices) {
            totalAmount += price;
        }
        int currentAmountSpent = 0;
        int index = 0;
        while (currentAmountSpent < amountOfMoney && index < prices.length) {
            currentAmountSpent += prices[index++];
        }
        if (currentAmountSpent == 0) {
            System.out.println(totalAmount);
        }
        else if (currentAmountSpent <= amountOfMoney) {
            System.out.println(0);
        }
        else {
            int result = currentAmountSpent - amountOfMoney;
            while (index < prices.length) {
                result += prices[index++];
            }
            System.out.println(result);
        }

        sc.close();
    }
}
