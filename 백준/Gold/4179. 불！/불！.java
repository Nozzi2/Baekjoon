import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static boolean[][] wall;	//벽의 위치가 저장된 배열
	static boolean[][] isBurn;	//타버린 곳인지 여부
	static boolean[][] visited;	//지훈이 방문여부
	//static int[][] map;			//몇초 후에 지나갈 수 없는지의 시간 배열
	
	static Queue<Position> burns;
	static Queue<Position> moves;
	
	static class Position{
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r>R || c>C) {
				return true;
			}
			return false;
		}

		public void next(int i) {
			this.r += dr[i];
			this.c += dc[i];
		}
	}
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/4179boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//map = new int[R+2][C+2];
		isBurn = new boolean[R+2][C+2];
		visited = new boolean[R+2][C+2];
		wall = new boolean[R+2][C+2];
		List<Position> fires = new ArrayList<>();
		moves = new LinkedList<>();
		burns = new LinkedList<>();
		
		for(int i=1, endi=R; i<=endi; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1, endj=C; j<=endj; j++) {
				if(chars[j-1] == '#') {
					wall[i][j] = true;
					isBurn[i][j] = true;
				} else if(chars[j-1] == 'F') {
					burns.offer(new Position(i,j));
					isBurn[i][j] = true;
				} else if(chars[j-1] == 'J') {
					moves.offer(new Position(i,j));
				}
			}
		}
		
		int time = 0;
		int result=-1;
		out: while(true) {
			time++;
			int burnsSize = burns.size();
			for(int i=0; i<burnsSize; i++) {
				Position fire = burns.poll();
				setBurn(fire);
			}
			
			int movesSize = moves.size();
			for(int i=0; i<movesSize; i++) {
				Position man = moves.poll();
				result = move(man,time);
				if(result != -1) break out;
			}
			if(moves.isEmpty()) break;
		}
		
		if(result == -1) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(time);
		}
		
	}
	

	private static int move(Position man, int time) {
		for(int i=0; i<4; i++) {
			Position next = new Position(man.r+dr[i], man.c+dc[i]);
			if(next.isOut()) return time+1; //경계 밖으로 벗어나면 bfs 종료
			if(visited[next.r][next.c] || isBurn[next.r][next.c]) continue; //시간내에 도달하지 못하거나 0이 아니면
			visited[next.r][next.c] = true;
			moves.offer(next);
		}
		return -1;
	}
	
	private static void setBurn(Position cur) {
		for(int i=0; i<4; i++) {
			Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
			if(next.isOut() || wall[next.r][next.c] || isBurn[next.r][next.c]) continue;
			isBurn[next.r][next.c] = true;
			burns.offer(next);
		}
	}
	

	private static void printMap() {
		for(int i=1, endi=R; i<=endi; i++) {
			for(int j=1, endj=C; j<=endj; j++) {
				if(isBurn[i][j]) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}

}