import java.io.*;
import java.util.HashSet;
import java.util.stream.Stream;

public class QuantityOfNumbers {
    public static void main(String[] args) throws IOException {
        int[] ints;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String[] numbers = br.readLine().split(" ");
            ints = Stream.of(numbers).mapToInt(Integer::parseInt).toArray();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(String.valueOf(solve(ints)));
        }
    }

    public static int solve(int[] ints) {
        HashSet<Integer> set = new HashSet<>();
        for (int number : ints) {
            set.add(number);
        }
        return set.size();
    }
}