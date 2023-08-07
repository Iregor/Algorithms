import java.io.*;
import java.util.Arrays;

public class TourismPrefix {
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
                routes[i][0] = Integer.parseInt(line[0]) - 1;
                routes[i][1] = Integer.parseInt(line[1]) - 1;
            }

            int[] heightStraight = new int[picsCount];
            int sum = 0;
            for (int i = 1; i < picsCount; i++) {
                if (pics[i][1] > pics[i-1][1]) {
                    sum += pics[i][1] - pics[i-1][1];
                }
                heightStraight[i] = sum;
            }

            int[] heightBack = new int[picsCount];
            sum = 0;
            for (int i = picsCount - 2; i >= 0; i--) {
                if (pics[i][1] > pics[i+1][1]) {
                    sum += pics[i][1] - pics[i+1][1];
                }
                heightBack[i] = sum;
            }


            for (int i = 0; i < routesCount; i++) {
                int head = routes[i][0];
                int tail = routes[i][1];
                if (head <= tail) {
                    bw.write(String.valueOf(heightStraight[tail] - heightStraight[head]));
                    bw.newLine();
                } else {
                    bw.write(String.valueOf(heightBack[tail] - heightBack[head]));
                    bw.newLine();
                }
            }
        }
    }
}
