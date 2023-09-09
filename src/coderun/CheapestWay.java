/*
В каждой клетке прямоугольной таблицы N×M записано некоторое число. Изначально игрок находится в левой верхней клетке.
За один ход ему разрешается перемещаться в соседнюю клетку либо вправо, либо вниз
(влево и вверх перемещаться запрещено). При проходе через клетку с игрока берут столько килограммов еды,
какое число записано в этой клетке (еду берут также за первую и последнюю клетки его пути).

Требуется найти минимальный вес еды в килограммах, отдав которую игрок может попасть в правый нижний угол.

Формат ввода
Вводятся два числа N и M — размеры таблицы.
Затем идет N строк по M чисел в каждой — размеры штрафов в килограммах за прохождение через
соответствующие клетки (числа от 0 до 100).

Формат вывода
Выведите минимальный вес еды в килограммах, отдав которую можно попасть в правый нижний угол.*/

package coderun;

import java.io.*;
import java.util.Arrays;

public class CheapestWay {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int cellWeight = matrix[i][j];
                int wayFromLeft = Integer.MAX_VALUE;
                int wayFromUp = Integer.MAX_VALUE;
                if (i > 0) {
                    wayFromUp = matrix[i - 1][j] + cellWeight;
                }
                if (j > 0) {
                    wayFromLeft = matrix[i][j - 1] + cellWeight;
                }
                if (i != 0 || j != 0) {
                    matrix[i][j] = Integer.min(wayFromUp, wayFromLeft);
                }
            }
        }
        writer.write(String.valueOf(matrix[N - 1][M - 1]));
        reader.close();
        writer.close();
    }
}
