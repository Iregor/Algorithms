import java.util.*;
import java.io.*;

public class CheCity {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		     BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			String[] line = br.readLine().split(" ");
			int objects = Integer.parseInt(line[0]);
			int dist = Integer.parseInt(line[1]);
			line = br.readLine().split(" ");
			int[] obj = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
			long combinations = findAllCombinations(dist, obj);
			bw.write(String.valueOf(combinations));
		}
	}

	private static long findAllCombinations(int dist, int[] obj) {
		int head = 0;
		int tail = 1;
		long combinations = 0;
		while (tail <= obj.length - 1) {
			if (obj[tail] - obj[head] > dist) {
				combinations += obj.length - 1 - tail + 1;
				head++;
			} else {
				tail++;
			}
		}
		return combinations;
	}
}