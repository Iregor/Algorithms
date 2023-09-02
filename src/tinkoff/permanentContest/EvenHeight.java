/*
На физкультуре происходит разбиение по двум командам. Ребята выстроены в шеренгу, у каждого из них есть свой рост ai.
Разбиение по командам произойдет по принципу «четный-нечетный» — все школьники с четным ростом отправляются в одну команду,
а нечетные — в другую.

В отличие от привычного урока, ребята не выстроились по росту.
Вместо привычного порядка они встали случайно.
Теперь физрук Яша смотрит на шеренгу и думает — может ли ровно одна пара учеников поменяться местами так,
чтобы команды оказались такими же, как и по принципу «первый-второй».
Иначе говоря, он хочет получить такой порядок, при котором все ученики с четным ростом стоят на четных позициях,
а с нечетным — на нечетных.

Помогите Яше найти нужную замену.

Формат входных данных

В первой строке находится число n(2≤n≤1000) — количество учеников в шеренге.

В следующей строке находится n натуральных чисел  — рост учеников.

Формат выходных данных

В единственной строке выведите
i и j — номера элементов, которые нужно поменять местами, чтобы добиться заданного условия.
Если ответов несколько — разрешается вывести любой.

Если не существует способа поменять два элемента местами — выведите -1 -1.
 */

package tinkoff.permanentContest;

import java.io.*;
import java.util.Arrays;

public class EvenHeight {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int n = Integer.parseInt(br.readLine());
			int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int evenToReplace = -1;
			int oddToReplace = -1;
			boolean tooMuchReplaces = false;
			for (int i = 0; i < numbers.length; i++) {
				boolean indexIsEven = (i + 1) % 2 == 0;
				if (indexIsEven) {
					if (numbers[i] % 2 != 0) {
						if (oddToReplace != -1) {
							tooMuchReplaces = true;
							break;
						} else {
							oddToReplace = i + 1;
						}
					}
				} else {
					if (numbers[i] % 2 == 0) {
						if (evenToReplace != -1) {
							tooMuchReplaces = true;
							break;
						} else {
							evenToReplace = i + 1;
						}
					}
				}
			}
			if (tooMuchReplaces || ((oddToReplace == -1) ^ (evenToReplace == -1))) {
				System.out.println(-1 + " " + -1);
			} else if (oddToReplace == -1 && evenToReplace == -1){
				if (numbers.length >= 3) {
					System.out.println(1 + " " + 3);
				} else {
					System.out.println(-1 + " " + -1);
				}
			} else {
				System.out.println(oddToReplace + " " + evenToReplace);
			}
		}
	}
}