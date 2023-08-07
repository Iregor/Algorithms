import java.util.*;
import java.io.*;

public class BinSearch {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			String[] line = br.readLine().split(" ");
			int N = Integer.parseInt(line[0]);
			int K = Integer.parseInt(line[1]);
			int[] ns = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] ks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int f : ks) {
				int index = binSearch(f, ns);
				if (index == -1) {
					bw.write("NO");
					bw.newLine();
				} else {
					bw.write("YES");
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