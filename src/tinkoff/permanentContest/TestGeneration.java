/*
Во время разработки некоторой задачи Саша решил сгенерировать несколько новых тестов. 
Каждый тест Саши должен представлять собой натуральное число, не меньшее l и не большее r.
Кроме того, натуральное число в тесте обязательно должно состоять из одинаковых цифр.

Например, число 999 подходит под это требование, а число 123 — нет.
Какое максимальное число различных тестов сможет создать Саша?

Формат входных данных

В единственной строке вводятся два натуральных числа (1≤l,r≤10^18)— ограничения на тесты Саши.

Формат выходных данных

Выведите одно число — количество тестов, которое может сделать Саша.
*/

package tinkoff.permanentContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestGeneration {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = br.readLine().split(" ");
            String l = line[0];
            String r = line[1];
            String curString = "1";
            while (curString.length() < l.length()) {
                curString += curString.charAt(0);
            }
            System.out.println(getTestRec(curString, l, r, 0));
        }
    }

    private static int getTestRec(String curString, String l, String r, int res) {
        boolean numberFormatExc = curString.charAt(0) == '9' && curString.length() == 19;
        if (numberFormatExc || curString.length() > r.length() || (curString.length() == r.length() && Long.parseLong(curString) > Long.parseLong(r))) {
            if (curString.charAt(0) == '9') {
                return res;
            } else {
                curString = String.valueOf((char) (curString.charAt(0) + 1));
                while (curString.length() < l.length()) {
                    curString += curString.charAt(0);
                }
                return getTestRec(curString, l, r, res);
            }
        }
        if (curString.length() == l.length() && Long.parseLong(curString) < Long.parseLong(l)) {
            curString += curString.charAt(0);
            return getTestRec(curString, l, r, res);
        }

        res++;
        curString += curString.charAt(0);
        return getTestRec(curString, l, r, res);
    }
}