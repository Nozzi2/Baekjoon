import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1,1,0,0}; //일반 4방이동방향
	static int[] dc = {0,0,-1,1}; //일반 4방이동방향
	static int[] dnr = {-1,-2,-2,-1,1,2,2,1}; //나이트 8방이동방향
	static int[] dnc = {-2,-1,1,2,2,1,-1,-2}; //나이트 8방이동방향
	static int R,C,step;
	static int[][] map;
	static int[][] chances; //방문체크겸 방문했을 때 남은 점프가능횟수 저장할 배열
	
	static class Pos {
		int r;
		int c;
		int chance; //현재 원숭이가 점프할 수 있는 횟수
		
		public Pos(int r, int c, int chance) {
			this.r = r;
			this.c = c;
			this.chance = chance;
		}
		
		boolean isOut() {
			if(r<1 || c<1 || r>R || c>C) {
				return true;
			}
			return false;
		}
		boolean isWall() {
			if(map[r][c]==1) {
				return true;
			}
			return false;
		}
		
		boolean isArrived() {
			if(r==R && c==C) return true;
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int jumpChance = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R+2][C+2];
		chances = new int[R+2][C+2]; //방문체크겸 방문했을 때 남은 점프가능횟수 저장할 배열
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				chances[i][j] = Integer.MIN_VALUE;
			}
		}
		
		Pos start = new Pos(1,1, jumpChance);
		
		if(start.r==R && start.c==C) { //W, H가 1일때는 시작점이 곧 도착점인 경우임
			System.out.println(0);
		} else {
			boolean result = bfs(start);
			System.out.println(result?step:"-1");
			
		}
	}

	//도착하면 true반환, 도착하지 못하면 false 반환
	private static boolean bfs(Pos start) {
		Queue<Pos> que = new ArrayDeque<>();
		que.offer(start);
		chances[start.r][start.c] = start.chance; //방문체크겸 방문횟수 저장
		
		while(!que.isEmpty()) {
			step++;
			for(int q=0, endq=que.size(); q<endq; q++) {
				Pos cur = que.poll();
				//상하좌우 4방탐색
				for(int i=0; i<4; i++) {
					Pos next = new Pos(cur.r+dr[i],cur.c+dc[i],cur.chance);
					if(next.isOut()) continue;
					if(next.isWall()) continue;
					//현재 기회가 이미 방문했을때 기회보다 같거나 적을때 탐색 종료
					//이전에 방문했다면 굳이 더 탐색할 필요가 없기 때문
					if(next.chance <= chances[next.r][next.c]) continue;
					if(next.isArrived()) return true;
					chances[next.r][next.c] = next.chance;
					que.offer(next);
				}
				
				//현재 점프기회가 0보다 클때만 나이트 8방탐색 진행
				if(cur.chance>0) {
					for(int i=0; i<8; i++) {
						Pos next = new Pos(cur.r+dnr[i],cur.c+dnc[i],cur.chance-1);
						if(next.isOut()) continue;
						if(next.isWall()) continue;
						//현재 기회가 이미 방문했을때 기회보다 같거나 적을때 탐색 종료
						//이전에 방문했다면 굳이 더 탐색할 필요가 없기 때문
						if(next.chance <= chances[next.r][next.c]) continue;
						if(next.isArrived()) return true;
						chances[next.r][next.c] = next.chance;
						que.offer(next);
					}
				}
			}//for q
		}//while
		return false;
	}
}