import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-2,-2,0,0,2,2};
	static int[] dc = {-1,1,2,-2,1,-1};
	
	static boolean[][] map;
	static int R;
	
	static class Position {
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r<0 || c<0 || r>=R || c>=R) {
				return true;
			}
			return false;
		}
		
		public void print() {				
			System.out.printf("%d,%d\n", r, c);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		R = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Position start = new Position(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		Position target = new Position(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		map = new boolean[R][R];
		
		int result = bfs(start, target);
		System.out.println(result);
	}

	private static int bfs(Position start, Position target) {
		if(start.r == target.r && start.c == target.c) return 0;
		
		Queue<Position> que = new LinkedList<>();
		que.offer(start);
		map[start.r][start.c]=true;
		int dist=0;
		while(!que.isEmpty()) {
			dist++;
			for(int T=0, endT=que.size(); T<endT; T++) {
				Position cur = que.poll();
				for(int i=0, endi=dr.length; i<endi; i++) {
					Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
					if(next.isOut() || map[next.r][next.c]) continue;
					if(next.r == target.r && next.c == target.c) {
						return dist;
					}
					map[next.r][next.c] = true;
					que.offer(next);
				}
			}
		}
		return -1;
	}

	private static boolean isNear(Position next, Position target) {
		int distR = Math.abs(next.r - target.r);
		int distC = Math.abs(next.c - target.c);
		if(distR<=1 || distC<=1) {
			return true;
		}
		return false;
	}
}