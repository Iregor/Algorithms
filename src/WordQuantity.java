import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

public class WordQuantity {
    public static void main(String[] args) throws IOException {
        HashSet<String> words = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Stream<String> str = br.lines();
            str.flatMap(line -> {
                        return Arrays.stream(line.split(" "));
                    })
                    .forEach(word -> {
                        word = word.trim();
                        if (!word.equals("") && !word.equals(" ") && !word.equals("\n") && !word.equals("\t") && !word.equals("\r")) {
                            words.add(word);
                        }
					});
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(String.valueOf(words.size()));
        }
    }
}