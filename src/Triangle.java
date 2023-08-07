
import java.util.Scanner;
import java.io.*;


public class Triangle {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        String res = isTriangle(a, b, c);

        try(BufferedWriter br = new BufferedWriter(new FileWriter("output.txt"))) {
			br.write(res);
        }
	}

    static String isTriangle(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "NO";
        }
        if (a + b > c || a + c > b || b + c > a)
            return "YES";
        return "NO";
    }
}