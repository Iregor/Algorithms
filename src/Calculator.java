import java.io.*;

public class Calculator {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int N = Integer.parseInt(br.readLine());
            int[] dp = new int[N + 1];  //quantity of operations
            dp[1] = 0;
            for (int i = 2; i <= N; i++) {
                dp[i] = Integer.min(dp[i-1], i % 2 == 0 ? dp[i / 2] : 100); //100 is not reachable
                dp[i] = Integer.min(dp[i], i % 3 == 0 ? dp[i / 3] : 100);
                dp[i]++;
            }
            bw.write(String.valueOf(dp[N]));
            bw.newLine();

            //to find sequence of numbers
            int[] numbers = new int[dp[N] + 1];
            numbers[dp[N]] = N;     //number to achieve
            numbers[0] = 1;         //begin from number
            int number = N;
            for (int seq = dp[N] - 1; seq >= 1; seq--) {
                if (dp[number - 1] == seq) {
                    numbers[seq] = number - 1;
                    number = number - 1;
                } else if (number % 2 == 0 && dp[number / 2] == seq) {
                    numbers[seq] = number / 2;
                    number = number / 2;
                } else if (number % 3 == 0 && dp[number / 3] == seq){
                    numbers[seq] = number / 3;
                    number = number / 3;
                }
            }
            for (int i = 0; i < numbers.length; i++) {
                bw.write(numbers[i] + " ");
            }
        }
    }
}