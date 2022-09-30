import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	
	static int[] dr = {0,1,-1,0};
	static int[] dc = {1,0,0,-1};
	static int N;
	static int[][] map;
	static int[][] costs;
	
	static class Position{
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		boolean isOut() {
			if(r>N || c>N || r<1 || c<1) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/1249swea.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int T=1; T<=TC; T++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][N+2];
			costs = new int[N+2][N+2];
			for(int i=1; i<=N; i++) {
				String s =br.readLine();
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(s.substring(j-1, j));
				}
			}
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					costs[i][j] = Integer.MAX_VALUE;
				}
			}
			
			Position start = new Position(1,1);
			bfs(start);
			
			System.out.printf("#%d %d\n",T,costs[N][N]);
		}//for T
	}//main end

	private static void bfs(Position start) {
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		costs[start.r][start.c] = 0;
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				
				if(next.isOut()) continue;
				
				int nvalue=map[next.r][next.c]+costs[cur.r][cur.c];
				if(nvalue>=costs[next.r][next.c]) continue; //탐색한 곳의 비용이 이전에 탐색했던 비용보다 더 크면 탐색 종료
				
				costs[next.r][next.c] = nvalue;
				que.offer(next);
			}
		}
		
	}
}