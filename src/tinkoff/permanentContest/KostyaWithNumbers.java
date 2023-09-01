/*
У Кости есть бумажка, на которой написано n чисел. Также у него есть возможность не больше,
чем k раз, взять любое число с бумажки, после чего закрасить одну из старых цифр,
а на ее месте написать новую произвольную цифру.
На какое максимальное значение Костя сможет увеличить сумму всех чисел на листочке?

Формат входных данных
В первой строке входного файла даны два целых числа n,k — количество чисел на бумажке и ограничение на
число операций.

Во второй строке записано n чисел.

Формат выходных данных

В выходной файл выведите одно число — максимальную разность между конечной и начальной суммой.
*/

package tinkoff.permanentContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KostyaWithNumbers {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int k = Integer.parseInt(line[1]);
            String[] numbers = br.readLine().split(" ");

            Comparator<String> comp = new Comparator<>() {
                @Override
                public int compare(String n1, String n2) {
                    //меньше то, что имеет больше разрядов
                    //если разрядов одинаково - меньше первое число
                    if (n1.length() != n2.length()) {
                        return n2.length() - n1.length();
                    } else {
                        return n1.charAt(0) - n2.charAt(0);
                    }

                }
            };

            PriorityQueue<String> pq = new PriorityQueue<>(comp);
            for (int i = 0; i < numbers.length; i++) {
                String number = removeLeadingNine(numbers[i]);
                if (!number.isEmpty()) {
                    pq.add(number);
                }
            }

            long sumDiff = 0;
            for (int i = 1; i <= k && !pq.isEmpty(); i++) {
                String number = pq.poll();
                sumDiff += (long) (9 - Character.getNumericValue(number.charAt(0))) * ((int) Math.pow(10, number.length() - 1));
                if (number.length() > 1) {
                    number = number.substring(1);
                } else {
                    number = "";
                }
                number = removeLeadingNine(number);
                if (!number.isEmpty()) {
                    pq.add(number);
                }
            }
            System.out.println(sumDiff);
        }
    }

    private static String removeLeadingNine(String number) {
        while (!number.isEmpty() && number.charAt(0) == '9') {
            if (number.length() == 1) {
                return "";
            }
            number = number.substring(1);
        }
        return number;
    }
}