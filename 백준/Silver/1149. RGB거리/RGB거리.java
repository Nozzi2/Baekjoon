import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] memo;		//DFS 탐색 한칸마다 최소값을 저장하기 위한 배열 
	static int sum;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		memo = new int[N][3];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		memo[0][0] = map[0][0];
		memo[0][1] = map[0][1];
		memo[0][2] = map[0][2];
		
		for(int i=1; i<N; i++) {
			memo[i][0] = Math.min(memo[i-1][1], memo[i-1][2])+map[i][0];
			memo[i][1] = Math.min(memo[i-1][2], memo[i-1][0])+map[i][1];
			memo[i][2] = Math.min(memo[i-1][0], memo[i-1][1])+map[i][2];
		}
		
		int min = Math.min(memo[N-1][0], memo[N-1][1]);
		min = Math.min(min, memo[N-1][2]);
		
		System.out.println(min);
	}
}