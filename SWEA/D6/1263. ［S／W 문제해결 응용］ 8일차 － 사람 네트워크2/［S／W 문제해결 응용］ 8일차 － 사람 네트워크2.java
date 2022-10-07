import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/1263swea.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=1; T<=TC; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int[][] map = new int[size][size];
			final int INF = Integer.MAX_VALUE>>3;
			
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					if(i!=j && map[i][j] == 0) {
						map[i][j] = INF;
					}
				}
			}
			
			for(int k=0; k<size; k++) {
				for(int i=0; i<size; i++) {
					for(int j=0; j<size; j++) {
						map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					}
				}
			}
			
			int[] ccs = new int[size];
			int min = Integer.MAX_VALUE;
			
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					ccs[i]+=map[i][j];
				}
				if(min > ccs[i]) {
					min = ccs[i];
				}
			}
			sb.append("#").append(T).append(" ").append(min).append("\n");
		}
		System.out.print(sb);
	}
}