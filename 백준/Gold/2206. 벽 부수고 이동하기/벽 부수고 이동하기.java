import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R,C;
	static boolean[][] map;
	static boolean[][][] visited;
	
	static class Pos {
		int r;
		int c;
		int chance; //벽을 부술 기회 단 한번!
		
		public Pos(int r, int c, int chance) {
			this.r = r;
			this.c = c;
			this.chance = chance;
		}
		
		boolean isOut() {
			return r>R || c>C || r<1 || c<1; 
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inArr = br.readLine().split(" ");
		R = Integer.parseInt(inArr[0]);
		C = Integer.parseInt(inArr[1]);
		
		
		
		map = new boolean[R+2][C+2];
		visited = new boolean[2][R+2][C+2];
		//visited[1][r][c] : 벽부술기회 1번 있었을 때 탐색 여부
		//visited[0][r][c] : 벽부술기회 0번 있었을 때 탐색 여부
		
		for(int i=1; i<=R; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1; j<=C; j++) {
				if(chars[j-1]=='1') {
					map[i][j] = true;
				}
			}
		}
		
	
		if(R==1 && C==1) { //출발지가 도착지인 경우
			System.out.println(1);
		} else {
			Pos start = new Pos(1,1,1); //r,c = 1,1 / chance=1 로 시작
			System.out.println(bfs(start)); //bfs 탐색결과 출력
		}
		

		
	}

	private static int bfs(Pos start) {
		Queue<Pos> que = new ArrayDeque<>();
		que.offer(start);
		visited[0][start.r][start.c] = true;
		int step=1;
		while(!que.isEmpty()) {
			step++;
			for(int q=0, endq=que.size(); q<endq; q++) {
				Pos cur = que.poll();
				//벽부술 기회 있을 때
				if(cur.chance==1) {
					//벽이든 아니든 탐색 가능함
					for(int i=0; i<4; i++) {
						Pos next = new Pos(cur.r+dr[i], cur.c+dc[i], cur.chance);
						if(visited[next.chance][next.r][next.c]) continue; //방문했는지 (벽부수기전)
						if(next.isOut()) continue;
						if(map[next.r][next.c]) { //벽을 만날경우
							next.chance = 0; //벽부술 기회 사용
						}
						if(next.r == R && next.c == C) return step;
						que.offer(next);
						visited[next.chance][next.r][next.c] = true;
					}//for i
				} else {//벽부술 기회 없을 때
					//벽이면 탐색 불가능
					for(int i=0; i<4; i++) {
						Pos next = new Pos(cur.r+dr[i], cur.c+dc[i], cur.chance);
						if(visited[next.chance][next.r][next.c]) continue; //방문했는지 (벽부순후)
						if(next.isOut()) continue;
						if(map[next.r][next.c]) continue; //벽을 만날경우 탐색 종료
						if(next.r == R && next.c == C) return step;
						que.offer(next);
						visited[next.chance][next.r][next.c] = true;
					}//for i
				}//if else
			}//for q
		}//while
		return -1;
	}

	private static void printMap() {
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.print(map[i][j]?1:0);
			}
			System.out.println();
		}
	}
}
