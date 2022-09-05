import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr= {-1,1,0,0,1,1,-1,-1};
	static int[] dc= {0,0,-1,1,1,-1,-1,1};
	
	static int[][] map;
	static int R,C;
	
	static class Position{
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r>R || c>C) return true;
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if(R==0 && C==0) break;
			map = new int[R+2][C+2];
			for(int i=1, endi=R; i<=endi; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1, endj=C; j<=endj; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int cnt=0;
			for(int i=1, endi=R; i<=endi; i++) {
				for(int j=1, endj=C; j<=endj; j++) {
					if(map[i][j]==1) {
						cnt++;
						bfs(new Position(i,j));
						
					}
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void printMap() {
		for(int i=1, endi=R; i<=endi; i++) {
			for(int j=1, endj=C; j<=endj; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void bfs(Position p) {
		Queue<Position> que = new LinkedList<>();
		que.offer(p);
		map[p.r][p.c] = 0;
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0, endi=dr.length; i<endi; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut()) { 
					continue;
				}
				if( map[next.r][next.c]==0) {
					continue;
				}
				que.offer(next);
				map[next.r][next.c]=0;
			}
		}
	}
	
	

}