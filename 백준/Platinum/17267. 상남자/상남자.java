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
	static boolean[][] visited;
	
	static class Pos {
		int r;
		int c;
		int le;
		int ri;
		
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
		int le = Integer.parseInt(st.nextToken());
		int ri = Integer.parseInt(st.nextToken());
		
		map = new int[R+2][C+2];
		lmap = new int[R+2][C+2];
		rmap = new int[R+2][C+2];
		visited = new boolean[R+2][C+2];
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
		
		//dfs( start,le,ri );
		bfs(start);
		
//		System.out.println();
//		for(int i=1; i<=R; i++) {
//			for(int j=1; j<=C; j++) {
//				System.out.print(lmap[i][j]!=Integer.MIN_VALUE?"1":"0");
//			}
//			System.out.println();
//		}
//		System.out.println();
		System.out.println(cnt);
	}

	private static void bfs(Pos start) {
		Queue<Pos> que = new ArrayDeque<>();
		que.offer(start);
		lmap[start.r][start.c] = start.le;
		rmap[start.r][start.c] = start.ri;
		cnt++;
		while(!que.isEmpty()) {
			Pos cur = que.poll();
			for(int i=0; i<4; i++) {
				if(i==2 && cur.le==0) continue;
				if(i==3 && cur.ri==0) continue;
				
				int nle = i==2?-1:0;
				int nri = i==3?-1:0;
				Pos next = new Pos(cur.r+dr[i],cur.c+dc[i],cur.le+nle,cur.ri+nri);
				
				
				if(next.isOut()) {
//					System.out.println("1번"+next.r+","+next.c);
					continue;
				}
				if(map[next.r][next.c]==1) {
//					System.out.println("2번"+next.r+","+next.c);
					continue;
				}
				if(lmap[next.r][next.c] >= next.le
					&& rmap[next.r][next.c] >= next.ri) {
//					System.out.println("3번"+next.r+","+next.c);
//					System.out.println("  l:"+lmap[cur.r][cur.c]+"/"+next.le);
//					System.out.println("  r:"+rmap[cur.r][cur.c]+"/"+next.ri);
					continue;
				}
				if(lmap[next.r][next.c]==Integer.MIN_VALUE) cnt++;
				lmap[next.r][next.c] = next.le;
				rmap[next.r][next.c] = next.ri;
//				System.out.println("들어는 감? "+next.r+","+next.c);
				que.offer(next);
			}
		}//while
		
	}

	private static boolean dfs(Pos cur, int le, int ri) {
		//맵 벗어났는지,벽여부 체크
		if(cur.isOut() || map[cur.r][cur.c] ==1) {
			lmap[cur.r][cur.c] = Integer.MIN_VALUE;
			rmap[cur.r][cur.c] = Integer.MIN_VALUE;
			return false;
		}
		
		//방문여부 체크
		//lmap[cur.r][cur.c] >= le && rmap[cur.r][cur.c] >= ri
//		if(lmap[cur.r][cur.c] >= le || rmap[cur.r][cur.c] >= ri) return; //방문했었으면 끝내기
//		else { //방문 안했으면 해당칸 방문체크하고 위아래로 모두 dfs 탐색 호출하기
//			if(lmap[cur.r][cur.c] == Integer.MIN_VALUE)	cnt++;
//			lmap[cur.r][cur.c] = le;
//			rmap[cur.r][cur.c] = ri;
//			
//			//위로 dfs 탐색 호출
//			dfs(new Pos(cur.r+1, cur.c), le, ri);
//			//아래로 dfs 탐색 호출
//			dfs(new Pos(cur.r-1, cur.c), le, ri);
//		}
//		if(lmap[cur.r][cur.c] >= le && rmap[cur.r][cur.c] >= ri) {
//			 int num=1;
//		}
//		//if(lmap[cur.r][cur.c] > le || rmap[cur.r][cur.c] > ri) return false; //방문했었으면 끝내기
//		else 
		if(lmap[cur.r][cur.c] < le || rmap[cur.r][cur.c] < ri){ //방문 안했으면 해당칸 방문체크하고 위아래로 모두 dfs 탐색 호출하기
			//위로 한줄 쫙 dfs 탐색 호출
			for(int i=cur.r; i>=1; i--) {
				if(new Pos(i,cur.c).isOut() || map[i][cur.c] ==1) break;
				if(lmap[i][cur.c] == Integer.MIN_VALUE) cnt++;
				lmap[i][cur.c] = le;
				rmap[i][cur.c] = ri;
				if(!dfs(new Pos(i, cur.c), le, ri)) break;
			}
			//아래로 한줄 쫙 dfs 탐색 호출
			for(int i=cur.r+1; i<=R; i++) {
				if(new Pos(i,cur.c).isOut() || map[i][cur.c] ==1) break;
				if(lmap[i][cur.c] == Integer.MIN_VALUE) cnt++;
				lmap[i][cur.c] = le;
				rmap[i][cur.c] = ri;
				if(!dfs(new Pos(i, cur.c), le, ri)) break;
			}
//			//위로 dfs 탐색 호출
//			dfs(new Pos(cur.r+1, cur.c), le, ri);
//			//아래로 dfs 탐색 호출
//			dfs(new Pos(cur.r-1, cur.c), le, ri);
		}
		
		//갖고 있는 le이 있으면 왼쪽  dfs탐색 호출
		if(le!=0) dfs(new Pos(cur.r,cur.c-1),le-1,ri);
		
		//갖고 있는 ri이 있으면 오른쪽  dfs탐색 호출
		if(ri!=0) dfs(new Pos(cur.r,cur.c+1),le,ri-1);
		
		return true;
	}
}