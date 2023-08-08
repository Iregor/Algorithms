//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B
//Задача 31. Поиск в глубину

/*Дан неориентированный граф, возможно, с петлями и кратными ребрами.
Необходимо построить компоненту связности, содержащую первую вершину.

Формат ввода
    В первой строке записаны два целых числа N (1 ≤ N ≤ 103) и M (0 ≤ M ≤ 5 * 105) — количество вершин и ребер в графе.
    В последующих M строках перечислены ребра — пары чисел, определяющие номера вершин, которые соединяют ребра.

Формат вывода
    В первую строку выходного файла выведите число K — количество вершин в компоненте связности.
    Во вторую строку выведите K целых чисел — вершины компоненты связности, перечисленные в порядке возрастания номеров.*/

import java.io.*;
import java.util.*;

public class DeepFirstSearch {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] firstLine = br.readLine().split(" ");
            int verticesSize = Integer.parseInt(firstLine[0]);
            int edgesSize = Integer.parseInt(firstLine[1]);
            String line;
            Graph graph = new Graph(verticesSize);
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                int[] edgeVertices = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                graph.newEdge(edgeVertices[0], edgeVertices[1]);
            }
            int root = 1;   //read task description
            Queue<Integer> ans = graph.depthSearch(root);
            Integer n;
            bw.write(String.valueOf(ans.size()));
            bw.newLine();
            while ((n = ans.poll()) != null) {
                bw.write(n + " ");
            }
        }
    }

    private static class Graph {
        private int[][] adjMat;
        private int verticesSize;

        public Graph(int verticesSize) {
            this.verticesSize = verticesSize + 1;
            adjMat = new int[verticesSize + 1][verticesSize + 1];
        }

        public void newEdge(int id1, int id2) {
            adjMat[id1][id2] = 1;
            adjMat[id2][id1] = 1;
        }

        public Queue<Integer> depthSearch(int id) {
            Stack<Integer> stack = new Stack<>();
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            Set<Integer> visited = new HashSet<>();
            stack.push(id);
            heap.add(id);
            visited.add(id);
            while (!stack.empty()) {
                int vertId = stack.pop();
                for (int k = 1; k < verticesSize; k++) {
                    if (adjMat[vertId][k] == 1 && !visited.contains(k)) {
                        stack.push(k);
                        visited.add(k);
                        heap.add(k);
                    }
                }
            }
            return heap;
        }
    }
}