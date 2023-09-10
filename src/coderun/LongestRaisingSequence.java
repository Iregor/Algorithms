/*
CodeRun. 28. НВП с восстановлением ответа.
Дана последовательность, требуется найти её наибольшую возрастающую подпоследовательность.

Формат ввода
В первой строке входных данных задано число N — длина последовательности.
Во второй строке задается сама последовательность (разделитель — пробел).
Элементы последовательности — целые числа, не превосходящие 10000 по модулю.

Формат вывода
Требуется вывести наибольшую возрастающую подпоследовательность данной
последовательности. Если таких подпоследовательностей несколько,
необходимо вывести одну (любую) из них.
*/

package coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class LongestRaisingSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = reader.readLine();
        int N = Integer.parseInt(line);
        int[] data = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] seqLengths = new int[data.length];
        seqLengths[0] = 1;
        //to determine longest sequence until each data element
        for (int i = 1; i < seqLengths.length; i++) {
            int maxLength = 0;
            for (int j = 0; j < i; j++) {
                if (data[j] < data[i] && seqLengths[j] > maxLength) {
                    maxLength = seqLengths[j];
                }
            }
            seqLengths[i] = maxLength + 1;
        }
        //to find index of last longest sequence element
        int maxIndex = seqLengths.length - 1;
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (seqLengths[i] > seqLengths[maxIndex]) {
                maxIndex = i;
            }
        }
        //to get longest sequence in stack
        Stack<Integer> ansSeq = new Stack<>();
        ansSeq.push(data[maxIndex]);
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (data[i] < data[maxIndex] && (seqLengths[i] == seqLengths[maxIndex] - 1)) {
                ansSeq.push(data[i]);
                maxIndex = i;
            }
        }
        //to print answer
        while (!ansSeq.isEmpty()) {
            writer.write(ansSeq.pop() + " ");
        }
        reader.close();
        writer.close();
    }
}
