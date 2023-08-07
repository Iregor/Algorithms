import java.io.*;

public class Miner {
	public static void main (String args[]) throws IOException {
		int[][] field;
		int minesCount;
		try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
			String line = br.readLine();
			String[] fieldSize = line.split(" ");
			field = new int[Integer.parseInt(fieldSize[0])][Integer.parseInt(fieldSize[1])];
			minesCount = Integer.parseInt(fieldSize[2]);

			String mine = br.readLine();
			while(mine != null) {
				field[Integer.parseInt(mine.split(" ")[0])-1][Integer.parseInt(mine.split(" ")[1])-1] = -1;
				mine = br.readLine();
			}
		}
		fillTheField(field);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			bw.write(getStringField(field));
		}
	}

	private static void fillTheField(int[][] field) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == -1) {
					processMine(field, i, j);
				}
			}
		}
	}

	private static void processMine(int[][] field, int i, int j) {
		for (int k = i - 1; k <= i + 1; k++) {
			for (int l = j - 1; l <= j + 1; l++) {
				if (!(k < 0 || l < 0 || k > field.length - 1 || l > field[k].length - 1 || (k == i && l == j) || field[k][l] == -1)) {
					field[k][l] = field[k][l] + 1;
				}
			}
		}
	}

	private static String getStringField(int[][] field) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == -1) {
					sb.append("*").append(" ");
					continue;
				}
				sb.append(field[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}