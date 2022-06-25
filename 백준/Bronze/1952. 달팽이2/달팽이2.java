import java.util.Scanner;

public class Main {

	static int r, c, curve = 0;
	static boolean[][] visit;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		r = input.nextInt();
		c = input.nextInt();

		visit = new boolean[r + 1][c + 1];

		simulation();
	}

	private static void simulation() {
		int x = 1, y = 1, d = 0;
		int nx = 1, ny = 1;
		while (true) {
			visit[x][y] = true;

			nx = x + dx[d];
			ny = y + dy[d];

			if (nx >= 1 && ny >= 1 && nx <= r && ny <= c && !visit[nx][ny]) {
				x = nx;
				y = ny;
			} else {
				d = (d + 1) % 4;
				x += dx[d];
				y += dy[d];
				if (visit[x][y]) {
					break;
				}
				curve++;
			}
//			print();
		}
		System.out.println(curve);
	}

	private static void print() {
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				System.out.print(visit[i][j] ? 1 + " " : 0 + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}