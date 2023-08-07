import java.io.*;
import java.util.*;

public class BankCount {
	private static HashMap<String, Integer> counts = new HashMap<>();

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			 BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] operation = line.split(" ");
				switch (operation[0]) {
					case "DEPOSIT":
						deposit(operation[1], Integer.parseInt(operation[2]));
						break;
					case "WITHDRAW":
						withdraw(operation[1], Integer.parseInt(operation[2]));
						break;
					case "BALANCE":
						bw.write(String.valueOf(balance(operation[1])));
						bw.newLine();
						break;
					case "TRANSFER":
						transfer(operation[1], operation[2], Integer.parseInt(operation[3]));
						break;
					case "INCOME":
						income(Integer.parseInt(operation[1]));
						break;
				}
			}
		}
	}

	private static void deposit(String name, Integer value) {
		counts.compute(name, (k, v) -> v == null ? value : v + value);
	}

	private static void withdraw(String name, Integer value) {
		counts.compute(name, (k, v) -> v == null ? -value : v - value);
	}

	private static String balance(String name) {
		Integer balance = counts.get(name);
		return balance != null ? String.valueOf(balance) : "ERROR";
	}

	private static void transfer(String from, String to, Integer sum) {
		counts.compute(from, (k, v) -> v == null ? -sum : v - sum);
		counts.compute(to, (k, v) -> v == null ? sum : v + sum);
	}

	private static void income(int p) {
		for (Map.Entry<String, Integer> entry : counts.entrySet()) {
			counts.compute(entry.getKey(), (k, v) -> {
				if (v != null && v > 0) {
					return v + v * p / 100;
				}
				return v;
			});
		}
	}
}