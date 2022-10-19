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
	static int[][] lmap, rmap;
	
	static class Pos {
		int r;
		int c;
		int le; //왼쪽으로 갈 수 있는 칸수
		int ri; //오른쪽으로 갈 수 있는 칸수
		
		public Pos(int r, int c) { 
			this.r = r;
			this.c = c;
		}
		public Pos(int r, int c, int le, int ri) { 
			this.r = r;
			this.c = c;
			this.le = le;
			this.ri = ri;
		}
		
		boolean isOut() {
			if(r<1 || c<1 || r>R || c>C) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int le = Integer.parseInt(st.nextToken()); //왼쪽으로 갈 수 있는 칸수
		int ri = Integer.parseInt(st.nextToken()); //오른쪽으로 갈 수 있는 칸수
		
		map = new int[R+2][C+2];
		lmap = new int[R+2][C+2];
		rmap = new int[R+2][C+2];
		cnt=0;
		Pos start = null;
		
		for(int i=1; i<=R; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1; j<=C; j++) {
				map[i][j] = chars[j-1]-'0';
				if(map[i][j] == 2) {
					start = new Pos(i,j, le, ri);
				}
				lmap[i][j] = Integer.MIN_VALUE;
				rmap[i][j] = Integer.MIN_VALUE;
			}
		}
		bfs(start);
		System.out.println(cnt);
	}

	
	private static void bfs(Pos start) {
		Queue<Pos> que = new ArrayDeque<>();
		que.offer(start);
		//방문체크 대신 방문했을 때 le,ri 값을 저장함
		lmap[start.r][start.c] = start.le;
		rmap[start.r][start.c] = start.ri;
		cnt++;
		
		while(!que.isEmpty()) {
			Pos cur = que.poll();
			for(int i=0; i<4; i++) {
				//왼쪽 또는 오른쪽 탐색할 떄 le, ri 값이 1 이상이여야함
				if(i==2 && cur.le==0) continue;
				if(i==3 && cur.ri==0) continue;
				
				int nle = i==2?-1:0;
				int nri = i==3?-1:0;
				Pos next = new Pos(cur.r+dr[i],cur.c+dc[i],cur.le+nle,cur.ri+nri);
				
				if(next.isOut()) continue;
				if(map[next.r][next.c]==1) continue;
				
				//이전에 방문했던 값이 le, ri 둘다 같거나 적게 남았다면,
				//즉, 이전에 방문 했던 값이 방문할 기회가 더 많았더라면
				//ex) 이전 방문했을 때 왼2칸, 오2칸 더 갈 수 있었는데,
				//    이번에 방문했을 때 왼0칸, 오2칸이라면 더이상 탐색할 필요 없음.
				//	   이전에 방문했을 때가 더 많이 갈 수 있기 때문.
				if(lmap[next.r][next.c] >= next.le
					&& rmap[next.r][next.c] >= next.ri) continue;
				if(lmap[next.r][next.c]==Integer.MIN_VALUE) cnt++;//첫 방문만 카운트
				lmap[next.r][next.c] = next.le;
				rmap[next.r][next.c] = next.ri;
				que.offer(next);
			}
		}//while
	}
}