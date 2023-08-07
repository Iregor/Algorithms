import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class RisingList {
	public static void main(String [] args) throws IOException {
		List<Integer> ints = new LinkedList<>();
		try(Scanner sc = new Scanner(new File("input.txt"))) {
			while(sc.hasNext()) {
				int element = sc.nextInt();
				if (element != -2_000_000_000) {
					ints.add(element);
				}
			}
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			bw.write(solve(ints));
		}
	}

	private static String solve(List<Integer> ints) {
		boolean isConstant = true;
		boolean isAscending = true;
		boolean isWeaklyAscending = true;
		boolean isDescending = true;
		boolean isWeaklyDescending = true;
		if (ints.isEmpty() || ints.size() == 1) {
			return "RANDOM";
		}
		Integer[] in = ints.toArray(new Integer[ints.size()]);
		for (int i = 1; i < in.length; i++) {
			if (!in[i].equals(in[i-1])) {
				isConstant = false;
			}
			if (in[i] <= in[i-1]) {
				isAscending = false;
			}
			if (in[i] < in[i-1]) {
				isWeaklyAscending = false;
			}
			if (in[i] >= in[i-1]) {
				isDescending = false;
			}
			if (in[i] > in[i-1]) {
				isWeaklyDescending = false;
			}
		}
		if (isConstant) {
			return "CONSTANT";
		}
		if (isAscending) {
			return "ASCENDING";
		}
		if(isWeaklyAscending) {
			return "WEAKLY ASCENDING";
		}
		if(isDescending) {
			return "DESCENDING";
		}
		if(isWeaklyDescending) {
			return "WEAKLY DESCENDING";
		}
		return "RANDOM";
	}
}