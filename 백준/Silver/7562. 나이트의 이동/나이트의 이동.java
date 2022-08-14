import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {2, 2, -2, -2, 1, 1, -1, -1};
	static int[] dc = {-1, 1, -1, 1, 2, -2, -2, 2};
	static int N;
	static int dist;
	static boolean[][] map;
	static position me;
	static position target;
	
	static class position{
		int r;
		int c;
		
		position(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int T=0; T<TC; T++) {
			N = Integer.parseInt(br.readLine());
			map = new boolean[N][N];
			dist = 0;
			st = new StringTokenizer(br.readLine());
			me = new position(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			target = new position(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			bfs(me);
			
			System.out.println(dist);
		}
	}

	private static void bfs(position cur) {
		//현재 위치가 도착점일 수도 있으니 종료해야함
		if(cur.r == target.r && cur.c == target.c) {
			return;
		}
		Queue<position> que = new LinkedList<position>();
		que.offer(cur);
		map[cur.r][cur.c] = true;
		
		while(!que.isEmpty()) {
			int size = que.size(); //큐 사이즈마다 거리가 1씩 증가된 것과 같음
			dist++;
			for(int T=0; T<size; T++) {
				position next = que.poll();
				
				for(int i=0; i<8; i++) {
					int nr = next.r+dr[i];
					int nc = next.c+dc[i];
					
					//배열을 벗어났거나, 방문했었거나,		  옳지 않은 방향으로 갔다면 다음 탐색을 해야함.
					if(isOut(nr,nc) || map[nr][nc] || !isRightDir(next, nr, nc)) {
						continue;
					}
					//도착지와 같다면 종료
					if(nr == target.r && nc == target.c) {
						return;
					}
					que.offer(new position(nr,nc));
					map[nr][nc] = true;
				}//for i
			}//for T
		}//while
		
	}

	//나이트가 맞는 방향으로 가는지 알려주는 메소드
	private static boolean isRightDir(position cur, int nr, int nc) {
		int cr = cur.r;
		int cc = cur.c;
		
		int distTargetCR = Math.abs(target.r-cr); // 도착지와 현재와의 줄 거리
		int distTargetCC = Math.abs(target.c-cc); // 도착지와 현재와의 칸 거리
		
		int distTargetNR = Math.abs(target.r-nr); // 도착지와 한번 움직인 곳과의 줄 거리
		int distTargetNC = Math.abs(target.c-nc); // 도착지와 한번 움직인 곳과의 칸 거리
		
		//R이든 C이든 한번 움직이면 거리가 줄어들어야만 한다.
		//조건 추가함! > 도착지로부터 한칸 떨어져 있는 경우라면 한번쯤은 늘어나도 괜찮음
		if(distTargetCR>distTargetNR || distTargetCC>distTargetNC || distTargetNR==1 || distTargetNC==1) {
			return true;
		}
		
		return false;
	}

	private static boolean isOut(int nr, int nc) {
		if(nr<0 || nc<0 || nr>N-1 || nc>N-1) {
			return true;
		}
		return false;
	}
}