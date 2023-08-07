import java.util.*;
import java.io.*;

public class BeautyTrees {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		     BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			String[] line = br.readLine().split(" ");
			int treesSize = Integer.parseInt(line[0]);
			int colors = Integer.parseInt(line[1]);
			int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int ansHead = 0;
			int ansTail = treesSize - 1;
			int head = 0;
			int tail = colors - 1;
			Map<Integer, Integer> occurrences = new HashMap<>();
			for (int i = head; i <= tail; i++) {
				occurrences.compute(trees[i], (k,v) -> v == null ? 1 : v + 1);
			}
			int minLength = treesSize;
			while (tail < treesSize) {
				if (occurrences.size() == colors) {
					if (minLength > tail - head + 1) {
						minLength = tail - head + 1;
						ansHead = head;
						ansTail = tail;
					}
					occurrences.compute(trees[head], (k, v) -> v == 1 ? null : v - 1);
					head++;
				} else {
					if (tail < treesSize - 1) {
						tail++;
						occurrences.compute(trees[tail], (k, v) -> v == null ? 1 : v + 1);
					} else {
						break;
					}
				}
			}
			bw.write(String.valueOf(ansHead + 1) + " " + String.valueOf(ansTail + 1));
		}
	}
}