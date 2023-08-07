import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordRecursion {
    public static void main(String[] args) throws IOException {
        List<Integer> ints = new ArrayList<>();
        int count;
        try (Scanner sc = new Scanner(new File("input.txt"))) {
            count = sc.nextInt();
            while (sc.hasNext()) {
                ints.add(sc.nextInt());
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(solve(ints, count));
        }
    }

    private static String solve(List<Integer> ints, int count) {
        int start = 0;
        List<Integer> intToAdd = new ArrayList<>();
        while (!isPolinome(ints, start, ints.size() - 1)) {
            intToAdd.add(ints.get(start));
            start++;
        }
        StringBuilder sb = new StringBuilder(intToAdd.size() + "\n");
        for (int i = intToAdd.size() - 1; i >= 0; i--) {
            ints.add(intToAdd.get(i));
            sb.append(ints.get(i)).append(" ");
        }
        return sb.toString();
    }

    private static boolean isPolinome(List<Integer> ints, int first, int last) {
        if (ints.get(first).equals(ints.get(last))) {
            if (first == last || first == last - 1) {
                return true;
            } else {
                return isPolinome(ints, first + 1, last - 1);
            }
        } else {
            return false;
        }
    }
}