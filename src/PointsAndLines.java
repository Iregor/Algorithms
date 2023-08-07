import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class PointsAndLines {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            String[] line = br.readLine().split(" ");
            int linesSize = Integer.parseInt(line[0]);
            int pointSize = Integer.parseInt(line[1]);
            Event[] events = new Event[linesSize * 2 + pointSize];
            for (int i = 0; i < linesSize; i++) {
                line = br.readLine().split(" ");
                int int1 = Integer.parseInt(line[0]);
                int int2 = Integer.parseInt(line[1]);
                events[i] = new Event(Integer.min(int1, int2), 1);
                events[linesSize + i] = new Event(Integer.max(int1, int2), -1);
            }
            line = br.readLine().split(" ");
            Event[] points = new Event[pointSize];
            for (int i = 0; i < pointSize; i++) {
                points[i] = new Event(Integer.parseInt(line[i]), 0);
                events[2 * linesSize + i] = new Event(Integer.parseInt(line[i]), 0);
            }
            Arrays.sort(events);
            int activeLines = 0;
            HashMap<Integer, Integer> countAtPoint = new HashMap<>();
            for (int i = 0; i < events.length; i++) {
                Event event = events[i];
                activeLines += event.value;
                if (event.value == 0) {
                    countAtPoint.put(event.x, activeLines);
                }
            }
            for (int i = 0; i < pointSize; i++) {
                bw.write(String.valueOf(countAtPoint.get(points[i].x)) + " ");
            }
        }
    }

    static class Event implements Comparable<Event> {
        int x;
        int value;

        public Event(int x, int value) {
            this.x = x;
            this.value = value;
        }

        public String toString() {
            return "{" + x + ", " + value + "}";
        }

        @Override
        public int compareTo(Event o) {
            Event event = (Event) o;
            if (this.x < event.x) {
                return -1;
            } else if (this.x > event.x) {
                return 1;
            } else {
                if (this.value > event.value) {
                    return -1;
                } else if (this.value == event.value) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    }
}
