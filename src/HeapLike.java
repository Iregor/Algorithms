//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B
//Задача 19. Хипуй

/*В этой задаче вам необходимо самостоятельно (не используя соответствующие классы и функции стандартной библиотеки) организовать структуру данных Heap 
для хранения целых чисел, над которой определены следующие операции: 
a) Insert(k) – добавить в Heap число k ; 
b) Extract достать из Heap наибольшее число (удалив его при этом).

Формат ввода
	В первой строке содержится количество команд N (1 ≤ N ≤ 100000), далее следуют N команд, каждая в своей строке. 
	Команда может иметь формат: “0 <число>” или “1”, обозначающий, соответственно, операции Insert(<число>) и Extract. 
	Гарантируется, что при выполнении команды Extract в структуре находится по крайней мере один элемент.

Формат вывода
	Для каждой команды извлечения необходимо отдельной строкой вывести число, полученное при выполнении команды Extract.*/

import java.io.*;

public class HeapLike {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int commands = Integer.parseInt(br.readLine());
            Heap heap = new Heap();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("0")) {
                    heap.insert(Integer.parseInt(line.split(" ")[1]));
                } else {
                    bw.write(String.valueOf(heap.delete()));
					bw.newLine();
                }
            }
        }
    }

    public static class Heap {
        private int[] data;
        int indexOfLast;        //position to insert (not to read)
        int initialSize;
        int size;

        public Heap() {
            initialSize = 8;
            data = new int[initialSize];
        }

        public void insert(int n) {
            if (indexOfLast == data.length) {
                expanse();
            }
            data[indexOfLast++] = n;
            size++;
            normalizeHeapUp(indexOfLast - 1);
        }

        private int delete() {
            int deletedElement = data[0];
            data[0] = data[--indexOfLast];
            normalizeHeapDown(0);
            return deletedElement;
        }

        private void normalizeHeapUp(int n) {
            int parentIndex = (n - 1) / 2;
            if (parentIndex >= 0 && data[parentIndex] < data[n]) {
                swap(parentIndex, n);
                normalizeHeapUp(parentIndex);
            }
        }

        private void normalizeHeapDown(int n) {
            int leftChIndex = 2 * n + 1;
            int rightChIndex = 2 * n + 2;
            if (leftChIndex >= indexOfLast) {            //node has no child
                return;
            }
            if (leftChIndex == indexOfLast - 1) {        //node has one child
                if (data[leftChIndex] > data[n]) {
                    swap(leftChIndex, n);
                    normalizeHeapDown(leftChIndex);
                }
            } else {									//node has two child
				int indexOfMaxCh = leftChIndex;
				if (data[rightChIndex] > data[leftChIndex]) {
					indexOfMaxCh = rightChIndex;
				}
				if (data[indexOfMaxCh] > data[n]) {
					swap(indexOfMaxCh, n);
					normalizeHeapDown(indexOfMaxCh);
				}
			}
        }

        private void swap(int parentIndex, int currentIndex) {
            int temp = data[parentIndex];
            data[parentIndex] = data[currentIndex];
            data[currentIndex] = temp;
        }

        private void expanse() {
            int[] newData = new int[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }
}