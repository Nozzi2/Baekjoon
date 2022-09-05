import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	
	static int[][] map;
	static boolean[][] visited;
	static int N;
	
	static class Position{
		int r;
		int c;
		int step;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		Position(int r, int c, int step){
			this.r = r;
			this.c = c;
			this.step = step;
		}
		
		public void setStep(int step) {
			this.step = step;
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r>N || c>N) {
				return true;
			}
			return false;
		}
		
		public boolean isArrived() {
			if(r==N && c==N) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		visited = new boolean[N+2][N+2];
		
		for(int i=1, endi=N; i<=endi; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1, endj=N; j<=endj; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(bfs()) {
			System.out.println("HaruHaru");
		} else {
			System.out.println("Hing");
		}
		
	}

	private static boolean bfs() {
		Queue<Position> que = new LinkedList<>();
		que.offer(new Position(1,1,map[1][1]));
		visited[1][1]=true;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0, endi=dr.length; i<endi; i++) {
				Position next = new Position(cur.r+(cur.step*dr[i]),cur.c+(cur.step*dc[i]));
				if(next.isOut() || visited[next.r][next.c]) continue;
				if(next.isArrived()) return true;
				next.setStep(map[next.r][next.c]);
				visited[next.r][next.c] = true;
				que.offer(next);
			}
		}
		return false;
	}
}