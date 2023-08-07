import java.io.*;
import java.util.HashSet;

public class ManhattenSet {
    public static void main(String[] args) throws IOException {
        HashSet<Point> pos = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String firstLine = br.readLine();
            short t = Byte.parseByte(firstLine.split(" ")[0]);
            short d = Byte.parseByte(firstLine.split(" ")[1]);
            short n = Byte.parseByte(firstLine.split(" ")[2]);

            short x = 0;
            short y = 0;
            short dist = (short) (t * n);
            for (short x1 = (short) (x - dist); x1 <= x + dist; x1++) {
                int lackOfDist = dist - Math.abs(x - x1);
                for (short y1 = (short) (y - lackOfDist); y1 <= y + lackOfDist; y1++) {
                    pos.add(new Point(x1, y1));
                }
            }
            HashSet<Point> localPos = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                localPos.clear();
                String line = br.readLine();
                x = Byte.parseByte(line.split(" ")[0]);
                y = Byte.parseByte(line.split(" ")[1]);
                dist = (short) (d + t * (n - i));
                for (short x1 = (short) (x - dist); x1 <= x + dist; x1++) {
                    int lackOfDist = dist - Math.abs(x - x1);
                    for (short y1 = (short) (y - lackOfDist); y1 <= y + lackOfDist; y1++) {
                        localPos.add(new Point(x1, y1));
                    }
                }
                pos.retainAll(localPos);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(String.valueOf(pos.size()));
            bw.newLine();
            for (Point p : pos) {
                bw.write(p.toString());
                bw.newLine();
            }
        }
    }

    private static class Point {
        short x;
        short y;

        public Point(short x, short y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Object ob) {
            if (ob == this) {
                return true;
            }
            if (!(ob instanceof Point)) {
                return false;
            }
            Point p = (Point) ob;
            return x == p.x && y == p.y;
        }

        public int hashCode() {
            return x * 31 + y;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            return sb.append(x).append(" ").append(y).toString();
        }
    }
}