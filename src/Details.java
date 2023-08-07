import java.util.Scanner;
import java.io.*;

public class Details {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("input.txt"));
		int N = sc.nextInt();	//N кг сплава
		int K = sc.nextInt();	//K кг - масса заготовки
		int M = sc.nextInt();	//M кг - масса детали
		sc.close();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			bw.write(String.valueOf(solve(N, K, M, 0)));
		}
	}

	private static int solve(int N, int K, int M, int det) {
		if (K < M) {
			return 0;
		}
		while (N >= K) {
			int nZag = N / K;
			det = det + nZag * (K / M);
			N = N % K + nZag * (K % M);
		}
		return det;
	}
}