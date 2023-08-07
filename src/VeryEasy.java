import java.io.*;

public class VeryEasy {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);  //1 ... 2 x 10^8
            int x = Integer.parseInt(line[1]);  //1 ... 10
            int y = Integer.parseInt(line[2]);  //1 ... 10
            int maxTime = N * Integer.max(x, y) / 2 + x;
            int minTimeToCopy = Integer.min(x, y);
            int l = 0;
            int r = maxTime;
            while (l < r) {
                int m = (l + r) / 2;
                if ((minTimeToCopy <= m ? 1 : 0) + (m - minTimeToCopy) / x + (m - minTimeToCopy) / y >= N) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            bw.write(String.valueOf(l));
        }
    }
}
