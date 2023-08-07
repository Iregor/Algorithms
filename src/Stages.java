import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Stages {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        int k1 = sc.nextInt();
        int m = sc.nextInt();
        int k2 = sc.nextInt();
        int p2 = sc.nextInt();
        int n2 = sc.nextInt();
        sc.close();

        int[] result = solve(k1, m, k2, p2, n2);

        try (BufferedWriter br = new BufferedWriter(new FileWriter("output.txt"))) {
            br.write(result[0] + " " + result[1]);
        }
    }

    private static int[] solve(int k1, int m, int k2, int p2, int n2) {
        if (k2 < (p2 - 1) * m * 1 + (n2 - 1) * 1 + 1) {
            return new int[]{-1, -1};
        }
        if (n2 > m) {
            return new int[]{-1, -1};
        }
        if (p2 == 1 && n2 == 1) {
            if (k1 < k2) {
                return new int[]{1, 1};
            } else if (m == 1) {
                return new int[]{0, 1};
            } else if (k1 <= k2 * m) {
                return new int[]{1, 0};
            } else {
                return new int[]{0, 0};
            }
        }
        int nMin = k2 / ((p2 - 1) * m + n2);
        if (k2 % ((p2 - 1) * m + n2) != 0) {
            nMin++;
        }
        int nMax = (k2 - 1) / ((p2 - 1) * m + (n2 - 1));
        if (nMin > nMax) {
            return new int[]{-1, -1};
        }
        int[] result = new int[(nMax - nMin + 1) * 2];
        for (int n = nMin; n <= nMax; n++) {
            int p1 = k1 / (m * n);
            if (k1 % (m * n) != 0) {
                p1++;
            }
            int n1 = (k1 - (p1 - 1) * m * n) / n;
            if ((k1 - (p1 - 1) * m * n) % n != 0) {
                n1++;
            }
            result[n - nMin] = p1;
            result[nMax - nMin + 1 + n - nMin] = n1;
        }
        int p1 = result[0];
        int n1 = result[result.length / 2];
        if (result.length != 2) {
            for (int i = 1; i < result.length / 2; i++) {
                if (result[i] != result[i - 1]) {
                    p1 = 0;
                }
                if (result[i + result.length / 2] != result[i + result.length / 2 - 1]) {
                    n1 = 0;
                }
            }
        }
        return new int[]{p1, n1};
    }
}