import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int test = 1; test <= T; test++) {
			
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			int[][] map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) map[i][j] = sc.nextInt();
			}
			
			int count = 0;
			int num;
			
			for (int i = 0; i < N; i++) {
				num = 0;
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) num++;
					if (map[i][j] == 0 || j == (N-1)) {
						if (num == K) count++;
						if (map[i][j] == 0) num = 0;
					}
				}		
			}
			
			for (int i = 0; i < N; i++) {
				num = 0;
				for (int j = 0; j < N; j++) {
					if (map[j][i] == 1) num++;
					if (map[j][i] == 0 || j == (N-1)) {
						if (num == K) count++;
						if (map[j][i] == 0) num = 0;
					}
				}		
			}
			
			System.out.println("#" + test + " " + count);
		}
	}

}