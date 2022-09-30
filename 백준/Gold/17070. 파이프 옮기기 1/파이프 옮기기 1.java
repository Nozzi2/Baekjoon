import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = {0,1,1};
	static int[] dc = {1,1,0};
	static int[][] map;
	static int N;
	static int cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		cnt=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		
		dfs(1,2,0);
		
		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int k) {
		if(r==N && c==N) {
			cnt++;
			return;
		}
		
		
		/*0 > 01
		1 > 012
		2 > 12*/
		
		for(int i=0; i<3; i++) {
			if(Math.abs(k-i)<2) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(nr>N || nc>N) continue; //파이프가 벗어날경우
				if(i!=1) {
					if(map[nr][nc]==1) continue; //벽이 있을 경우
				} else {
					if(map[nr][nc]==1 
					 ||map[nr-1][nc]==1
					 ||map[nr][nc-1]==1) continue;
				}
								
				dfs(nr, nc, i);
			}
		}
		
	}
}