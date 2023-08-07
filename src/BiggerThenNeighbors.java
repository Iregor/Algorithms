import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class BiggerThenNeighbors{
	public static void main(String [] args) throws IOException {
		List<Integer> ints = new LinkedList<>();
		try(Scanner sc = new Scanner(new File("input.txt"))) {
			while(sc.hasNext()) {
				int element = sc.nextInt();
				ints.add(element);
			}
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			bw.write(solve(ints));
		}
	}

	private static String solve(List<Integer> ints) {
		if (ints.size() < 3) {
			return String.valueOf(0);
		}
		Integer[] in = ints.toArray(new Integer[ints.size()]);
		int count = 0;
		for (int i = 1; i < in.length - 1; i++) {
			if (in[i] > in[i-1] && in[i] > in[i+1]) {
				count++;
			}
		}
		return String.valueOf(count);
	}
}