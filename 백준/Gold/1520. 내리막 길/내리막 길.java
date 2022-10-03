import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R,C,cnt;
	static int[][] map;
	static int[][] memo;
	
	static class Position {
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
		
		boolean isArrived() {
			if(r==R && c==C) {
				return true;
			}
			return false;
		}
		
		boolean higherThan(Position p) {
			if(map[r][c] > map[p.r][p.c]) {
				return true;
			}
			return false;
		}
	}

	//구글링해서 풀이법 보고 구현함
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R+2][C+2];
		memo = new int[R+2][C+2];
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memo[i][j] = -1; //방문하지 않았음 처리
			}
		}
		
		Position start = new Position(1,1);
		
		System.out.println(dfs(start));
	}
	
	
	//메모리초과되어 메모이제이션 추가
	/*
	 * 방문여부를 체크하고, 방문한 곳을 탐색하여 도착지에 도달한다면 +1을 리턴받아서 해당 경로의 도착 경우의 수를 저장한다.
	 * 상하좌우로 모두 탐색하기 때문에 모든 경우의 수를 고려할 수 있고,
	 * 방문하는 곳은 단 한번만 방문하여 불필요한 연산을 줄일 수 있다. 
	 * 최종적으로는 시작점에 모든 방문가능한 경우의 수가 저장된다.
	 * ex) 4 3	 >	2 1
	 *     2 1		1 -1
	 */
	private static int dfs(Position start) {
		if(start.isArrived()) {
			return 1;
		}
		
		if(memo[start.r][start.c] != -1) { //방문했었다면
			return memo[start.r][start.c];
		} else { //방문하지 않았다면
			memo[start.r][start.c]=0; //일단 방문 처리
			for(int i=0; i<4; i++) { //해당칸의 모든 경로의 경우의 수를 더하기 위해 반복
				Position next = new Position(start.r+dr[i], start.c+dc[i]);
				
				if(next.isOut()) continue;
				if(start.higherThan(next)) {
					//다음 방문할 곳에 도착 경로가 몇개인지 저장
					memo[start.r][start.c] += dfs(next); 
				}
			}
		}
		
		return memo[start.r][start.c];
	}

	//메모리초과
	private static void bfs(Position start) {
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		
		while(!que.isEmpty()) {
			Position cur = que.poll();
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				if(next.isOut()) continue;
				if(!cur.higherThan(next)) continue; //현재블럭이 다음 블럭보다 높지 않으면
				if(next.isArrived()) {
					cnt++;
					continue;
				}
				que.offer(next);
			}
		}
	}


}