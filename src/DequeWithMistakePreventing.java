//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B
//Задача 18. Дек с защитой от ошибок

/*Научитесь пользоваться стандартной структурой данных deque для целых чисел.
Напишите программу, содержащую описание дека и моделирующую работу дека, реализовав все указанные здесь методы.
Программа считывает последовательность команд и в зависимости от команды выполняет ту или иную операцию.
После выполнения каждой команды программа должна вывести одну строчку.

Возможные команды для программы:

push_front n
    Добавить (положить) в начало дека новый элемент. Программа должна вывести ok.
push_back n
    Добавить (положить) в конец дека новый элемент. Программа должна вывести ok.
pop_front
    Извлечь из дека первый элемент. Программа должна вывести его значение.
pop_back
    Извлечь из дека последний элемент. Программа должна вывести его значение.
front
    Узнать значение первого элемента (не удаляя его). Программа должна вывести его значение.
back
    Узнать значение последнего элемента (не удаляя его). Программа должна вывести его значение.
size
    Вывести количество элементов в деке.
clear
    Очистить дек (удалить из него все элементы) и вывести ok.
exit
    Программа должна вывести bye и завершить работу.
Гарантируется, что количество элементов в деке в любой момент не превосходит 100.
Перед исполнением операций pop_front, pop_back, front, back программа должна проверять, содержится ли в деке хотя бы один элемент.
Если во входных данных встречается операция pop_front, pop_back, front, back, и при этом дек пуст, то программа должна вместо числового значения вывести строку error.*/

import java.io.*;

public class DequeWithMistakePreventing {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            Deque deq = new Deque(100);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("push")) {
                    int n = Integer.parseInt(line.split(" ")[1]);
                    if (line.contains("front")) {
                        bw.write(deq.pushFront(n));
                        bw.newLine();
                    } else {
                        bw.write(deq.pushBack(n));
                        bw.newLine();
                    }
                }
                if (line.contains("exit")) {
                    bw.write("bye");
                    break;
                }
                switch (line) {
                    case "pop_front" -> {
                        bw.write(deq.popFront());
                        bw.newLine();
                    }
                    case "pop_back" -> {
                        bw.write(deq.popBack());
                        bw.newLine();
                    }
                    case "front" -> {
                        bw.write(deq.front());
                        bw.newLine();
                    }
                    case "back" -> {
                        bw.write(deq.back());
                        bw.newLine();
                    }
                    case "size" -> {
                        bw.write(deq.size());
                        bw.newLine();
                    }
                    case "clear" -> {
                        bw.write(deq.clear());
                        bw.newLine();
                    }
                }
            }
        }
    }

    private static class Deque {
        private int[] data;
        private int head;
        private int tail;
        private final int initialSize;
        private int size;

        public Deque(int initialSize) {
            this.initialSize = initialSize;
            data = new int[initialSize];    //we have guarantees of max size
            head = tail = initialSize / 2;
            size = 0;
        }

        public String pushFront(int n) {
            if (size == initialSize) {
                return "error";
            }
            if (size != 0) {
                head--;
                if (head == -1) {
                    head = initialSize + head;
                }
            }
            data[head] = n;
            size++;
            return "ok";
        }

        public String pushBack(int n) {
            if (size == initialSize) {
                return "error";
            }
            if (size != 0) {
                tail = (tail + 1) % initialSize;
            }
            data[tail] = n;
            size++;
            return "ok";
        }

        public String popFront() {
            if (size == 0) {
                return "error";
            }
            size--;
            if (head == tail) {
                return String.valueOf(data[head]);
            } else {
                String ans = String.valueOf(data[head++]);
                head = head % 100;
                return ans;
            }
        }

        public String popBack() {
            if (size == 0) {
                return "error";
            }
            size--;
            if (head == tail) {
                return String.valueOf(data[tail]);
            } else {
                String ans = String.valueOf(data[tail--]);
                if (tail < 0) {
                    tail = initialSize + tail;
                }
                return ans;
            }
        }

        public String front() {
            if (size == 0) {
                return "error";
            } else {
                return String.valueOf(data[head]);
            }
        }

        public String back() {
            if (size == 0) {
                return "error";
            } else {
                return String.valueOf(data[tail]);
            }
        }

        public String size() {
            return String.valueOf(size);
        }

        public String clear() {
            data = new int[initialSize];    //we have guarantees of max size
            head = tail = initialSize / 2;
            size = 0;
            return "ok";
        }


    }
}
