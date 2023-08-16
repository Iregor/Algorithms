//Тренировки по алгоритмам 3.0 от Яндекса — Дивизион B
//Задача 33. Списывание

/*Задача:
    Во время контрольной работы профессор Флойд заметил, что некоторые студенты обмениваются записками.
    Сначала он хотел поставить им всем двойки, но в тот день профессор был добрым, а потому решил разделить студентов на две группы: списывающих и дающих списывать, и поставить двойки только первым.
    У профессора записаны все пары студентов, обменявшихся записками. Требуется определить, сможет ли он разделить студентов на две группы так, чтобы любой обмен записками осуществлялся от студента одной группы студенту другой группы.

Формат ввода
    В первой строке находятся два числа N и M — количество студентов и количество пар студентов, обменивающихся записками (1 ≤ N ≤ 102, 0 ≤ M ≤ N(N−1)/2).
    Далее в M строках расположены описания пар студентов: два числа, соответствующие номерам студентов, обменивающихся записками (нумерация студентов идёт с 1).
    Каждая пара студентов перечислена не более одного раза.

Формат вывода
    Необходимо вывести ответ на задачу профессора Флойда. Если возможно разделить студентов на две группы - выведите YES; иначе выведите NO.*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cheating {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] line = br.readLine().split(" ");
            int students = Integer.parseInt(line[0]);
            int edges = Integer.parseInt(line[1]);
            Graph graph = new Graph(students);
            for (int i = 1; i <= edges; i++) {
                line = br.readLine().split(" ");
                graph.edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            }
            bw.write(graph.divideInTwoGroups());
        }
    }

    private static class Graph {
        private List<Integer> vertices;
        private int[][] adjMat;
        private int size;

        Graph(int size) {
            vertices = new ArrayList<>();
            for (int i = 1; i <= size; i++) {
                vertices.add(i);
            }
            adjMat = new int[size + 1][size + 1];
            this.size = size;
        }

        public void edge(int vert1, int vert2) {
            adjMat[vert1][vert2] = 1;
            adjMat[vert2][vert1] = 1;
        }

        public String divideInTwoGroups() {
            String[] ans = new String[]{"YES"};
            Map<Integer, Integer> groups = new HashMap<>();
            while (!vertices.isEmpty()) {
                int vertex = vertices.get(vertices.size() - 1);
                divideIntoGroupsRec(List.of(vertex), 0, groups, ans);
            }
            return ans[0];
        }

        private void divideIntoGroupsRec(List<Integer> currentGenVertices,
                                         int generation,
                                         Map<Integer, Integer> groups,
                                         String[] ans) {
            //for every vertex:
            // add to group
            //remove from all vertices collection
            //find realated vertices and put them into another group
            List<Integer> nextGenVertices = new ArrayList<>();
            for (Integer vertex : currentGenVertices) {
                groups.put(vertex, generation % 2);
                vertices.remove(vertex);                                            //O(n)
                nextGenVertices.addAll(findNotVisitedChilds(vertex, groups, generation, ans));
            }
            if (!ans[0].equals("NO") && !nextGenVertices.isEmpty()) {
                divideIntoGroupsRec(nextGenVertices, ++generation, groups, ans);
            }
        }

        private List<Integer> findNotVisitedChilds(int vertex, Map<Integer, Integer> groups, int generation, String[] ans) {
            List<Integer> childsOfVertex = new ArrayList<>();
            for (int k = 1; k < adjMat.length; k++) {
                if (adjMat[vertex][k] == 1) {
                    if (!groups.containsKey(k)) {
                        childsOfVertex.add(k);
                    } else if (groups.get(k) == generation % 2) {
                        ans[0] = "NO";                            //if child has same generation as parent
                    }
                }
            }
            return childsOfVertex;
        }
    }
}
