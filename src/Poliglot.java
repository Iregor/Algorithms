import java.util.*;
import java.io.*;

public class Poliglot {
	public static void main(String[] args) throws IOException {
			HashSet<String> allLangs = new HashSet<>();
			HashSet<String> commonLangs = new HashSet<>();
			try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
				int rows = Integer.parseInt(br.readLine());
				HashSet<String> studentLangs = new HashSet<>();
				for (int i = 1; i <= rows; i++) {		//to read all students
					int langs = Integer.parseInt(br.readLine());
					studentLangs.clear();
					for (int j = 1; j <= langs; j++) {	//to read all languages
						studentLangs.add(br.readLine());
					}
					allLangs.addAll(studentLangs);
					if (i == 1) {
						commonLangs.addAll(studentLangs);
					} else {
						commonLangs.retainAll(studentLangs);
					}
				}
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
				bw.write(String.valueOf(commonLangs.size()));
				bw.newLine();
				for (String lang : commonLangs) {
					bw.write(lang);
					bw.newLine();
				}
				bw.write(String.valueOf(allLangs.size() + "\n"));
				for (String lang : allLangs) {
					bw.write(lang + "\n");
				}
			}
	}
}