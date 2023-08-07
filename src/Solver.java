import java.util.Scanner;
import java.io.*;

public class Solver {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int troom = scanner.nextInt();
        int tcond = scanner.nextInt();
        String program = scanner.nextLine();
        program = scanner.nextLine();

        int result = calc(troom, tcond, program);

        try(BufferedWriter br = new BufferedWriter(new FileWriter("output.txt"))) {
            br.write(String.valueOf(result));
        }
    }

    private static int calc(int troom, int tcond, String program) {
        if (program.equals("auto")) {
            return tcond;
        }
        if (program.equals("fan")) {
            return troom;
        }
        if (program.equals("freeze") && troom >= tcond) {
            return tcond;
        } else if (program.equals("freeze")) {
            return troom;
        }
        if (program.equals("heat") && troom <= tcond) {
            return tcond;
        } else {
            return troom;
        }
    }
}