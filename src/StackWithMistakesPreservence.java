//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B. Задача 11. 

/*Научитесь пользоваться стандартной структурой данных stack для целых чисел. 
Напишите программу, содержащую описание стека и моделирующую работу стека, реализовав все указанные здесь методы. 
Программа считывает последовательность команд и в зависимости от команды выполняет ту или иную операцию. 
После выполнения каждой команды программа должна вывести одну строчку. Возможные команды для программы:

push n
Добавить в стек число n (значение n задается после команды). Программа должна вывести ok.

pop
Удалить из стека последний элемент. Программа должна вывести его значение.

back
Программа должна вывести значение последнего элемента, не удаляя его из стека.

size
Программа должна вывести количество элементов в стеке.

clear
Программа должна очистить стек и вывести ok.

exit
Программа должна вывести bye и завершить работу.

Перед исполнением операций back и pop программа должна проверять, содержится ли в стеке хотя бы один элемент. Если во входных данных встречается операция back или pop, и при этом стек пуст, то программа должна вместо числового значения вывести строку error.
*/

import java.util.*;
import java.io.*;

public class StackWithMistakesPreservence {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			String line;
			Stack stack = new Stack();
			while ((line = br.readLine()) != null) {
				if (line.contains("push")) {
					int number = Integer.parseInt(line.split(" ")[1]);
					bw.write(String.valueOf(stack.push(number)));
					bw.newLine();
					continue;
				}
				if (line.contains("exit")) {
					bw.write("bye");
					break;
				}
				switch (line) {
					case "pop":
						Integer pop = stack.pop();
						bw.write(pop != null ? String.valueOf(pop) : "error");
						bw.newLine();
						break;
					case "back":
						Integer back = stack.back();
						bw.write(back != null ? String.valueOf(back) : "error");
						bw.newLine();
						break;
					case "size":
						bw.write(String.valueOf(stack.size()));
						bw.newLine();
						break;
					case "clear":
						bw.write(stack.clear());
						bw.newLine();
						break;
				}
			}
		}
	}

	private static class Stack {
		private int capacity;
		private int[] data;
		private int current;
		private int size;

		public Stack() {
			this.capacity = 32;
			this.data = new int[capacity];
			this.current = 0;
			this.size = 0;
		}

		public Stack(int capacity) {
			this.capacity = capacity;
			this.data = new int[capacity];
			this.current = 0;
			this.size = 0;
		}

		public String push(int ob) {
			if (current == capacity - 1) {
				migrate();
			}
			size++;
			current++;
			data[current - 1] = ob;
			return "ok";
		}

		public Integer pop() {
			if (current != 0) {
				size--;
				current--;
				return data[current];
			}
			return null;
		}

		public Integer back() {
			return current != 0 ? data[current -1] : null;
		}

		public int size() {
			return size;
		}

		public String clear() {
			current = 0;
			size = 0;
			capacity = 32;
			data = new int[capacity];
			return "ok";
		}

		private void migrate() {
			capacity *= 2;
			int[] newData = new int[capacity];
			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
	}
}

