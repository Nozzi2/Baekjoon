import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		final int INF = Integer.MAX_VALUE>>3;
		
		int edgeCnt = Integer.parseInt(br.readLine()); 
		for(int i=0; i<edgeCnt; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int val = Integer.parseInt(st.nextToken());
			
			map[r][c] = map[r][c]!=0?Math.min(map[r][c], val):val;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i!=j && map[i][j] == 0) {
					map[i][j] = INF;
				}
			}
		}
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
//					if(map[i][k]+map[k][j] < map[i][j]) {
//						map[i][j] = map[i][k]+map[k][j];
//					}
					map[i][j] = Math.min(map[i][k]+map[k][j], map[i][j]);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]==INF?"0 ":map[i][j]+" ");
			}
			System.out.println();
		}
	}
}