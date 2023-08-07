import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class TheGreatLinelandMigration {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int cities = Integer.parseInt(br.readLine());
            int[] lifeExp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Stack<City> stack = new Stack<>();
            for (int i = 0; i < lifeExp.length; i++) {
                int cityExp = lifeExp[i];
                while (stack.size() > 0 && stack.peek().value > cityExp) {
                    lifeExp[stack.pop().index] = i;
                }
                stack.push(new City(cityExp, i));
            }
            while (!stack.empty()) {
                lifeExp[stack.pop().index] = -1;
            }
            String ans = Arrays.stream(lifeExp).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            bw.write(ans);
        }
    }

    private static class City {
        int value;
        int index;

        public City(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
