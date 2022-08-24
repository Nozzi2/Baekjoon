import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1,0,0,1};
	static int[] dc = {0,-1,1,0};
	
	static int N,resultStep, step;
	static int[][] map;
	static boolean[][] visited;
	static Shark baby;
	static LinkedList<Position> que;
	
	static class Shark {
		int r;
		int c;
		int level;
		int exp;
		
		Shark(int r, int c, int level){
			this.r = r;
			this.c = c;
			this.level = level;
		}
		
		public void eat(Position next) {
			map[next.r][next.c] = 0;
			this.r = next.r;
			this.c = next.c;
			exp++;
			if(exp==level) {
				level++;
				exp=0;
			}
		}
		
		public void print() {
			System.out.printf("현재 위치 : %d, %d / 레벨 : %d , 경험치 : %d\n",this.r, this.c, this.level, this.exp);
		}
	}
	
	static class Position implements Comparable<Position> {
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r>N || c>N) {
				return true;
			}
			return false;
		}
		
		public void print() {
			System.out.printf("현재 위치 : %d, %d\n",this.r, this.c);
		}

		@Override
		public int compareTo(Position o) {
			if(r - o.r == 0) return c-o.c;
            return r-o.r;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/16236boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		visited = new boolean[N+2][N+2];
		baby=null;
		resultStep=0;
		que = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					baby = new Shark(i,j,2);
					map[i][j] =0;
				}
			}
		}

		
		while(true) {
			step++;
			if(bfs()) {
				que.clear();
				visited = new boolean[N+2][N+2];
				resultStep+=step;
				step=0;
			} else if(que.size()!=0) {
				Collections.sort(que);
			} else if(que.size()==0) {
				break;
			}
		}
		System.out.println(resultStep);
		
	}
	
	

	private static void printResult() {
		//bfs();
		baby.print();
		System.out.println(resultStep);
		printMap();
		System.out.println();
	}



	private static boolean bfs() {
		List<Position> fishs = new ArrayList<>();
		que.add(new Position(baby.r, baby.c));
		int cnt = que.size();
		visited[baby.r][baby.c] = true;
		for(int T=0, endT=cnt; T<endT; T++) {
			Position cur = que.get(0);
			que.remove();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut() || map[next.r][next.c] > baby.level || visited[next.r][next.c]) {
					continue;
				}
				if(map[next.r][next.c]!=0 && map[next.r][next.c] < baby.level) {
					fishs.add(next);
				}
				que.offer(next);
				visited[next.r][next.c] = true;
			}
		}
		
		if(!fishs.isEmpty()) { //만약 먹을 수 있는 고기가 있다면 우선순위가 높은 고기먼저 먹어야함.
			Collections.sort(fishs);
			baby.eat(fishs.get(0));
			return true;
		}
		return false;
	}

	private static void printMap() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}