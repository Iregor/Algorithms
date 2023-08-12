//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B
//Задача 21. Три единицы подряд

/*Задача 
	По данному числу N определите количество последовательностей из нулей и единиц длины N, 
	в которых никакие три единицы не стоят рядом.

Формат ввода
	Во входном файле написано натуральное число N, не превосходящее 35.

Формат вывода
	Выведите количество искомых последовательностей. 
	Гарантируется, что ответ не превосходит 231-1.*/

import java.io.*;

public class ThreeOnesInRow {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			int n = Integer.parseInt(br.readLine());	//less or equal 35
			long[] dp = new long[36];		//sequences with "111"
			long[] allSeq = new long[36];	//all sequences
			long[] res = new long[36];		//sequences with no "111"

			dp[3] = 1;
			allSeq[3] = 2 * 2 * 2;
			res[0] = 1;	//for thi s case "0111", see later
			res[1] = 2;
			res[2] = 2 * 2;
			res[3] = allSeq[3] - dp[3];

			for (int i = 4; i <= n; i++) {
				allSeq[i] = allSeq[i - 1] * 2;
				dp[i] = dp[i-1] * 2 + res[i - 4];		//think of here XXXX0111 where XXXX is sequence with no "111"
				res[i] = allSeq[i] - dp[i];
			}
			bw.write(String.valueOf(res[n]));
		}
	}
}