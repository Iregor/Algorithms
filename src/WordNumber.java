import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WordNumber {
	public static void main(String[] args) throws IOException {
		List<String> words;
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			words = br
				.lines()
				.flatMap(line -> Arrays.stream(line.split(" ")))
				.filter(str -> !str.equals(""))
				.collect(Collectors.toList());
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			HashMap<String, Integer> map = new HashMap<>();
			for (String str : words) {
				Integer occurrence;
				bw.write(String.valueOf((occurrence = map.get(str)) != null ? occurrence : 0) + " ");
				map.put(str, occurrence != null ? occurrence + 1 : 1);
			}
		}

	}
}