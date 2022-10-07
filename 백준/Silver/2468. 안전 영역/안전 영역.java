import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	static int[][] map;
	static boolean[][] visited;
	static int N;
	
	static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		boolean isOut() {
			if(r > N || c>N || r<1 || c<1) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}//for j
		}//for i
		
		int maxArea=Integer.MIN_VALUE;
		for(int height=min; height<max; height++) {
			visited = new boolean[N+2][N+2];
			int cnt=0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(!visited[i][j]) {
						if(bfs(new Position(i,j),height)) {
							cnt++;
						}//if
					}//if
				}//for j
			}//for i
			maxArea = Math.max(maxArea, cnt);
		}//for height
		
		System.out.println(maxArea==Integer.MIN_VALUE?1:maxArea);
	}

	private static boolean bfs(Position start, int height) {
		if(visited[start.r][start.c] || map[start.r][start.c]<=height) 
			return false;
		
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		visited[start.r][start.c] = true;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut()) continue;
				if(visited[next.r][next.c]) continue;
				if(map[next.r][next.c]<=height) continue;
				visited[next.r][next.c] = true;
				que.offer(next);
			}
		}
		
		return true;
	}
}