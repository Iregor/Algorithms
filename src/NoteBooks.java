import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class NoteBooks {
	public static void main (String[] args) throws IOException {
		Scanner scan = new Scanner(new File("input.txt"));
		int a1 = scan.nextInt();
		int b1 = scan.nextInt();
		int a2 = scan.nextInt();
		int b2 = scan.nextInt();
		scan.close(); 
	
		int[] minTableSize = findTableSize(a1, b1, a2, b2);

		try (BufferedWriter br = new BufferedWriter(new FileWriter("output.txt"))) {
			br.write(minTableSize[0] + " " + minTableSize[1]);
		}
	}

	private static int[] findTableSize(int a1, int b1, int a2, int b2) {
		int S;
		int[] tableSize = new int[2];
		int Smin = max(a1, a2) * (b1 + b2);
		tableSize[0] = max(a1, a2);
		tableSize[1] = b1 + b2;

		S = max(a1, b2) * (b1 + a1);
		if (Smin > S) {
			Smin = S;
			tableSize[0] = max(a1, b2);
			tableSize[1] = b1 + a1;
		}

		S = max(b1, a2) * (a1 + b2);
		if (Smin > S) {
			Smin = S;
			tableSize[0] = max(b1, a2);
			tableSize[1] = a1 + b2;
		}

		S = max(b1, b2) * (a1 + a2);
		if (Smin > S) {
			Smin = S;
			tableSize[0] = max(b1, b2);
			tableSize[1] = a1 + a2;
		}
		
		return tableSize;
	}

	private static int max(int a, int b) {
		return a >= b ? a : b;
	}
}