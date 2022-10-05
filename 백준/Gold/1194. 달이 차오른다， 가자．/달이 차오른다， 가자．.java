import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int R,C,step,minStep;
	static char[][] map;
	static boolean[][][] visited;
	static int keys;
	
	static class Position{
		int r;
		int c;
		int key;
		
		public Position(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
		
		boolean isOut() {
			if(r>R || c>C || r<1 || c<1) {
				return true;
			}
			return false;
		}

		boolean isArrived() {
			if(map[r][c]=='1') {
				return true;
			}
			return false;
		}
		
		boolean isWall() {
			if(map[r][c]=='#') {
				return true;
			}
			return false;
		}
		
		boolean isPath() {
			if(map[r][c]=='.') {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Position [r=").append(r).append(", c=").append(c).append("]");
			return builder.toString();
		}
	}

	public static void main(String[] args) throws IOException {
		keys=0;
		Position start=null;
		step=0;
		minStep = Integer.MAX_VALUE;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+2][C+2];
		visited = new boolean[64][R+2][C+2]; //0은 키가 하나도 없을 때, 1은 a키만 있을 때 63은 모든 키가 있을 때 
		
		for(int i=1; i<=R; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1; j<=C; j++) {
				map[i][j] = chars[j-1];
				if(map[i][j] == '0') {
					start = new Position(i,j,0);
					map[i][j] = '.';
				}
			}//for j
		}//for i
		
		bfs(start);
		if(minStep == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minStep);
		}
	}

	private static void bfs(Position start) {
		Queue<Position> que = new ArrayDeque<>();
		que.offer(start);
		visited[start.key][start.r][start.c] = true;
		
		while(!que.isEmpty()) {
			if(step++>minStep) return; //최소 걸음수보다 크면 가지치기
//			System.out.println(step+"++...");
			
			for(int i=0, endi=que.size(); i<endi; i++) {
				Position cur = que.poll();
				for(int j=0; j<4; j++) {
					Position next = new Position(cur.r+dr[j], cur.c+dc[j], cur.key);
					if(next.isOut() || next.isWall() || visited[next.key][next.r][next.c]) continue;
					if(next.isArrived()) { //도착하면 
//						System.out.println("도착!!"+next.r+next.c);
//						System.out.println("BFS는 최소 걸음수가 보장되지롱~~"+step);
//						System.out.println(step);
						minStep = Math.min(minStep, step);
						//System.exit(0);
						return;
					}
					visited[next.key][next.r][next.c] = true;
					if(next.isPath()) {
						que.offer(next);
						continue;
					}
					if('a' <= map[next.r][next.c] && map[next.r][next.c] <= 'f') { //키가 있는 곳이라면
						if( !((next.key & 1<<(map[next.r][next.c]-'a') )>0) ) { //해당 키를 갖고 있지 않다면
							next.key += 1<<(map[next.r][next.c]-'a'); //해당키 저장
							//visited[key] = new boolean[R+2][C+2];
							for(int a=1; a<=R; a++) {
								for(int b=1; b<=C; b++) {
									visited[next.key][a][b] = false;
								}
							}
//							System.out.println("key 얻었당 "+key+map[next.r][next.c]);
							//int tmpStep = step;
//							System.out.println("bfs start");
							que.offer(next);
//							System.out.println("bfs end");
							//step=tmpStep;
							//next.key -= 1<<(map[next.r][next.c]-'a'); //해당키 저장
						} else {
							
							que.offer(next);
						}
					} else { //문이라면
						if( (next.key & 1<<(map[next.r][next.c]-'A') )>0 ) { // 해당 키를 갖고 있다면
							visited[next.key][next.r][next.c] = true;
							que.offer(next);
//							System.out.println("  "+map[next.r][next.c]);
//							System.out.println("   문이당 "+(1<<(map[next.r][next.c]-'A')));
//							System.out.println("  "+next);
//							System.out.println("  key 값은 "+key);
						} else {
//							System.out.println("  "+map[next.r][next.c]);
//							System.out.println("   문이당 "+(1<<(map[next.r][next.c]-'A')));
//							System.out.println("  "+next);
//							System.out.println("   키없당..ㅠㅠ");
							continue;
						}
					}
				}//for j
			}//for i
		}//while
	}
}