//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B
//Задача 20. Пирамидальная сортировка

/*Отсортируйте данный массив. Используйте пирамидальную сортировку.

Формат ввода
    Первая строка входных данных содержит количество элементов в массиве N, N ≤ 105. Далее задаются N целых чисел, не превосходящих по абсолютной величине 109.

Формат вывода
    Выведите эти числа в порядке неубывания.*/

import java.io.*;
import java.util.Arrays;

public class HeapSorting {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            br.readLine();
            Heap heap = new Heap();
            for (int n : Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()) {
                heap.insert(n);
            }
            Integer n;
            while ((n = heap.delete()) != null) {
                bw.write(n + " ");
            }
        }
    }

    public static class Heap {
        private int[] data;
        private int indexOfLast;        //position to insert (not to read)
        private int initialSize;
        private int size;

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

        private Integer delete() {
            if (size == 0) {
                return null;
            }
            int deletedElement = data[0];
            data[0] = data[--indexOfLast];
            size--;
            normalizeHeapDown(0);
            return deletedElement;
        }

        private void normalizeHeapUp(int n) {
            int parentIndex = (n - 1) / 2;
            if (parentIndex >= 0 && data[parentIndex] > data[n]) {
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
                if (data[leftChIndex] < data[n]) {
                    swap(leftChIndex, n);
                    normalizeHeapDown(leftChIndex);
                }
            } else {                                    //node has two child
                int indexOfMinCh = leftChIndex;
                if (data[rightChIndex] < data[leftChIndex]) {
                    indexOfMinCh = rightChIndex;
                }
                if (data[indexOfMinCh] < data[n]) {
                    swap(indexOfMinCh, n);
                    normalizeHeapDown(indexOfMinCh);
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