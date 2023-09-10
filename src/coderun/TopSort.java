/*
10. Топологическая сортировка
Дан ориентированный граф. Необходимо построить топологическую сортировку.

Формат ввода
В первой строке входного файла два натуральных числа N и M,
(1≤N,M≤100000) — количество вершин и рёбер в графе соответственно. Далее в M строках перечислены рёбра графа.
Каждое ребро задаётся парой чисел — номерами начальной и конечной вершин соответственно.

Формат вывода
Выведите любую топологическую сортировку графа в виде последовательности номеров вершин (перестановка чисел от 1 до N).
Если топологическую сортировку графа построить невозможно, выведите -1.
*/

package coderun;

import java.io.*;
import java.util.*;

public class TopSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 1; i <= M; i++) {
            line = reader.readLine().split(" ");
            int fromNode = Integer.parseInt(line[0]);
            int toNode = Integer.parseInt(line[1]);
            adjList.compute(fromNode, (k, v) -> {
                if (v == null) {
                    List<Integer> adjNodes = new ArrayList<>();
                    adjNodes.add(toNode);
                    return adjNodes;
                } else {
                    v.add(toNode);
                    return v;
                }
            });
        }
        Deque<Integer> allNodes = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            allNodes.push(i);
        }
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> topSort = new Stack<>();
        int ansCode = 0;
        while(!allNodes.isEmpty()) {
            int node = allNodes.pop();
            if (visited.contains(node)) {
                continue;
            }
            ansCode = addChildsToStack(node, topSort, visited, new HashSet<>(), adjList);
            if (ansCode == -1) {
                break;
            }
        }
        if (ansCode == -1) {
            writer.write(ansCode + " ");
        } else {
            while (!topSort.isEmpty()) {
                writer.write(topSort.pop() + " ");
            }
        }
        reader.close();
        writer.close();
    }

    private static int addChildsToStack(int node,
                                        Stack<Integer> topSort,
                                        Set<Integer> visited,
                                        Set<Integer> visitedThisThread,
                                        Map<Integer, List<Integer>> adjList) {
        System.out.println("look in node: " + node);
        if (visitedThisThread.contains(node)) {
            System.out.println("node was visited: " + node);
            return -1;
        }
        if (visited.contains(node)) {
            return 1;
        }
        visitedThisThread.add(node);
        visited.add(node);
        List<Integer> childNodes = adjList.get(node);
        int ansCode = 1;
        if (childNodes != null && childNodes.size() == 1) {
            ansCode = addChildsToStack(childNodes.get(0), topSort, visited, visitedThisThread, adjList);
        } else if (childNodes != null && childNodes.size() > 1) {
            for (int childNode : childNodes) {
                Set<Integer> visitedNewThread = new HashSet<>(visitedThisThread);
                ansCode = addChildsToStack(childNode, topSort, visited, visitedNewThread, adjList);
                if (ansCode == -1) {
                    return ansCode;
                }
            }
        }
        topSort.push(node);
        return ansCode;
    }
}
