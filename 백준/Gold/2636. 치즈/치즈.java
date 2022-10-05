import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R,C, minR, minC, maxR, maxC;
	static int[][] map;
	static boolean[][] visited;
	
	static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		boolean isOut(Range range) {
			if(r>range.maxR || c>range.maxC || r<range.minR || c<range.minC) {
				return true;
			}
			return false;
		}

		boolean isOut() {
			if(r>R|| c>C || r<1 || c<1) {
				return true;
			}
			return false;
		}
	}
	
	static class Range {
		int minR;
		int minC;
		int maxR;
		int maxC;
		
		public Range(int minR, int minC, int maxR, int maxC) {
			this.minR = minR;
			this.minC = minC;
			this.maxR = maxR;
			this.maxC = maxC;
		}

		@Override
		public String toString() {
			return "Range [minR=" + minR + ", minC=" + minC + ", maxR=" + maxR + ", maxC=" + maxC + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		minR = minC = Integer.MAX_VALUE;
		maxR = maxC = Integer.MIN_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R+2][C+2];
		visited = new boolean[R+2][C+2];
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					minR = Math.min(minR, i);
					minC = Math.min(minC, j);
					maxR = Math.max(maxR, i);
					maxC = Math.max(maxC, j);
				}
			}
		}
		
		Range range = new Range(minR-1, minC-1, maxR+1, maxC+1);
		
		Position start = new Position(minR-1, minC-1);
		
		int preCheese=0;
		int cheese=0;
		int cnt=0;
//		System.out.println(range);
		while(true) {
//			printMap();
			visited = new boolean[R+2][C+2];
			cheese = bfs(start, range);
			//cheese = bfs(start);
			if(cheese==0) break;
			preCheese=cheese;
			cnt++;
			range = new Range(minR, minC, maxR, maxC);
			start = new Position(minR, minC);
			
//			System.out.println(range);
		}
		System.out.println(cnt);
		System.out.println(preCheese);
		
//		printMap();
	}
	
	private static int bfs(Position start) {
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		visited[start.r][start.c] = true;
		int cnt=0;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut()) continue;
				if(visited[next.r][next.c]) continue;
				visited[next.r][next.c] = true;
				if(map[next.r][next.c]==1) {
					cnt++;
					map[next.r][next.c] = 0;
					continue;
				}
				que.offer(next);
			}
		}
		return cnt;
	}

	private static int bfs(Position start, Range range) {
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		visited[start.r][start.c] = true;
		int cnt=0;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut(range)) continue;
				if(visited[next.r][next.c]) continue;
				visited[next.r][next.c] = true;
				if(map[next.r][next.c]==1) {
					minR = Math.min(minR, next.r);
					minC = Math.min(minC, next.c);
					maxR = Math.max(maxR, next.r);
					maxC = Math.max(maxC, next.c);
					cnt++;
					map[next.r][next.c] = 0;
					continue;
				}
				que.offer(next);
			}
		}
		return cnt;
	}

	
	private static void printMap() {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}