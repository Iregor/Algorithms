import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Keyboard {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			int bottons = Integer.parseInt(br.readLine());
			String capacityLine = br.readLine();
			int[] capacity = Arrays.stream(capacityLine.split(" ")).mapToInt(Integer::parseInt).toArray();
			int pushes = Integer.parseInt(br.readLine());
			String pushesLine = br.readLine();
			int[] bottonPushes = Arrays.stream(pushesLine.split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] bottonPushed = new int[capacity.length];
			for (int index : bottonPushes) {
				bottonPushed[index-1]++;
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
				for (int i = 0; i < capacity.length; i++) {
					if (capacity[i] >= bottonPushed[i]) {
						bw.write("NO");
					} else {
						bw.write("YES");
					}
					bw.newLine();
				}
			}
		}
	}
}