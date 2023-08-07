/*Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B.
Задача 13 - Постфиксная запись*/

/*В постфиксной записи (или обратной польской записи) операция записывается после двух операндов.
Например, сумма двух чисел A и B записывается как A B +.
Запись B C + D * обозначает привычное нам (B + C) * D, а запись A B C + D * + означает A + (B + C) * D.
Достоинство постфиксной записи в том, что она не требует скобок и дополнительных соглашений о приоритете операторов для своего чтения.*/

/*Формат ввода
    В единственной строке записано выражение в постфиксной записи, содержащее цифры и операции +, -, *.
    Цифры и операции разделяются пробелами. В конце строки может быть произвольное количество пробелов.

Формат вывода
    Необходимо вывести значение записанного выражения.*/

import java.io.*;
import java.util.Stack;

public class PostfixExpression {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] exp = br.readLine().trim().split(" ");
            Stack<String> ans = new Stack<>();
            for (int i = 0; i < exp.length; i++) {
                String symb = exp[i];
                switch (symb){
                    case "+":
                        int op1 = Integer.parseInt(ans.pop());
                        int op2 = Integer.parseInt(ans.pop());
                        ans.push(String.valueOf(op2+op1));
                        break;
                    case "-":
                        op1 = Integer.parseInt(ans.pop());
                        op2 = Integer.parseInt(ans.pop());
                        ans.push(String.valueOf(op2-op1));
                        break;
                    case "*":
                        op1 = Integer.parseInt(ans.pop());
                        op2 = Integer.parseInt(ans.pop());
                        ans.push(String.valueOf(op2*op1));
                        break;
                    default:
                        ans.push(symb);
                }
            }
            bw.write(ans.pop());
        }
    }
}
