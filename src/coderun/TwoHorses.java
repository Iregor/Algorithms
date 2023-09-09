//CodeRun. 43. TwoHorses

/*
На стандартной шахматной доске (8х8) живут 2 шахматных коня: Красный и Зелёный.
Обычно они беззаботно скачут по просторам доски, пощипывая шахматную травку, но сегодня особенный день:
у Зелёного коня День Рождения. Зеленый конь решил отпраздновать это событие вместе с Красным.
Но для осуществления этого прекрасного плана им нужно оказаться на одной клетке. Заметим, что Красный и Зелёный шахматные
кони сильно отличаются от черного с белым: они ходят не по очереди, а одновременно, и если оказываются на одной клетке,
никто никого не съедает. Сколько ходов им потребуется, чтобы насладиться праздником?

Формат ввода
На вход программы поступают координаты коней, записанные по стандартным шахматным правилам
(т.е. двумя символами - маленькая латинская буква (от a до h) и цифра (от 1 до 8), задающие столбец и строку соответственно).

Формат вывода
Требуется вывести наименьшее необходимое количество ходов, либо число -1, если кони не могут встретиться.*/

package coderun;

import java.io.*;
import java.util.*;

public class TwoHorses {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = reader.readLine().split(" ");
        String firstPos = line[0];
        String secondPos = line[1];
        Set<String> visited = new HashSet<>();
        List<Integer[]> waysToStart = new ArrayList<>();
        waysToStart.add(getPosition(firstPos));
        Integer[] cellToAchieve = getPosition(secondPos);
        int stepsToAchieve = getOneWaySteps(visited, 0, waysToStart, cellToAchieve);
        if (stepsToAchieve == 0 || stepsToAchieve % 2 != 0) {
            stepsToAchieve = -1;
        } else {
            stepsToAchieve /= 2;
        }
        writer.write(String.valueOf(stepsToAchieve));
        reader.close();
        writer.close();
    }

    private static int getOneWaySteps(
            Set<String> visited,
            int stepNumber,
            List<Integer[]> waysToGo,
            Integer[] cellToAchieve) {
        List<Integer[]> childWays = new ArrayList<>();
        for (Integer[] pos : waysToGo) {
            String posToVisit = String.valueOf(pos[0]) + String.valueOf(pos[1]);
            if (visited.contains(posToVisit)) {
                continue;
            }
            visited.add(posToVisit);
            if (pos[0].equals(cellToAchieve[0]) && pos[1].equals(cellToAchieve[1])) {
                return stepNumber;
            }
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (Math.abs(i) != Math.abs(j)
                            && pos[0] + i >= 1
                            && pos[0] + i <= 8
                            && pos[1] + j >= 1
                            && pos[1] + j <= 8
                            && i != 0
                            && j != 0) {
                        childWays.add(new Integer[]{pos[0] + i, pos[1] + j});
                    }
                }
            }
        }
        return getOneWaySteps(visited, stepNumber + 1, childWays, cellToAchieve);
    }

    private static Integer[] getPosition(String s) {
        return new Integer[]{s.charAt(0) - 'a' + 1, s.charAt(1) - '0'};
    }
}
