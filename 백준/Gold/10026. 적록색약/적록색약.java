import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int size;
	static int cntNormal, cntRG;
	static char[][] map;
	static boolean[][] visited;

	static class Position {
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r >size || c>size) {
				return true;
			}
			
			return false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		size = Integer.parseInt(br.readLine());
		map = new char[size+2][size+2];
		
		for(int i=1, endi=size; i<=endi; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1, endj=size; j<=endj; j++) {
				map[i][j] = chars[j-1];
			}//for j
		}//for i

		visited = new boolean[size+2][size+2];
		cntNormal = 0;
		for(int i=1, endi=size; i<=endi; i++) {
			for(int j=1, endj=size; j<=endj; j++) {
				Position start = new Position(i,j);
				bfsNormal(start);
			}//for j
		}//for i
		
		visited = new boolean[size+2][size+2];
		cntRG = 0;
		for(int i=1, endi=size; i<=endi; i++) {
			for(int j=1, endj=size; j<=endj; j++) {
				Position start = new Position(i,j);
				bfsRG(start);
			}//for j
		}//for i
		System.out.printf("%d %d",cntNormal,cntRG);
	}

	private static void bfsRG(Position start) {
		if(visited[start.r][start.c]) return; //이미 방문한 적이 있다면 bfs 종료
		cntRG++;
		Queue<Position> que = new LinkedList<>();
		char target = map[start.r][start.c];
		que.offer(start);
		visited[start.r][start.c] = true;
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut() || visited[next.r][next.c]
						|| !isRG(target, next)){
					continue;
				}
				que.offer(next);
				visited[next.r][next.c] = true;
			}
		}
	}

	private static void bfsNormal(Position start) {
		if(visited[start.r][start.c]) return; //이미 방문한 적이 있다면 bfs 종료
		cntNormal++;
		Queue<Position> que = new LinkedList<>();
		char target = map[start.r][start.c];
		que.offer(start);
		visited[start.r][start.c] = true;
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut() || visited[next.r][next.c] || map[next.r][next.c]!=target){
					continue;
				}
				que.offer(next);
				visited[next.r][next.c] = true;
			}
		}
	}
	
	private static boolean isRG(char target, Position next) {
		if(target=='R' || target=='G') {
			if(map[next.r][next.c]=='R' || map[next.r][next.c]=='G') {
				return true;
			}
		} else {
			if(map[next.r][next.c]=='B') {
				return true;
			}
		}
		return false;
	}
}

/*
5
RRRBB
GGBBB
BBBRR
BBRRR
RRRRR
*/