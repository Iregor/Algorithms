//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B
//Задача 22. Grasshopper

/*Задача
    У одного из студентов в комнате живёт кузнечик, который очень любит прыгать по клетчатой одномерной доске.
    Длина доски — N клеток. К его сожалению, он умеет прыгать только на 1, 2, …, k клеток вперёд.

    Однажды студентам стало интересно, сколькими способами кузнечик может допрыгать из первой клетки до последней.
    Помогите им ответить на этот вопрос.

Формат ввода
    В первой и единственной строке входного файла записано два целых числа — N и k .

Формат вывода
    Выведите одно число — количество способов, которыми кузнечик может допрыгать из первой клетки до последней.
 */

import java.io.*;

public class GrassHopper {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int k = Integer.parseInt(line[1]);
            int[] dp = new int[N + 1];
            dp[1] = 1;
            for (int i = 2; i <= N; i++) {
                for (int j = 1; j <= k && i - j >= 1; j++) {
                    dp[i] += dp[i - j];
                }
            }
            bw.write(String.valueOf(dp[N]));
        }
    }
}
