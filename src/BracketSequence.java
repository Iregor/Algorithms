//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B. Задача 12.

/*Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок.
Программа должна определить, является ли данная скобочная последовательность правильной.
Пустая последовательность является правильной.
Если A – правильная, то последовательности (A), [A], {A} – правильные.
Если A и B – правильные последовательности, то последовательность AB – правильная.*/

import java.io.*;
import java.util.Stack;

public class BracketSequence {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            Stack<Character> stack = new Stack<>();
            String line = br.readLine();
            if (line == null) {
                bw.write("yes");
                return;
            }
            String ans = "yes";
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch == '(' || ch == '[' || ch == '{') {
                    stack.push(ch);
                } else {
                    if (stack.empty()) {
                        ans = "no";
                        break;
                    }
                    if (ch == ')' && stack.pop() != '(') {
                        ans = "no";
                        break;
                    } else if (ch == ']' && stack.pop() != '[') {
                        ans = "no";
                        break;
                    } else if (ch == '}' && stack.pop() != '{') {
                        ans = "no";
                        break;
                    }
                }
            }
            if (!stack.empty()) {
                ans = "no";
            }
            bw.write(ans);
        }
    }
}
