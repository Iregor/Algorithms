import java.util.Scanner;
import java.io.*;

public class SqrtEquation {
    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        scan.close();

        try (BufferedWriter br = new BufferedWriter(new FileWriter(new File("output.txt")))) {
            if (c < 0 || a == 0 && b < 0 || a == 0 && b != c * c || a != 0 && ((c*c - b) % a) != 0) {
                br.write("NO SOLUTION");
            } else if (a == 0) {
                br.write("MANY SOLUTIONS");
            } else {
                br.write(String.valueOf((c * c - b) / a));
            }
        }
    }
}