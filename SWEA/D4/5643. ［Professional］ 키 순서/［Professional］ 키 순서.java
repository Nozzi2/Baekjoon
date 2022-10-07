import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		final int INF = Integer.MAX_VALUE>>3;
		
		//System.setIn(new FileInputStream("res/5643swea.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=1; T<=TC; T++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			int[][] map1 = new int[N][N];
			int[][] map2 = new int[N][N];
			
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken())-1;
				map1[r][c]++;
				map2[c][r]--;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i!=j && map1[i][j]==0) {
						map1[i][j] = INF;
					}

					if(i!=j && map2[j][i]==0) {
						map2[j][i] = INF;
					}
				}//for j
			}//for i
			
			for(int k=0; k<N; k++) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						map1[i][j] = Math.min(map1[i][j], map1[i][k]+map1[k][j]);
						map2[i][j] = Math.min(map2[i][j], map2[i][k]+map2[k][j]);
					}
				}
			}
			
			int cnt=N;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map1[i][j]==INF) {
						map1[i][j] = map2[i][j];
					}
				}//for j
			}//for i

			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map1[i][j]>=INF>>1) {
						cnt--;
						break;
					}
				}//for j
			}//for i
			
			sb.append("#").append(T).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}