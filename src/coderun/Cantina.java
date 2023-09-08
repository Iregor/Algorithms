package coderun;

import java.io.*;
import java.util.Stack;

public class Cantina {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int days = Integer.parseInt(reader.readLine());
        if (days == 0) {
            writer.write(String.valueOf(0));
            writer.newLine();
            writer.write(0 + " " + 0);
            writer.close();
            reader.close();
            return;
        }
        int[] prices = new int[days + 1];
        for (int i = 1; i <= days; i++) {
            prices[i] = Integer.parseInt(reader.readLine());
        }
        int[][] costs = new int[days + 1][days + 1];
        fillArrayWithNumber(costs, 300 * 100 + 1);
        costs[0][0] = 0;

        for (int day = 1; day <= days; day++) {
            for (int coups = 0; coups <= days; coups++) {
                if (coups < days && costs[coups][day] > costs[coups + 1][day - 1]) {        //to spend coup
                    costs[coups][day] = costs[coups + 1][day - 1];
                }
                if (prices[day] > 100) {
                    if (coups > 0 && costs[coups][day] > costs[coups - 1][day - 1] + prices[day]) {    //bo buy dinner and get coup
                        costs[coups][day] = costs[coups - 1][day - 1] + prices[day];
                    }
                } else if (costs[coups][day] > costs[coups][day - 1] + prices[day]) {    //to buy dinner and get no coup
                    costs[coups][day] = costs[coups][day - 1] + prices[day];
                }
            }
        }

        //to find out result
        int minCost = Integer.MAX_VALUE;
        int coupsLeft = 0;

        for (int coups = 0; coups <= days; coups++) {
            if (costs[coups][days] <= minCost) {
                minCost = costs[coups][days];
                coupsLeft = coups;
            }
        }

        int currentCoups = coupsLeft;
        int coupsSpent = 0;
        Stack<Integer> daysToUseCoup = new Stack<>();
        for (int day = days - 1; day >= 1; day--) {
            if (currentCoups < days && costs[currentCoups + 1][day] == costs[currentCoups][day + 1]) {  //to spend coup
                coupsSpent++;
                currentCoups++;
                daysToUseCoup.push(day + 1);
            } else if (prices[day + 1] > 100 && costs[currentCoups - 1][day] + prices[day + 1] == costs[currentCoups][day + 1]) {  //to buy and get coup
                currentCoups--;
            }
        }

        writer.write(String.valueOf(minCost));
        writer.newLine();
        writer.write(coupsLeft + " " + coupsSpent);
        writer.newLine();
        while (!daysToUseCoup.isEmpty()) {
            writer.write(String.valueOf(daysToUseCoup.pop()));
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

    private static void fillArrayWithNumber(int[][] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = number;
            }
        }
    }
}
