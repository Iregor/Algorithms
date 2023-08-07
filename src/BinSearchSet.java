import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class BinSearchSet {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int K = Integer.parseInt(line[1]);
            int[] ns = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] ks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Set<Integer> setNs = new HashSet<>(Arrays.stream(ns).boxed().collect(Collectors.toList()));
            for (Integer f : ks) {
                if (setNs.contains(f)) {
                    bw.write("YES");
                    bw.newLine();
                } else {
                    bw.write("NO");
                    bw.newLine();
                }
            }
        }

    }

    private static int binSearch(int f, int[] ns) {
        int l = 0;
        int r = ns.length - 1;
        while(l < r) {
            int m = l + (r - l) / 2;
            if (ns[m] >= f) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (ns[r] != f) {
            return -1;
        } else {
            return r;
        }
    }
}