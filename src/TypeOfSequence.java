import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class TypeOfSequence {
	public static void main(String [] args) throws IOException {
		List<Integer> ints = new LinkedList<>();
		try(Scanner sc = new Scanner(new File("input.txt"))) {
			while(sc.hasNext()) {
				ints.add(sc.nextInt());
			}
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			bw.write(solve(ints));
		}
	}

	private static String solve(List<Integer> ints) {
		if (ints.isEmpty()) {
			return "NO";
		}
		Integer[] in = ints.toArray(new Integer[ints.size()]);
		for (int i = 1; i < in.length; i++) {
			if (in[i] <= in[i-1]) {
				return "NO";
			}
		}
		return "YES";
	}
}