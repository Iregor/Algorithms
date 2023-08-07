import java.io.*;

public class MaxThreeNumberCompositionInt {
    private static int max1;
    private static int max2;
    private static int max3;
    private static int min1;
    private static int min2;
    private static boolean ismax1;
    private static boolean ismax2;
    private static boolean ismax3;
    private static boolean ismin2;
    private static boolean ismin1;

    public static void main(String[] args) throws IOException {
        int number;
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            String[] numbers = sb.toString().split(" ");
            for (int i = 0; i < numbers.length; i++) {
                determineMax(Integer.parseInt(numbers[i]));
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(solve());
        }
    }

    private static void determineMax(int number) {
        int buff;
        if (ismax1 == false) {
            ismax1 = true;
            max1 = number;
            return;
        }
        if (max1 < number) {
            buff = max1;
            max1 = number;
            number = buff;
        }
        if (ismax2 == false) {
            ismax2 = true;
            max2 = number;
            return;
        }
        if (max2 < number) {
            buff = max2;
            max2 = number;
            number = buff;
        }
        if (ismax3 == false) {
            ismax3 = true;
            max3 = number;
            return;
        }
        if (max3 < number) {
            buff = max3;
            max3 = number;
            number = buff;
        }
        if (ismin1 == false) {
            ismin1 = true;
            min1 = number;
            return;
        }
        if (min1 > number) {
            buff = min1;
            min1 = number;
            number = buff;
        }
        if (ismin2 == false) {
            ismin2 = true;
            min2 = number;
            return;
        }
        if (min2 > number) {
            min2 = number;
        }
    }

    private static String solve() {
        if (ismin1 == true && ismin2 == true) {
            return (long)min1 * (long)min2 * (long)max1 < (long)max3 * (long)max2 * (long)max1
                    ? max3 + " " + max2 + " " + max1
                    : min1 + " " + min2 + " " + max1;
        }
        return max3 + " " + max2 + " " + max1;
    }
}