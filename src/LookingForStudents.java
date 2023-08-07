import java.io.*;
import java.util.Arrays;

public class LookingForStudents {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] lineArr = br.readLine().split(" ");
            int studentSize = Integer.parseInt(lineArr[0]);
            int teacherSize = Integer.parseInt(lineArr[1]);
            int freeStudents = 0;
            int lastEnd = -1;
            String line;
            View[] views = new View[teacherSize];
            for (int i = 0; i < teacherSize; i++) {
                lineArr = br.readLine().split(" ");
                views[i] = new View(Integer.parseInt(lineArr[0]), Integer.parseInt(lineArr[1]));
            }
            Arrays.sort(views);
            for (int i = 0; i < teacherSize; i++) {
                int start = views[i].from;
                int end = views[i].to;
                if (start > lastEnd) {
                    freeStudents += start - lastEnd - 1;
                }
                if (end > lastEnd) {
                    lastEnd = end;
                }
            }
            freeStudents += studentSize - 1 - lastEnd;
            bw.write(String.valueOf(freeStudents));
        }
    }

    static class View implements Comparable<View> {
        int from;
        int to;

        public View(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(View o) {
            View view = (View) o;
            if (this.from < view.from) {
                return -1;
            } else if (this.from > view.from) {
                return 1;
            } else if (this.to > view.to) {
                return 1;
            } else if (this.to < view.to) {
                return -1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "{" + from + ", " + to + "}";
        }
    }
}