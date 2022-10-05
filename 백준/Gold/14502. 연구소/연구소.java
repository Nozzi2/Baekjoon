import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R,C,safeArea;
	static int[][] map;
	static boolean[][] visited;
	static List<Position> virusList;
	
	static class Position{
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r>R || c>C || r<1 || c<1) {
				return true;
			}
			return false;
		}
		
		public boolean isWall() {
			if(map[r][c]==1) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		virusList = new ArrayList<Position>();
		int safeAreaOrigin=0;
		int maxSafeArea = Integer.MIN_VALUE;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R+2][C+2];
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusList.add(new Position(i,j));
				} else if(map[i][j] == 0) { //
					safeAreaOrigin++;
				}
			}//for j
		}//for i
		
		//완전탐색
		for(int a=1; a<=(R*C)-2; a++) {
			int ar = ((a-1)/C)+1;
			int ac = (a-1)%C+1;
			if(map[ar][ac] == 0) {
				map[ar][ac] = 1;
				safeAreaOrigin--;
			} else continue;
			
			for(int b=a+1; b<=(R*C)-1; b++) {
				int br1 = ((b-1)/C)+1;
				int bc = (b-1)%C+1;
				if(map[br1][bc] == 0) {
					map[br1][bc] = 1;
					safeAreaOrigin--;
				} else continue;
				
				for(int c=b+1; c<=R*C; c++) {
					int cr = ((c-1)/C)+1;
					int cc = (c-1)%C+1;
					if(map[cr][cc] == 0) {
						map[cr][cc] = 1;
						safeAreaOrigin--;
					} else continue;
					
					//바이러스 BFS 탐색으로 안전영역 갯수 갱신
					safeArea = safeAreaOrigin;
					visited = new boolean[R+2][C+2];
					for(Position p : virusList) {
						bfs(p);
					}
					maxSafeArea = Math.max(maxSafeArea, safeArea);
					
					map[cr][cc] = 0;
					safeAreaOrigin++;
				}
				map[br1][bc] = 0;
				safeAreaOrigin++;
			}
			map[ar][ac] = 0;
			safeAreaOrigin++;
		}
		
		System.out.println(maxSafeArea);
	}

	private static void printMap() {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	private static void bfs(Position start) {
		if(visited[start.r][start.c]) {
			safeArea++;
			return;
		}
		
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		visited[start.r][start.c] = true;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut()) continue;
				if(next.isWall()) continue;
				if(visited[next.r][next.c]) continue;
				
				visited[next.r][next.c] = true;
				safeArea--;
				que.offer(next);
			}
		}
		
	}
}