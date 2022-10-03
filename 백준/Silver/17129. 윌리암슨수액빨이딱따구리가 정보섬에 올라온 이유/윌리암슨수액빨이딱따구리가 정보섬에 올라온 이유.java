import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	static int R,C,cnt;
	static boolean isArrived;
	
	static class Position{
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		boolean isOut() {
			if(r>R || c>C || r<1 || c<1) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		cnt = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R+2][C+2];
		visited = new boolean[R+2][C+2];
		
		Position start=null;
		for(int i=1; i<=R; i++) {
			String s = br.readLine();
			for(int j=1; j<=C; j++) {
				map[i][j] = Integer.parseInt(s.substring(j-1, j));
				if(map[i][j] == 2 ) {
					start = new Position(i,j);
				}
			}
		}
		
		bfs(start);
		
		if(isArrived) {
			System.out.println("TAK");
			System.out.println(cnt);
		} else {
			System.out.println("NIE");
		}
		
	}

	private static void bfs(Position start) {
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		visited[start.r][start.c] = true;
		
		while(!que.isEmpty()) {
			cnt++;
			for(int s=0, ends=que.size(); s<ends; s++) {
				Position cur = que.poll();
				for(int i=0; i<4; i++) {
					Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
					if(next.isOut() || visited[next.r][next.c] || map[next.r][next.c] == 1) 
						continue;
					if(map[next.r][next.c] != 0) {
						isArrived = true;
						return;
					}
					que.offer(next);
					visited[next.r][next.c] = true;
				}
			}
		}
		
	}
}