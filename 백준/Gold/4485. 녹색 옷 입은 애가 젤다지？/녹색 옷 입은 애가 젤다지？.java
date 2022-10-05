import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static int[][] map;
	static int[][] values;
	
	static class Position {
		int r;
		int c;
		int val;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public Position(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
		
		boolean isOut() {
			if(r>N || c>N || r<1 || c<1) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC=0;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N==0) break;
			map = new int[N+2][N+2];
			values = new int[N+2][N+2];
			
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					values[i][j] = Integer.MAX_VALUE;
				}
			}
			
			//DFS
//			Position start = new Position(1,1);
//			values[1][1] = map[1][1];
//			dfs(start, values[1][1]);
			
			//BFS
			Position start = new Position(1,1,map[1][1]);
			bfs(start);
			
			sb.append("Problem ").append(++TC).append(": ").append(values[N][N]).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(Position start) {
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		values[start.r][start.c] = start.val;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i], cur.val);
				if(next.isOut()) continue;
				next.val = values[cur.r][cur.c]+map[next.r][next.c];
				if(values[next.r][next.c] <= next.val) continue;
				values[next.r][next.c] = next.val;
				que.offer(next);
			}
					
					
		}
	}

	private static void dfs(Position start, int val) {
		if(start.r == N && start.c == N) return;
		
		for(int i=0; i<4; i++) {
			Position next = new Position(start.r+dr[i], start.c+dc[i]);
			if(next.isOut()) continue;
			if( values[next.r][next.c] <= (val+map[next.r][next.c]) ) continue;
			values[next.r][next.c] = val+map[next.r][next.c];
			dfs(next, values[next.r][next.c]);
		}
	}
}