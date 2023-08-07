import java.io.*;
import java.util.Arrays;

public class NumbersSum {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String line = br.readLine();
            int carsCount = Integer.parseInt(line.split(" ")[0]);
            int sumAchieve = Integer.parseInt(line.split(" ")[1]);
            int[] cars = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int head = 0;
            int tail = 0;
            int sum = cars[0];
            int matches = 0;
            while (tail < carsCount) {
                if (sum == sumAchieve) {
                    matches++;
                    sum -= cars[head];
                    head++;
                    tail++;
                    if (tail == carsCount) {
                        break;
                    }
                    sum += cars[tail];
                } else if (sum < sumAchieve) {
                    tail++;
                    if (tail == carsCount) {
                        break;
                    }
                    sum += cars[tail];
                } else {
                    sum -= cars[head];
                    head++;
                }
            }
            bw.write(String.valueOf(matches));
        }
    }
}
