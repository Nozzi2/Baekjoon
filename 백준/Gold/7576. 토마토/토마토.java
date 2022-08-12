import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int[][] map;
	static int dist;
	static Queue<position> que;
	
	static class position{
		int r;
		int c;
		
		position(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		map = new int[r+2][c+2];
		que = new LinkedList<position>();
		boolean isPerfect = true;
		for(int i=1; i<=r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					que.offer(new position(i,j));
				} else if(isPerfect && map[i][j]==0) {
					isPerfect=false;
				}//if
			}//for j
		}//for i
		
		if(!isPerfect) {
			dist = 1;
			bfs();
			
			int max = Integer.MIN_VALUE;
			boolean isBreak = false;
			for(int i=1; i<=r; i++) {
				for(int j=1; j<=c; j++) {
					if(map[i][j] == 0) {
						isBreak = true;
						break;
					}
					max = Math.max(max, map[i][j]);
				}
				if(isBreak) {
					System.out.println(-1);
					break;
				}
			}
			if(!isBreak) {
				System.out.println(max-1);
			}
		} else {
			System.out.println(0);
		}
		
		
	}
	private static void bfs() {;
		//위에서 이미 큐에 넣어놨음
	
		while(!que.isEmpty()) {
			int size=que.size();
			for(int i=0; i<size; i++) {
				position cur = que.poll();
				map[cur.r][cur.c] = dist;
				//System.out.println(i);
				for(int j=0; j<4; j++) {
					int nr = cur.r+dr[j];
					int nc = cur.c+dc[j];
					if(isOut(nr,nc) || map[nr][nc]==-1) {
						continue;
					} else if(map[nr][nc]==0 || map[nr][nc] > map[cur.r][cur.c]+1) {
						que.offer(new position(nr,nc));
						map[nr][nc] = dist+1;
					}
				}
			}
			dist++;
			
		}
		
	}
	private static boolean isOut(int nr, int nc) {
		if(nr==0 || nc==0 || nr>map.length-2 || nc>map[0].length-2 ) {
			return true;
		}
		return false;
	}

}