import java.util.Scanner;
import java.io.*;

/*Поезда стоят:
	1 минуту
	Таня не приходит и не уходит в течении этой минуты
Интервал между поездами: 
	a минут для первого пути
	b минут для второго пути
Всего было: 
	n поездов на первом пути
	m поездов на втором пути
Найти:
	минимальное и максимально время, которое Таня была на платформе
	либо сообщить, что она сбилась со счету (вернуть -1)
*/

public class Metro {
	public static void main (String args[]) throws IOException {
		int a;
		int b;
		int n;
		int m;
		try(Scanner sc = new Scanner(new File("input.txt"))) {
			a = sc.nextInt();
			b = sc.nextInt();
			n = sc.nextInt();
			m = sc.nextInt();
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			bw.write(solve(a, b, n, m));
		}
	}

	private static String solve(int a, int b, int n, int m) {
		int min1time = n * 1 + (n - 1) * a;
		int max1time = n * 1 + (n + 1) * a;
		int min2time = m * 1 + (m - 1) * b;
		int max2time = m * 1 + (m + 1) * b;
		if (min1time > max2time || min2time > max1time) {
			return String.valueOf(-1);
		}
		return max(min1time, min2time) + " " + min(max1time, max2time);
	}

	private static int max(int a, int b) {
		return a >= b ? a : b;
	}

	private static int min(int a, int b) {
		return a <= b ? a : b;
	}
}