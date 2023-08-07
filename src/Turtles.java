import java.util.*;
import java.io.*;

public class Turtles {
	public static void main(String[] args) throws IOException {
		int num;
		Set<Integer> set = new HashSet<>();
		try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			num = Integer.parseInt(br.readLine());
			for(int i = 1; i <= num; i++) {
				String row = br.readLine();
				String[] numbers = row.split(" ");
				int a = Integer.parseInt(numbers[0]);
				int b = Integer.parseInt(numbers[1]);
				if (a < 0 || b < 0) {
					continue;
				}
				if (a + b + 1 != num) {
					continue;
				}
				set.add(a);
			}
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			bw.write(String.valueOf(set.size()));
		}
	}

	private static int findMaxSetSize(HashMap<Integer, HashSet<Integer>> map) {
		int max = 0;
		for (HashSet<Integer> set : map.values()) {
			if (set.size() > max) {
				max = set.size();
			}
		}
		return max;
	}
}