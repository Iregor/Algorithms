import java.util.*;
import java.io.*;

public class StyleCloses {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		     BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			int shortsSize = Integer.parseInt(br.readLine());
			int[] shorts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int pantsSize = Integer.parseInt(br.readLine());
			int[] pants = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int sPoint = 0;
			int pPoint = 0;
			int difference = -1;
			int[] pair = new int[]{shorts[0], pants[0]};
			while (sPoint < shortsSize && pPoint < pantsSize) {
				int diff = Math.abs(shorts[sPoint] - pants[pPoint]);
				if (difference > diff || difference == -1) {
					difference = diff;
					pair[0] = shorts[sPoint];
					pair[1] = pants[pPoint];
				}
				if (shorts[sPoint] > pants[pPoint]) {
					pPoint++;
				} else if (shorts[sPoint] < pants[pPoint]) {
					sPoint++;
				} else {
					break;
				}
			}
			bw.write(pair[0] + " " + pair[1]);
		}
	}
}