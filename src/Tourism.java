import java.io.*;
import java.util.Arrays;

public class Tourism {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int picsCount = Integer.parseInt(br.readLine());
            int[][] pics = new int[picsCount][2];
            for (int i = 0 ; i < picsCount; i++) {
                String[] line = br.readLine().split(" ");
                pics[i][0] = Integer.parseInt(line[0]);
                pics[i][1] = Integer.parseInt(line[1]);
            }
            int routesCount = Integer.parseInt(br.readLine());
            int[][] routes = new int[routesCount][2];
            for (int i = 0; i < routesCount; i++) {
                String[] line = br.readLine().split(" ");
                routes[i][0] = Integer.parseInt(line[0]);
                routes[i][1] = Integer.parseInt(line[1]);
            }

            for (int i = 0; i < routesCount; i++) {
                int head = routes[i][0];
                int tail = routes[i][1];
                int increment;
                int heightSum = 0;
                if (head > tail) {
                    increment = -1;
                } else {
                    increment = 1;
                }
                while (head != tail) {
                    head = head + increment;
                    if (pics[head - increment - 1][1] < pics[head - 1][1]) {
                        heightSum += pics[head - 1][1] - pics[head - increment - 1][1];
                    }
                }
                bw.write(String.valueOf(heightSum));
                bw.newLine();
            }
        }
    }
}
